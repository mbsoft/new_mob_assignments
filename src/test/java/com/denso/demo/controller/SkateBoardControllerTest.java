package com.denso.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

@RunWith(MockitoJUnitRunner.class)
public class SkateBoardControllerTest {

    @Test
    public void getSkateBoards_givenAPIPath_return200AsStatusCode() {
        given()
                .standaloneSetup(SkateBoardController.class)
                .when()
                .get("/skate-board")
                .then()
                .statusCode(200);
    }
}