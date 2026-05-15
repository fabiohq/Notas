package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.infrastructure.adapters.input.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.application.ports.input.UserManagementInputPort;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.application.usecases.UserManagementUseCase;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.ExampleUserRecord;
import com.santander.darwin.core.exceptions.NotFoundDarwinException;
import com.santander.darwin.core.exceptions.dto.GluonErrorModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * Example controller for Darwin Database-based applications
 */
@RestController
@RequestMapping("/bnc-bsn049-mswatchliscreen/databases")
@Slf4j
class DatabaseController {

    private final ObjectMapper objectMapper;
	private final UserManagementUseCase userManagementUseCase;

	/**
	 * Injection of the input port for uses in several controller methods
	 * @param userManagementInputPort to access domain layer logic
	 */
	public DatabaseController(UserManagementInputPort userManagementInputPort,ObjectMapper objectMapper) {
		this.userManagementUseCase = userManagementInputPort;
		this.objectMapper = objectMapper;
	}

	/**
	 * Basic method to control the "/user" endpoint that, when receives a request
	 * with a name and an email will call the Database Service to save a new User,
	 * using a POST HTTP method.
	 * @param name to save in the Database @RequestParam("name") String name
	 * @param mail to save in the Database @RequestParam("mail") String mail
	 * @return UserRecord
	 */
	@Operation(summary = "Create a new user", description = "Create a new user with the provided name and email")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "User created successfully", content = @Content(schema = @Schema(implementation = ExampleUserRecord.class))),
			@ApiResponse(responseCode = "415", description = "Unsupported Media Type", content = @Content(array = @ArraySchema(schema = @Schema(implementation = GluonErrorModel.GluonErrorModelBuilder.GluonError.class)))) })
	@PostMapping("/user")
	@ResponseStatus(HttpStatus.CREATED)
	public ExampleUserRecord createUser(@RequestParam("name") String name, @RequestParam("mail") String mail) {

		log.info("*** INIT (POST) /bnc-bsn049-mswatchliscreen/databases/user?name={}&mail={} >>> ",name,mail);
		
		ExampleUserRecord response = userManagementUseCase.createUser(name, mail);
		
		try {
			String jsonResponse = objectMapper.writeValueAsString(response);
			StringBuilder sbr = new StringBuilder();
			sbr.append(" Response=").append(jsonResponse);
			log.info("*** FIN (POST) /bnc-bsn049-mswatchliscreen/databases/user?name={}&mail={} {}>>> ",name,mail,sbr.toString());
		} catch (Exception e) {
			log.error("Error serializando response");
		}
		return response;
	}

	/**
	 * Basic method to control the "/user/{id}" endpoint that retrieves an User with
	 * a given id requested, using a GET HTTP method.
	 * @param id to retrieve @PathVariable("id") Long id
	 * @return UserRecord
	 */
	@Operation(summary = "Create a new user", description = "Create a new user with the provided name and email")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User retrieved successfully", content = @Content(schema = @Schema(implementation = ExampleUserRecord.class))),
			@ApiResponse(responseCode = "406", description = "Not Acceptable", content = @Content(array = @ArraySchema(schema = @Schema(implementation = GluonErrorModel.GluonErrorModelBuilder.GluonError.class)))) })
	@GetMapping("/user/{id}")
	public ExampleUserRecord retrieveUser(@PathVariable("id") Long id) {
		
		log.info("*** INIT (GET) /bnc-bsn049-mswatchliscreen/databases/user/{} >>> ",id);
		
		ExampleUserRecord response = userManagementUseCase.findById(id).orElseThrow(() -> new NotFoundDarwinException("User not found"));
		
		try {
			String jsonResponse = objectMapper.writeValueAsString(response);
			StringBuilder sbr = new StringBuilder();
			sbr.append(" Response=").append(jsonResponse);
			log.info("*** FIN (GET) /bnc-bsn049-mswatchliscreen/databases/user/{} {} >>> ",id,sbr.toString());
		} catch (Exception e) {
			log.error("Error serializando response");
		}
		
		return response;
	}
}

package com.santander.bnc.bsn049.bncbsn049mswatchliscreen.infrastructure.adapters.input.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.entity.WatchlistScreeningRequest;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.exception.ServiceException;
import com.santander.bnc.bsn049.bncbsn049mswatchliscreen.domain.service.WatchlistScreeningService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/watchlist_screening")
@Slf4j
@Tag(name = "watchlist_screening", description = "Watchlist Screening")
@RequiredArgsConstructor
public class WatchlistScreeningControllers {

	@Autowired
	WatchlistScreeningService termDepositService;

	@PostMapping("/validate_status")
	public Object validateStatus(@RequestHeader("Authorization") String authorization,
			@RequestHeader("x-santander-client-id") String clientId,
			@Valid @RequestBody WatchlistScreeningRequest request) throws ServiceException {

		return termDepositService.validateStatus(request);
	}

}


