package com.denso.demo.integration;

import com.denso.demo.domain.SkateBoard;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
public class SkateBoardIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void integrationTestForSkateBoards() throws Exception {

        //  Adding a skate board to the repository
        SkateBoard skateBoard = SkateBoard.builder().ownerName("kiran").available(true).build();
        MvcResult postResult = mockMvc.perform(post("/skate-board")
                .contentType(APPLICATION_JSON)
                .content(new Gson().toJson(skateBoard)))
                .andReturn();
        assertThat(postResult.getResponse().getStatus()).isEqualTo(ACCEPTED.value());

        //  Retrieve all the skate boards which got added
        MvcResult getResult = mockMvc.perform(get("/skate-board")).andReturn();
        SkateBoard[] skateBoards = new Gson().fromJson(getResult.getResponse().getContentAsString(), SkateBoard[].class);
        assertThat(skateBoards[0]).isEqualToIgnoringGivenFields(skateBoard, "timeStamp", "id");

        //  Retrieve specific skate board based on Id
        MvcResult getSpecificSkeBoard = mockMvc
                .perform(get("/skate-board/" + skateBoards[0].getId()))
                .andReturn();
        SkateBoard actual = new Gson().fromJson(getSpecificSkeBoard.getResponse().getContentAsString(), SkateBoard.class);
        assertThat(actual).isEqualToIgnoringGivenFields(skateBoard, "timeStamp", "id");

        //  Update the owner name on skate board
        skateBoard.setOwnerName("Not Kiran");
        MvcResult updateSkeBoard = mockMvc.perform(put("/skate-board/" + skateBoards[0].getId())
                .contentType(APPLICATION_JSON)
                .content(new Gson().toJson(skateBoard)))
                .andReturn();
        assertThat(updateSkeBoard.getResponse().getStatus()).isEqualTo(ACCEPTED.value());

        //  Retrieve the skate board which got updated
        MvcResult updatedSkeBoard = mockMvc
                .perform(get("/skate-board/" + skateBoards[0].getId()))
                .andReturn();
        SkateBoard updatedSkateBoard = new Gson().fromJson(updatedSkeBoard.getResponse().getContentAsString(), SkateBoard.class);
        assertThat(updatedSkateBoard.getOwnerName()).isEqualTo("Not Kiran");

        MvcResult deleteSkateBoard = mockMvc.perform(delete("/skate-board/" + skateBoards[0].getId())).andReturn();
        assertThat(deleteSkateBoard.getResponse().getStatus()).isEqualTo(OK.value());

        //  Check if skate board is deleted
        MvcResult afterDeletion = mockMvc
                .perform(get("/skate-board/" + skateBoards[0].getId()))
                .andReturn();

        SkateBoard afterDeletionBoard = new Gson().fromJson(afterDeletion.getResponse().getContentAsString(), SkateBoard.class);
        assertThat(afterDeletionBoard).isNull();
    }
}
