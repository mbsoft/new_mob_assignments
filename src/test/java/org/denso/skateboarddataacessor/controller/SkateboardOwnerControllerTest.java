package org.denso.skateboarddataacessor.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.denso.skateboarddataaccessor.controller.SkateboardOwnerController;
import org.denso.skateboarddataaccessor.exception.OwnerNotFoundException;
import org.denso.skateboarddataaccessor.model.request.UpdateOwnerBoardsRequest;
import org.denso.skateboarddataaccessor.service.SkateboardOwnerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class SkateboardOwnerControllerTest {

    @InjectMocks
    @Spy
    private SkateboardOwnerController skateboardOwnerController;

    @Mock
    private SkateboardOwnerService skateboardOwnerService;

    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(skateboardOwnerController).build();
    }

    @Test
    public void readOwnerShouldReturnHttpNotFound_NoRecordInDatabase() throws Exception {
        when(skateboardOwnerService.getOwnerById(anyLong())).thenThrow(new OwnerNotFoundException(1L));
        mockMvc.perform(get("/owner/1"))
                .andExpect(status().isNotFound());
    }

    @Test(expected = OwnerNotFoundException.class)
    public void updateOwnerBoardsShouldThrowException_OwnerNotFound() throws Exception {
        when(skateboardOwnerService.updateOwnerBoards(any(UpdateOwnerBoardsRequest.class))).thenThrow(new OwnerNotFoundException(1L));

        UpdateOwnerBoardsRequest request = UpdateOwnerBoardsRequest.builder()
                .ownerId(1L)
                .boards(new HashSet<>())
                .build();

        mockMvc.perform(post("/owner/updateBoards")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test(expected = OwnerNotFoundException.class)
    public void updateOwnerNameShouldThrowException_NoOwnerInDatabase() throws Exception {
        when(skateboardOwnerService.updateOwnerName(anyLong(), anyString())).thenThrow(new OwnerNotFoundException(1L));

        mockMvc.perform(post("/owner/1/newName"))
                .andExpect(status().isNotFound());
    }
}
