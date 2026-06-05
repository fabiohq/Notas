package com.santander.bnc.bsn049.bncbsn049savekycservice.domain.service;


import java.time.LocalDate;
import java.time.Period;

import java.util.List;

import com.santander.bnc.bsn049.bncbsn049savekycservice.application.serviceImp.KycServiceImp;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.models.PersonNaturalModel;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.models.ResponseModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.bean.AltairRequest;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.bean.AltairResponse;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.bean.CabeceraBean;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.bean.DataRequestBean;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.bean.DesiredException;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.bean.KYCQuestionnairesBean;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.bean.OneFccRequest;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.bean.OneFccResponse;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.bean.OneFccTokenResponse;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.bean.PartyBean;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.bean.Questions;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.bean.RiskBean;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.bean.SaveKYCRequest;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.bean.SaveKYCResponse;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.bean.ScoreBean;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.bean.SesionBean;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.bean.ValidityPeriodBean;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.client.IAltairInformation;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.client.SoapClientList;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.client.SoapClientRisk;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.repository.QuestionRepository;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.util.SaveRequestValidator;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.wsdl_list.FVERIFICATERCEROV3;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.wsdl_list.FVERIFICATERCEROV3Response;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.wsdl_risk.FNIVELRIESGOV2;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.wsdl_risk.FNIVELRIESGOV2Response;

import com.santander.bnc.bsn049.bncbsn049savekycservice.infrastructure.config.LocalDateTypeAdapter;

import feign.RetryableException;


@Service
public class SaveService {

	@Autowired
	private IAltairInformation iAltairInformation;

	@Value("${engine.protocol}")
	private String protocol;
	@Value("${engine.host}")
	private String host;
	@Value("${engine.context}")
	private String context;
	@Value("${engine.mqRoute}")
	private String mqRoute;
	@Value("${valid-month}")
	private String validMonth;
	@Value("${porcentageVigia}")
	private String porcentageVigia;
	@Value("${urlOneFcc}")
	private String urlOneFcc;
	@Value("${userOneFcc}")
	private String userOneFcc;
	@Value("${passOneFcc}")
	private String passOneFcc;

	@Autowired
	private SoapClientRisk soapClientRisk;

	@Autowired
	private SoapClientList soapClientList;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	KycServiceImp kycServiceImp;






	private static final Logger logger = LoggerFactory.getLogger(SaveService.class);

	public AltairResponse sendInformationAltair(String document, String typeDocument, String penumpe)
			throws DesiredException {

		AltairResponse altairResponse = new AltairResponse();
		AltairRequest altairRequest = new AltairRequest();
		CabeceraBean cabecera = new CabeceraBean();
		SesionBean sesionBean = new SesionBean();
		cabecera.setSesion(sesionBean);
		altairRequest.setCabecera(cabecera);
		DataRequestBean dataRequest = new DataRequestBean();
		dataRequest.setNumDocumento(document);
		dataRequest.setTipoDocumento(typeDocument);
		dataRequest.setPENUMPE(penumpe);
		altairRequest.setData(dataRequest);
		try {


			altairResponse = iAltairInformation.altairResponse(mqRoute, altairRequest);


			printAndSaveAltair(altairResponse, altairRequest);
		} catch (RetryableException e) {
			logger.error("Error Altair: ", e);
			if (document != null) {
				throw new DesiredException("'party.person.documents[0].documentNumber' not found", 404, e);
			} else {
				throw new DesiredException("'party.partyId' not found", 404, e);
			}
		} catch (Exception e) {
			logger.error("Error Altair: ", e);
			if (document != null) {
				throw new DesiredException("'party.person.documents[0].documentNumber' not found", 404,e);
			} else {
				throw new DesiredException("'party.partyId' not found", 404, e);
			}
		}

		return altairResponse;

	}

	public FNIVELRIESGOV2Response sendInformationVigia(FNIVELRIESGOV2 riskLevelRequest) throws DesiredException {

		FNIVELRIESGOV2Response vigiaResponse = new FNIVELRIESGOV2Response();
		vigiaResponse = soapClientRisk.getRiskResponse(riskLevelRequest);

		if (vigiaResponse.getFNIVELRIESGOV2Result().getNivelRiesgo().equals("")) {
			throw new DesiredException("Internal Server Error", 500);
		}
		printAndSaveVigia(vigiaResponse, riskLevelRequest);
		return vigiaResponse;

	}

	public FVERIFICATERCEROV3Response sendInformationVigiaList(FVERIFICATERCEROV3 listPEPRequest)
			throws DesiredException {


		FVERIFICATERCEROV3Response vigiaResponse = new FVERIFICATERCEROV3Response();
		vigiaResponse = soapClientList.getListResponse(listPEPRequest);

		if (vigiaResponse.getFVERIFICATERCEROV3Result().getEncontradoId().equals("")) {
			throw new DesiredException("Internal Server Error", 500);
		}
		printAndSaveVigiaList(vigiaResponse, listPEPRequest);
		return vigiaResponse;

	}

	public OneFccResponse sendInformationOneFccList(OneFccRequest request) throws DesiredException {

		ResponseEntity<OneFccResponse> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		String headerValue = getOneFccToken();

				try {

			HttpHeaders headers = new HttpHeaders();
			headers.add("x-santander-client-id", "432432432");
			String validateHeaders = sanitizeHeaderValue(headerValue);
			headers.setBearerAuth(validateHeaders);

			headers.setContentType(MediaType.APPLICATION_JSON);


			response = restTemplate.exchange(urlOneFcc + "/onboarding", HttpMethod.POST,
					new HttpEntity<>(request, headers),
					new ParameterizedTypeReference<>() {
					});



		} catch (Exception e) {System.out.println("error oneFCC" + e.getMessage());
			throw new DesiredException(
					e.getMessage(), 500, e);
		}

		return response.getBody();

	}

	public String getOneFccToken() throws DesiredException {

		ResponseEntity<OneFccTokenResponse> responseToken = new ResponseEntity<>(HttpStatus.NO_CONTENT);

		try {

			HttpHeaders headers = new HttpHeaders();
			headers.setBasicAuth(userOneFcc, passOneFcc);

			HttpEntity<String> entity = new HttpEntity<>(headers);
			responseToken = restTemplate.exchange(urlOneFcc + "/login", HttpMethod.GET, entity,
					OneFccTokenResponse.class);

		} catch (Exception e) {

			throw new DesiredException(
					e.getMessage(), 500, e);
		}
 		var resposeBody = responseToken.getBody();
		if(resposeBody == null){
			throw new DesiredException("response is null", 500);
		}

		return resposeBody.getJwtToken();

	}

	public SaveKYCResponse saveInformation(SaveKYCRequest request) throws DesiredException {

          VariablesRiesgos variablesRiesgos = new VariablesRiesgos();
		SaveKYCResponse saveKYCResponse = new SaveKYCResponse();
		PartyBean party = request.getParty();
		new SaveRequestValidator(party);
		String document = party.getPerson().getDocuments().get(0).getDocumentNumber();
		String typeDocument = party.getPerson().getDocuments().get(0).getDocumentTypeCode();
		String penumpe = party.getPartyId();
		boolean PEP = false;
		boolean facta = false;
		boolean CRS = false;

		List<KYCQuestionnairesBean> kYCQuestionnairesList = request.getKnowYourCustomerQuestionnaires();
		for (KYCQuestionnairesBean kycQuestionnairesBean : kYCQuestionnairesList) {
			new SaveRequestValidator(kycQuestionnairesBean);
		}

		AltairResponse altairResponse = null;
		altairResponse = sendInformationAltair(document, typeDocument, penumpe);
		request.getParty().setPartyId(altairResponse.getData().getDatosBasicos().getNumper());
		request.getParty().getPerson().getDocuments().get(0)
				.setDocumentNumber(altairResponse.getData().getDatosBasicos().getNumeroIdentificacion());
		request.getParty().getPerson().getDocuments().get(0)
				.setDocumentTypeCode(altairResponse.getData().getDatosBasicos().getTipoIdentificacion());

		if (penumpe == null) {
			penumpe = request.getParty().getPartyId();
		} else {
			document = request.getParty().getPerson().getDocuments().get(0).getDocumentNumber();
			typeDocument = request.getParty().getPerson().getDocuments().get(0).getDocumentTypeCode();
		}
		FNIVELRIESGOV2Response fNIVELRIESGOV2Response = null;

		FNIVELRIESGOV2 riskLevelRequest = new FNIVELRIESGOV2();
		riskLevelRequest.setNumIde(altairResponse.getData().getDatosBasicos().getNumeroIdentificacion());
		riskLevelRequest.setSisGeoNT(altairResponse.getData().getDatosBasicos().getNacionalidad());
		riskLevelRequest.setSisGeo(altairResponse.getData().getDatosBasicos().getCiudad());
		riskLevelRequest.setEdad(calculateAge(
				LocalDate.parse(altairResponse.getData().getDatosBasicos().getFechaNacimiento()), LocalDate.now()));
		riskLevelRequest.setGenero(altairResponse.getData().getDatosBasicos().getSexo().equals("M") ? "F" : "M");
		riskLevelRequest.setSisGeoTx(" ");
		riskLevelRequest.setCalificacion(" ");
		boolean tinRequiredEU = false;
		String tinEU = null;
		boolean tinRequiredCRS = false;
		String tinCRS = null;
		String codeCRS = null;

		boolean declaration = false;

		List<Questions> questions = request.getKnowYourCustomerQuestionnaires().get(0).getQuestions();

		for (Questions questionsResult : questions) {

			Questions questionResultVigia = new Questions();
			questionResultVigia = questionRepository.getQuestion(questionsResult.getQuestionId());
			if (questionResultVigia != null && questionResultVigia.getVigia() != null) {


				switch (questionResultVigia.getVigia()) {
					case "Actividad":
						String activity = questionRepository
								.getAnswerCode(questionsResult.getAnswers().get(0).getValues().get(0).toString());
						riskLevelRequest
								.setActividad(questionsResult.getAnswers().get(0).getValues().get(0).toString());
						switch (riskLevelRequest.getActividad()) {
							case "4b833e7e-bf7a-48bc-a247-7fca30222418": // empleado
								riskLevelRequest.setOriFon("SAL");
								riskLevelRequest.setCIIU("0010");
								riskLevelRequest.setProfesion("00010");
								riskLevelRequest.setActividad(activity);

								variablesRiesgos.setOcupacion("EMP");
								variablesRiesgos.setIndustria(riskLevelRequest.getCIIU());

								break;
							case "ead4572d-e1fa-4886-8110-385ff27cd39e": // independiente
								riskLevelRequest.setOriFon("AIN");
								riskLevelRequest.setProfesion("00140");
								riskLevelRequest.setActividad(activity);

								variablesRiesgos.setOcupacion("IND");

								break;
							case "b3db406b-312b-442b-ad9d-afb022b6b05f": // rentista de capital
								riskLevelRequest.setOriFon("ARR");
								riskLevelRequest.setCIIU("0090");
								riskLevelRequest.setProfesion("00140");
								riskLevelRequest.setActividad(activity);

								variablesRiesgos.setOcupacion("REN");
								variablesRiesgos.setIndustria(riskLevelRequest.getCIIU());
								break;

							case "2175953a-d564-45e8-812a-261d1b5eb656": // pensionado
								riskLevelRequest.setOriFon("PEN");
								riskLevelRequest.setCIIU("0020");
								riskLevelRequest.setProfesion("00140");
								riskLevelRequest.setActividad(activity);

								variablesRiesgos.setOcupacion("PEN");
								variablesRiesgos.setIndustria(riskLevelRequest.getCIIU());
								break;

							case "d8cd8bbe-ecde-4b67-bc54-eb9ac762e1a9": // no
								riskLevelRequest.setOriFon("DOH");
								riskLevelRequest.setProfesion("00140");
								riskLevelRequest.setActividad(activity);
								variablesRiesgos.setOcupacion("IND");

								break;
							case "6aa00d6b-c063-448a-987c-077eea90b11d":
								riskLevelRequest.setOriFon("EST");
								riskLevelRequest.setCIIU("0081");
								riskLevelRequest.setProfesion("00140");
								riskLevelRequest.setActividad(activity);

								variablesRiesgos.setOcupacion("EST");
								variablesRiesgos.setIndustria(riskLevelRequest.getCIIU());
								break;
							case "0b09781e-35e0-48ad-a7b3-c11aa83d83d4":
								riskLevelRequest.setOriFon("AMA");
								riskLevelRequest.setCIIU("0082");
								riskLevelRequest.setProfesion("00140");
								riskLevelRequest.setActividad(activity);

								variablesRiesgos.setOcupacion("AMA");
								variablesRiesgos.setIndustria(riskLevelRequest.getCIIU());

								break;
							case "4403844d-1dfc-47b5-8de6-67158601c1fa":
								riskLevelRequest.setOriFon("DES");
								riskLevelRequest.setCIIU("0082");
								riskLevelRequest.setProfesion("00140");
								riskLevelRequest.setActividad(activity);

								variablesRiesgos.setOcupacion("DES");
								variablesRiesgos.setIndustria(riskLevelRequest.getCIIU());
								break;
							default:
								throw new DesiredException(
										"questionId: b880381e-e6ec-4c7f-9935-fc1c3f4a25fa - not valid", 404);
						}
						break;
					case "CIIU":
//						String risk = questionRepository
//								.getCIUU(questionsResult.getAnswers().get(0).getValues().get(0).toString());

						String name = questionRepository
								.getAnswerCode(questionsResult.getAnswers().get(0).getValues().get(0).toString());


//						if (risk != null
//								&& (risk.equals("Alto") || risk.equals("Extremo") || risk.equals("PROHIBIDA"))) {
//							if (risk.equals("PROHIBIDA")) {
//								risk = "Alto";
//							}
//							fNIVELRIESGOV2Response = new FNIVELRIESGOV2Response();
//							Resultado fNIVELRIESGOV2Result = new Resultado();
//							fNIVELRIESGOV2Result.setNivelRiesgo(risk);
//							fNIVELRIESGOV2Response.setFNIVELRIESGOV2Result(fNIVELRIESGOV2Result);
//							saveKYCResponse = orderResponseVigia(fNIVELRIESGOV2Response,
//									request.getKnowYourCustomerQuestionnaires().get(0).getQuestionnaireId());
//
//						}

						riskLevelRequest.setCIIU(name);

						variablesRiesgos.setIndustria(riskLevelRequest.getCIIU());
						getNivelRiesgo(request, altairResponse, saveKYCResponse, variablesRiesgos);

						break;
					case "Ingresos":

						String income = questionsResult.getAnswers().get(0).getValues().get(0).toString();

						if (income.matches("^[0-9]*$") == false) {
							throw new DesiredException(
									"questionId: 7ba2118b-9e82-4e73-8f9f-61574ca33a13 - must be numeric", 400);
						}
						riskLevelRequest.setIngresos(income);

						break;
					case "Egresos":
						String expenses = questionsResult.getAnswers().get(0).getValues().get(0).toString();

						if (expenses.matches("^[0-9]*$") == false) {
							throw new DesiredException(
									"questionId: dd3c61c3-f5f6-487d-bf6a-4a3bb91a1148 - must be numeric", 400);
						}
						riskLevelRequest.setEgresos(questionsResult.getAnswers().get(0).getValues().get(0).toString());

						break;
					case "Pasivo":
						String pasive = questionsResult.getAnswers().get(0).getValues().get(0).toString();

						if (pasive.matches("^[0-9]*$") == false) {
							throw new DesiredException(
									"questionId: 15296a49-a628-4f7a-bdb7-10988ec85d83 - must be numeric", 400);
						}
						riskLevelRequest.setPasivo(questionsResult.getAnswers().get(0).getValues().get(0).toString());

						break;
					case "Activo":
						String assets = questionsResult.getAnswers().get(0).getValues().get(0).toString();

						if (assets.matches("^[0-9]*$") == false) {
							throw new DesiredException(
									"questionId: 9577c42b-8eff-455c-bf65-ec8bc782808e - must be numeric", 400);
						}
						riskLevelRequest.setActivo(questionsResult.getAnswers().get(0).getValues().get(0).toString());

						break;
					case "PEP":
						switch (questionsResult.getAnswers().get(0).getValues().get(0).toString()) {
							case "235386b2-4f0c-4cea-966d-c4a75ddedf75":
								riskLevelRequest.setTipPEP("");
								break;
							case "f12ec33d-58d7-4b28-a79b-21b0f3aeae90":
								riskLevelRequest.setTipPEP("001");
								break;
							case "21f145e0-2625-43f5-b4e6-f981669fd3ca":
								riskLevelRequest.setTipPEP("003");
								break;
							case "5522c64f-5d88-47dc-bbb0-ff806892b7ce":
								riskLevelRequest.setTipPEP("005");
								break;
							case "80301bf3-ef67-43f7-9776-d29497b1c985":
								riskLevelRequest.setTipPEP("003");
								break;
							default:
								throw new DesiredException(
										"questionId: 423a210d-4295-44d6-8d66-3e02221a6212 - not valid", 400);
						}
						break;

					case "EEUU":
						switch (questionsResult.getAnswers().get(0).getValues().get(0).toString()) {
							case "e3c6bc14-3349-4fcb-bdfc-dfc45519c484":
								break;
							case "2035b327-7ebf-40a0-ac52-514c0f913784":
								facta = true;
								break;
							case "e8141fc7-0835-4d68-b8ed-6a8ac7123490":
								facta = true;
								break;
							case "f97db556-0617-47d0-b4e3-a9027200830f":
								facta = true;
								break;
							default:
								throw new DesiredException(
										"questionId: 5b4157f3-f7ab-4468-8661-5f8eccc6f038 - not valid", 400);
						}
						break;

					case "TINEEUU":
						switch (questionsResult.getAnswers().get(0).getValues().get(0).toString()) {
							case "db2878b7-ece8-4f9f-8f6a-2f5718bd3040":
								tinRequiredEU = true;
								break;
							case "2c86b759-31e8-4589-adc9-a2c293d1d37b":
								tinRequiredEU = false;
								break;
							default:
								throw new DesiredException(
										"questionId: 9b7a6f50-c226-4f93-a341-23070c999a5d - not valid", 400);
						}
						break;
					case "NUMTINEU":
						tinEU = questionsResult.getAnswers().get(0).getValues().get(0).toString();

						break;
					case "TINCRF":
						switch (questionsResult.getAnswers().get(0).getValues().get(0).toString()) {
							case "356ccfff-9e12-4adf-b665-facc8ee69301":
								tinRequiredCRS = false;
								break;
							case "4d980eab-dff0-4739-acc4-bbfeb6c7b712":
								tinRequiredCRS = true;
								break;
							default:
								throw new DesiredException(
										"questionId: efd07624-79ea-4bd0-8b09-fcaabd8fb468 - not valid", 400);

						}
						break;
					case "NUMTINCRF":
						tinCRS = questionsResult.getAnswers().get(0).getValues().get(0).toString();

						break;
					case "CODECRF":
						codeCRS = questionRepository
								.getAnswerCode(questionsResult.getAnswers().get(0).getValues().get(0).toString());

						break;
					case "PEP006":
						if (riskLevelRequest.getTipPEP() != null && !riskLevelRequest.getTipPEP().equals("")) {
							switch (questionsResult.getAnswers().get(0).getValues().get(0).toString()) {
								case "38bc9751-cb95-4baa-8952-86dc64e0e06e":
									riskLevelRequest.setTipPEP("006");
									CRS = true;
									break;
								case "76f55e2a-419f-4a17-9966-5f78208684a9":
									break;
								default:
									throw new DesiredException(
											"questionId: a0b5e918-f149-476e-a4aa-ab95384bc3aa - not valid", 400);
							}
						} else {
							switch (questionsResult.getAnswers().get(0).getValues().get(0).toString()) {
								case "38bc9751-cb95-4baa-8952-86dc64e0e06e":
									CRS = true;
									break;
								case "76f55e2a-419f-4a17-9966-5f78208684a9":
									break;
								default:
									throw new DesiredException(
											"questionId: a0b5e918-f149-476e-a4aa-ab95384bc3aa - not valid", 400);
							}
						}
						break;

					case "Declaracion":
						switch (questionsResult.getAnswers().get(0).getValues().get(0).toString()) {
							case "9e8103fb-f7b4-47d2-95b3-036536a15893":
								declaration = true;
								break;
							case "4feb71ba-0c1b-45e9-9995-172beea13404":
								declaration = false;
								throw new DesiredException("Debe aceptar condiciones para continuar", 400);
							default:
								throw new DesiredException(
										"questionId: 3accdaad-8a9d-4263-9597-943be9f315e4 - not valid", 400);
						}
						break;
					default:
						break;
				}
			}

		}


		if (!declaration) {
			throw new DesiredException("Debe aceptar condiciones para continuar", 400);
		}


		riskLevelRequest.setOtrosIngresos("0");
		long patrimonio = Long.parseLong(riskLevelRequest.getActivo()) - Long.parseLong(riskLevelRequest.getPasivo());
		riskLevelRequest.setPatrimonio("" + patrimonio);

		String fullNameAltair = "";

		if (altairResponse.getData().getDatosBasicos().getNombre() != null) {
			if (altairResponse.getData().getDatosBasicos().getPrimerApellido() != null) {
				if (altairResponse.getData().getDatosBasicos().getSegundoApellido() != null) {
					fullNameAltair = altairResponse.getData().getDatosBasicos().getNombre() + " "
							+ altairResponse.getData().getDatosBasicos().getPrimerApellido() + " "
							+ altairResponse.getData().getDatosBasicos().getSegundoApellido();
				} else {
					fullNameAltair = altairResponse.getData().getDatosBasicos().getNombre() + " "
							+ altairResponse.getData().getDatosBasicos().getPrimerApellido();
				}
			}
		} else {
			porcentageVigia = "";
		}

		FVERIFICATERCEROV3 listPEPVigia = new FVERIFICATERCEROV3();

		listPEPVigia.setPeOrigen("CDTKYC");
		listPEPVigia.setPeDatoid(riskLevelRequest.getNumIde());
		listPEPVigia.setPePorcentaje(porcentageVigia);
		listPEPVigia.setPeDatonombre(fullNameAltair);

		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
				.setPrettyPrinting().create();
		String prettyResponse = gson.toJson(altairResponse);

		OneFccRequest requestOneFcc = OneFccRequest.builder().idDocument(riskLevelRequest.getNumIde())
				.name(fullNameAltair).documentType(altairResponse.getData().getDatosBasicos().getTipoIdentificacion())
				.doBOrEntityCreationDate(altairResponse.getData().getDatosBasicos().getFechaNacimiento())
				.country("CO").countryType("Country of Citizenship").personType("I")
				.firstSurname(altairResponse.getData().getDatosBasicos().getPrimerApellido())
				.requester("CDT")
				.build();

		OneFccResponse response = sendInformationOneFccList(requestOneFcc);

		if (response != null
				&& response.getStatus().equals("KO")
				&& riskLevelRequest.getTipPEP().equals("")) {

			riskLevelRequest.setTipPEP("001");
		}


		if (riskLevelRequest.getActividad() == null) {
			throw new DesiredException("La ocupaci\u00f3n es obligatoria.", 400);
		}

		if (riskLevelRequest.getCIIU() == null) {
			throw new DesiredException("El CIUU es obligatorio.", 400);
		}

		if (riskLevelRequest.getIngresos() == null) {
			throw new DesiredException("Los ingresos son obligatorios.", 400);
		}

		if (riskLevelRequest.getEgresos() == null) {
			throw new DesiredException("Los egresos son obligatorios.", 400);
		}

		if (riskLevelRequest.getActivo() == null) {
			throw new DesiredException("El activo es obligatorio.", 400);
		}

		if (riskLevelRequest.getPasivo() == null) {
			throw new DesiredException("El pasivo es obligatorio.", 400);
		}

		if (riskLevelRequest.getTipPEP() == null) {
			throw new DesiredException("El tipo de PEP es obligatorio.", 400);
		}


		if (fNIVELRIESGOV2Response == null) {

			/*fNIVELRIESGOV2Response = sendInformationVigia(riskLevelRequest);
			saveKYCResponse = orderResponseVigia(fNIVELRIESGOV2Response,
					request.getKnowYourCustomerQuestionnaires().get(0).getQuestionnaireId());*/
			getNivelRiesgo(request, altairResponse, saveKYCResponse, variablesRiesgos);
		}


		if (!riskLevelRequest.getTipPEP().equals("") || facta || CRS) {
			setterRiskBean(saveKYCResponse, "Alto");
		}


		if (!riskLevelRequest.getTipPEP().equals("")) {
			PEP = true;
		}



		printAndSave(saveKYCResponse, request, document, typeDocument, penumpe, facta, PEP,
				CRS, riskLevelRequest.getActividad(), riskLevelRequest.getProfesion(),
				riskLevelRequest.getCIIU(), riskLevelRequest.getIngresos(), riskLevelRequest.getEgresos(),
				riskLevelRequest.getPasivo(), riskLevelRequest.getActivo(), riskLevelRequest.getPatrimonio(),
				tinRequiredEU, tinEU, codeCRS, tinRequiredCRS, tinCRS);

		return saveKYCResponse;

	}

	private void printAndSave(SaveKYCResponse saveKYCResponse, SaveKYCRequest request, String document,
			String typeDocument, String penumpe, boolean facta, boolean PEP, boolean CRS, String activity,
			String profession, String CIIU, String incomes, String expenses, String passives, String assets,
			String patrimony, boolean tinRequiredEU, String tinEU, String codeCRS, boolean tinRequiredCRS,
			String tinCRS) throws DesiredException {
		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
				.setPrettyPrinting().create();
		String prettyResponse = gson.toJson(saveKYCResponse);
		String prettyRequest = gson.toJson(request);

		logger.info("Request : " + prettyRequest.toString() + " Response: " + prettyResponse.toString());


		try {
			questionRepository.addForm(saveKYCResponse.getKycResolutionId(), document, typeDocument,
					LocalDate.now().toString(), LocalDate.now().plusMonths(6).toString(), prettyRequest, prettyResponse,
					penumpe, facta, PEP, CRS, activity, profession, CIIU, incomes, expenses, passives, assets,
					patrimony, tinRequiredEU, tinEU, codeCRS, tinRequiredCRS, tinCRS);
		} catch (DesiredException e) {
			logger.error(" KYC form could duplicate registered in database with the identifier '{}' : '{}' .",
					saveKYCResponse.getKycResolutionId(), e.getMessage());
			throw e;
		}
	}

	private void printAndSaveAltair(AltairResponse altairResponse, AltairRequest altairRequest)
			throws DesiredException {
		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
				.setPrettyPrinting().create();
		String prettyResponse = gson.toJson(altairResponse);
		String prettyRequest = gson.toJson(altairRequest);


		logger.info("ALTAIR : Request : " + prettyRequest + " Response: " + prettyResponse);

	}

	private void printAndSaveVigiaList(FVERIFICATERCEROV3Response vigiaResponse, FVERIFICATERCEROV3 vigiaRequest)
			throws DesiredException {
		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
				.setPrettyPrinting().create();
		String prettyResponse = gson.toJson(vigiaResponse);
		String prettyRequest = gson.toJson(vigiaRequest);
		logger.info("Vigia : Request : " + prettyRequest + " Response: " + prettyResponse);

	}

	private void printAndSaveVigia(FNIVELRIESGOV2Response vigiaResponse, FNIVELRIESGOV2 vigiaRequest)
			throws DesiredException {
		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
				.setPrettyPrinting().create();
		String prettyResponse = gson.toJson(vigiaResponse);
		String prettyRequest = gson.toJson(vigiaRequest);


		logger.info("Vigia : Request : " + prettyRequest + " Response: " + prettyResponse);

	}

	private SaveKYCResponse orderResponseVigia(FNIVELRIESGOV2Response fNIVELRIESGOV2Response, String questionariId) {
		SaveKYCResponse saveKYCResponse = new SaveKYCResponse();

		RiskBean riskBean = new RiskBean();
		ScoreBean scoreBean = new ScoreBean();
		ValidityPeriodBean validityPeriodBean = new ValidityPeriodBean();
		scoreBean.setValidityPeriod(validityPeriodBean);
		riskBean.setScore(scoreBean);
		saveKYCResponse.setRisk(riskBean);

		saveKYCResponse.getRisk().getScore().setCode(fNIVELRIESGOV2Response.getFNIVELRIESGOV2Result().getNivelRiesgo());
		saveKYCResponse.setKycResolutionId(questionariId);
		saveKYCResponse.setLocalReference(saveKYCResponse.getKycResolutionId());
		saveKYCResponse.getRisk().getScore().getValidityPeriod().setStartDate(LocalDate.now().toString());
		int months = Integer.parseInt(this.validMonth);
		saveKYCResponse.getRisk().getScore().getValidityPeriod()
				.setEndDate(LocalDate.now().plusMonths(months).toString());
		return saveKYCResponse;
	}

	public String calculateAge(
			LocalDate birthDate,
			LocalDate currentDate) {
		return Period.between(birthDate, currentDate).getYears() + "";
	}

	 public SaveKYCResponse getNivelRiesgo(SaveKYCRequest request, AltairResponse altairResponse, SaveKYCResponse saveKYCResponse, VariablesRiesgos variablesRiesgos) {





		 PersonNaturalModel pn = new PersonNaturalModel();
		 pn.setNumeroDocumento(request.getParty().getPerson().getDocuments().get(0).getDocumentNumber());
		 pn.setTipoDocumento(request.getParty().getPerson().getDocuments().get(0).getDocumentTypeCode());
		 pn.setNombre(altairResponse.getData().getDatosBasicos().getNombre() +" "+ altairResponse.getData().getDatosBasicos().getPrimerApellido() );
		 pn.setPrimerNombre(altairResponse.getData().getDatosBasicos().getNombre());
		 pn.setSegundoNombre("");
		 pn.setPrimerApellido(altairResponse.getData().getDatosBasicos().getPrimerApellido());
		 pn.setSegundoApellido(altairResponse.getData().getDatosBasicos().getSegundoApellido());
		 pn.setCorreo(altairResponse.getData().getDatosBasicos().getEmail());
		 pn.setOcupacion(variablesRiesgos.getOcupacion());
		 pn.setIndustria(extraerUltimos(variablesRiesgos.getIndustria(), 4));

		 pn.setProducto("CDT");
		 pn.setPais(altairResponse.getData().getDatosBasicos().getPaisDireccion());
		 pn.setCiudad(altairResponse.getData().getDatosBasicos().getCiudad());
		 pn.setCanal("D");

		 ResponseModel res = kycServiceImp.getRiesgoPN(pn);


		 if(request.getKnowYourCustomerQuestionnaires().isEmpty()){
			 logger.info("Lista vacia");
		 }
		 saveKYCResponse.setKycResolutionId(request.getKnowYourCustomerQuestionnaires().get(0).getQuestionnaireId());
		 saveKYCResponse.setLocalReference(saveKYCResponse.getKycResolutionId());
		 setterRiskBean(saveKYCResponse , res.getResultado());
		 variablesRiesgos.setOcupacion("");
		 variablesRiesgos.setIndustria("");

		 return saveKYCResponse;
	}

	public void setterRiskBean (SaveKYCResponse saveKYCResponse, String nivel){
		ValidityPeriodBean validityPeriodBean = new  ValidityPeriodBean();
		int months = Integer.parseInt(this.validMonth);
		validityPeriodBean.setStartDate(LocalDate.now().toString());
		validityPeriodBean.setEndDate(LocalDate.now().plusMonths(months).toString());

		ScoreBean scoreBean = new ScoreBean();
		scoreBean.setCode(nivel);
		scoreBean.setValidityPeriod(validityPeriodBean);

		RiskBean riskBean = new RiskBean();
		riskBean.setScore(scoreBean);

		saveKYCResponse.setRisk(riskBean);


	}

	public String extraerUltimos (String texto, int value){
		if ( texto == null || texto.length() < 4 ){
			return texto;
		}
		return texto.substring(texto.length() - value);

	}

	public String getOcupacion(String ocu){

	return 	switch (ocu){
			case "FE", "FF", "FG", "FH", "FI", "F2", "F4" -> "IND";
			case "FC" -> "REN";
			case "F1", "F3" -> "EMP";
			case "FD", "F8" -> "PEN";
			case "FZ" -> "FZ";
			default -> "0";

		};

	}

private String sanitizeHeaderValue(String value) throws DesiredException {
		if(value == null || value.isBlank()){
			throw new DesiredException("Valor invalido o nulo", 500);
		}

		String sanitized = value.replaceAll("[\\r\\n]", "");


	if( sanitized.isBlank()){
		throw new DesiredException("Valor invalido o nulo", 500);
	}


		return sanitized;
}


}
