package com.santander.bnc.bsn049.bncbsn049savekycservice.domain.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.bean.DesiredException;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.bean.Questions;

@Repository
public class QuestionRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(QuestionRepository.class);

    /**
     * Obtiene los registros de informaci\u00F3n asociados a los diferentes
     * t\u00E9rminos o nomenclaturas de las direcciones.
     * 
     * @return Listado de t\u00E9rminos o nomenclaturas de las direcdiones.
     * @see AddressTermsBean
     */
    public Questions getQuestion(String id) {
        Questions questionBeanList = null;

        String sql = "SELECT * FROM CDTKYC.QUESTION WHERE KEY = ?";

        try {
            questionBeanList = jdbcTemplate.queryForObject(sql, new QuestionRowMapper(), id);
        } catch (DataAccessException e) {
            logger.error("Data was not recorded. Error executing query", e);
            return questionBeanList;
        } catch (Exception e) {
            logger.error("Data was not recorded.", e);
            return questionBeanList;
        }
        return questionBeanList;

    }

    public static class QuestionRowMapper implements RowMapper<Questions> {

        public Questions mapRow(ResultSet resultSet, int row) throws SQLException {
            Questions questionBean = new Questions();
            questionBean.setQuestionId(resultSet.getString("key"));
            questionBean.setDescription(resultSet.getString("name"));
            questionBean.setVigia(resultSet.getString("vigia"));

            return questionBean;
        }
    }

    public String getCIUU(String name) throws DesiredException {
        String questionBeanList = null;

        String sql = "SELECT RISK FROM CDTKYC.ANSWER WHERE KEY = ?";

        try {
            questionBeanList = jdbcTemplate.queryForObject(sql, new AnswerRowMapper(), name);
        } catch (DataAccessException e) {
            logger.error("Error table RISK. Error executing query", e);
            throw new DesiredException("questionId: 2d747251-8d68-4a59-82bd-7838ec1485b3 - not valid", 404, e);
        } catch (Exception e) {
            logger.error("Error table RISK.", e);
            throw new DesiredException("questionId: 2d747251-8d68-4a59-82bd-7838ec1485b3 - not valid", 404, e);
        }
        return questionBeanList;

    }

    public static class AnswerRowMapper implements RowMapper<String> {

        public String mapRow(ResultSet resultSet, int row) throws SQLException {
            String risk = resultSet.getString("risk");

            return risk;
        }
    }

    public String getAnswerCode(String key) throws DesiredException {
        String questionBeanList = null;

        String sql = "SELECT NAME FROM CDTKYC.ANSWER WHERE KEY = ?";


        try {
            questionBeanList = jdbcTemplate.queryForObject(sql, new AnswerCodeRowMapper(), key);
        } catch (DataAccessException e) {
           logger.error("Error getting answer code Error executing query", e);
            throw new DesiredException("questionId: " + key, 404, e);
        } catch (Exception e) {
            logger.error("Error getting answer code.", e);
            throw new DesiredException("questionId: " + key, 404, e);
        }
        return questionBeanList;

    }

    public static class AnswerCodeRowMapper implements RowMapper<String> {

        public String mapRow(ResultSet resultSet, int row) throws SQLException {
            String name = resultSet.getString("name");

            return name;
        }
    }

    public void addForm(String key, String document, String documentType, String dateStart, String dateEnd,
            String request, String response, String penumpe, boolean facta, boolean PEP, boolean CRS, String activity,
            String profession, String cIIU, String incomes, String expenses, String passives, String assets,
            String patrimony, boolean tinRequiredEU, String tinEU, String codeCRS, boolean tinRequiredCRS,
            String tinCRS) throws DesiredException {

        try {
            Date date_Start = new SimpleDateFormat("yyyy-MM-dd").parse(dateStart);
            dateStart = new SimpleDateFormat("dd/MM/yyyy").format(date_Start);
            logger.info("date_Start: " + date_Start + "dateStart: " + dateStart);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            logger.error("could not be posible parse dates '{}':'{}' .",
                    dateStart, e);
        }

        try {
            Date date_End = new SimpleDateFormat("yyyy-mm-dd").parse(dateEnd);
            dateEnd = new SimpleDateFormat("dd/mm/yyyy").format(date_End);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            logger.error("could not be posible parse dates '{}':'{}' .",
                    dateEnd, e);
        }

        String sql = "INSERT INTO CDTKYC.KYC_FORM (KEY, DOCUMENT,DOCUMENT_TYPE,START_DATE,END_DATE,REQUEST,RESPONSE,PENUMPE,FATCA,PEP,CRS,ACTIVITY,"
                + "PROFESSION,CIIU,INCOMES,EXPENSES,PASSIVES,ASSETS,PATRIMONY,TIN_EU,NUMBER_TIN_EU,CODE_CRF,TIN_CRF,NUMBER_TIN_CRF) "
                + "VALUES(?, ?, ?, to_date(?,'dd/mm/yyyy'), to_date(?,'dd/mm/yyyy'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            jdbcTemplate.update(sql, key, document, documentType, dateStart, dateEnd, request, response, penumpe, facta,
                    PEP, CRS, activity, profession, cIIU, incomes,
                    expenses, passives, assets, patrimony, tinRequiredEU, tinEU, codeCRS, tinRequiredCRS, tinCRS);
            logger.debug(
                    "The registration KYC form '{}' was successful.",
                    key);
        } catch (DuplicateKeyException e) {
            logger.error(" KYC form could duplicate registered in database with the identifier '{}': '{}'.",
                    key, e.getMessage(), e);
            throw new DesiredException("'knowYourCustomerQuestionnaires.questionnaireId': duplicado", 400, e);
        } catch (Exception exception) {
            logger.error(" KYC form could not be registered in database with the identifier '{}': '{}' .",
                    key, exception.getMessage(), exception);
            throw new DesiredException(
                    "'knowYourCustomerQuestionnaires.questionnaireId': could not be registered in database with the identifier  "
                            + key,
                    400, exception);
        }
    }
}



package com.santander.bnc.bsn049.bncbsn049savekycservice.domain.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.bean.DesiredException;
import com.santander.bnc.bsn049.bncbsn049savekycservice.domain.bean.FormKYCBean;

@Repository
@RequestMapping("/v2/know_your_customer")
public class FormKYCRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(FormKYCRepository.class);

    /**
     * Obtiene los registros de informaci\u00F3n asociados a los diferentes
     * t\u00E9rminos o nomenclaturas de las direcciones.
     * 
     * @return Listado de t\u00E9rminos o nomenclaturas de las direcdiones.
     * @throws DesiredException
     * @see AddressTermsBean
     */
    public FormKYCBean getFormPenumpe(String key) throws DesiredException {
        FormKYCBean questionBeanList = null;

        String sql = "SELECT * FROM \r\n"
                + "(SELECT * FROM CDTKYC.KYC_FORM WHERE PENUMPE = ? ORDER BY TO_DATE( START_DATE, 'dd/mm/yyyy') DESC)\r\n"
                + " WHERE ROWNUM = 1";

        try {
            questionBeanList = jdbcTemplate.queryForObject(sql, new AnswerRowMapper(), key);

        } catch (BadSqlGrammarException e) {
            logger.error("KYC_FORM data was not found.", e);
            throw new DesiredException("'party_id': not found", 404, e);
        } catch (Exception e) {
            logger.error("KYC_FORM data was not found.", e);
            throw new DesiredException("KYC_FORM data not found", 404, e);
        }
        return questionBeanList;

    }

    public FormKYCBean getFormKYC(String key) throws DesiredException {
        FormKYCBean questionBeanList = null;

        String sql = "SELECT * FROM CDTKYC.KYC_FORM WHERE KEY = ?";

        try {
            questionBeanList = jdbcTemplate.queryForObject(sql, new AnswerRowMapper(), key);

        } catch (BadSqlGrammarException e) {
            logger.error("KYC_FORM data was not found.", e);
            throw new DesiredException("'kyc_resolution_id': not found", 404, e);
        } catch (Exception e) {
            logger.error("KYC_FORM data was not found.", e);
            throw new DesiredException("KYC_FORM data not found", 404,e);
        }
        return questionBeanList;

    }

    public static class AnswerRowMapper implements RowMapper<FormKYCBean> {

        public FormKYCBean mapRow(ResultSet resultSet, int row) throws SQLException {
            FormKYCBean formKYCBean = new FormKYCBean();
            formKYCBean.setRequest(resultSet.getString("request"));
            formKYCBean.setResponse(resultSet.getString("response"));
            formKYCBean.setKey(resultSet.getString("key"));

            return formKYCBean;
        }
    }

}


