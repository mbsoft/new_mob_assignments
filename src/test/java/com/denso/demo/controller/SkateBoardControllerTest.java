package com.denso.demo.controller;

import com.denso.demo.service.SkateBoardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SkateBoardControllerTest {

    @Mock
    private SkateBoardService skateBoardService;

    @Test
    public void getSkateBoards_givenAPIPath_return200AsStatusCode() {

        given()
                .standaloneSetup(new SkateBoardController(skateBoardService))
                .when()
                .get("/skate-board")
                .then()
                .statusCode(200);

        verify(skateBoardService).getAllAvailableSkateBoards();
    }
}