package com.denso.demo.controller;

import com.denso.demo.domain.SkateBoard;
import com.denso.demo.service.SkateBoardService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@RunWith(MockitoJUnitRunner.class)
public class SkateBoardControllerTest {

    @Mock
    private SkateBoardService skateBoardService;

    @Test
    public void getSkateBoards_givenAPICallToGetSkateBoard_return200AsStatusCode() {

        List<SkateBoard> skateBoards = Arrays.asList(SkateBoard.builder().ownerName("ownerName").description("description").build());

        when(skateBoardService.getAllAvailableSkateBoards()).thenReturn(skateBoards);

        given()
                .standaloneSetup(new SkateBoardController(skateBoardService))
                .when()
                .get("/skate-board")
                .then()
                .statusCode(200)
                .body(is("[{\"ownerName\":\"ownerName\",\"description\":\"description\",\"brand\":null,\"weight\":null,\"length\":null,\"location\":null,\"timeStamp\":null,\"available\":false}]"));
    }

    @Test
    public void addSkateBoard_givenAPICallToPostSkateBoard_return200AsStatusCode() {

        SkateBoard skateBoard = SkateBoard.builder().ownerName("ownerName").description("description").build();

        given()
                .standaloneSetup(new SkateBoardController(skateBoardService))
                .header(CONTENT_TYPE, "application/json")
                .body(new Gson().toJson(skateBoard))
                .when()
                .post("/skate-board")
                .then()
                .statusCode(200);

        verify(skateBoardService).addSkateBoard(skateBoard);
    }

    @Test
    public void updateSkateBoardAvailability_givenAPICallToPutSkateBoard_return200AsStatusCode() {

        SkateBoard skateBoard = SkateBoard.builder()
                .ownerName("ownerName")
                .description("description")
                .brand("brand")
                .length(new BigDecimal(12.00))
                .location("Detroit")
                .weight(new BigDecimal(12.00))
                .timeStamp(LocalDateTime.now().toString())
                .build();

        given()
                .standaloneSetup(new SkateBoardController(skateBoardService))
                .header(CONTENT_TYPE, "application/json")
                .body(new Gson().toJson(skateBoard))
                .when()
                .put("/skate-board/1")
                .then()
                .statusCode(200);


        verify(skateBoardService).updateSkateBoardAvailability(1l, skateBoard);
    }
}