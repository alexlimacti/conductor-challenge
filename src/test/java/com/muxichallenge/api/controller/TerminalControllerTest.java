package com.muxichallenge.api.controller;

import com.muxichallenge.api.dto.TerminalDTO;
import com.muxichallenge.domain.service.TerminalService;
import com.muxichallenge.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class TerminalControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TerminalService terminalService;

    @Autowired
    protected Flyway flyway;


    private String jsonTerminalCorreto;
    private String jsonTerminalIncorreto;
    private String jsonTerminalIncorretoUpdate;

    private TerminalDTO terminalDTO;

    private static final String DADOS_INVALIDOS_PROBLEM_TITLE = "Invalid data";

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/api/v1/terminal";

        jsonTerminalCorreto = ResourceUtils.getContentFromResource(
                "/json/correto/terminal-correto-completo.json");

        jsonTerminalIncorreto = ResourceUtils.getContentFromResource(
                "/json/incorreto/terminal-incorreto.json");

        jsonTerminalIncorretoUpdate = ResourceUtils.getContentFromResource(
                "/json/incorreto/terminal-incorreto-update.json");

        flyway.clean();
        flyway.migrate();

        prepararDados();

    }

    @Test
    public void deveRetornarStatus200_QuandoConsultarTerminais(){
        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveRetornarStatus200_QuandoConsultarTerminalPorLogic(){
        given()
                .pathParam("logic", terminalDTO.getLogic())
                .accept(ContentType.JSON)
                .when()
                .get("/{logic}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("ptid", equalTo(terminalDTO.getPtid()));
    }

    @Test
    public void deveRetornarStatus200_QuandoSalvarTerminal(){
        given()
                .body(jsonTerminalCorreto)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deveRetornarStatus400_QuandoCampoObrigatorioVazioOuNulo(){
        given()
                .body(jsonTerminalIncorreto)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("title", equalTo(DADOS_INVALIDOS_PROBLEM_TITLE));;
    }

    private void prepararDados() {
        terminalDTO = new TerminalDTO();
        terminalDTO.setLogic(1234567);
        terminalDTO.setSerial("123");
        terminalDTO.setModel("PWWIN");
        terminalDTO.setSam(0);
        terminalDTO.setPtid("F04A2E4088B");
        terminalDTO.setPlat(4);
        terminalDTO.setVersion("8.00b3");
        terminalDTO.setMxr(0);
        terminalDTO.setMxf(16777216);
        terminalDTO.setVerfm("PWWIN");
        terminalService.save(terminalDTO);

        terminalDTO = new TerminalDTO();
        terminalDTO.setLogic(7654321);
        terminalDTO.setSerial("321");
        terminalDTO.setModel("AWWTZ");
        terminalDTO.setSam(0);
        terminalDTO.setPtid("F0345SBAR");
        terminalDTO.setPlat(4);
        terminalDTO.setVersion("8.00b3");
        terminalDTO.setMxr(0);
        terminalDTO.setMxf(98475638);
        terminalDTO.setVerfm("AWWTZ");
        terminalService.save(terminalDTO);
    }

}
