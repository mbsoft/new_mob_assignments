package org.denso.skateboarddataacessor.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.denso.skateboarddataaccessor.controller.SkateboardOwnerController;
import org.denso.skateboarddataaccessor.exception.OwnerNotFoundException;
import org.denso.skateboarddataaccessor.model.Skateboard;
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
import java.util.Set;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Test
    public void updateOwnerBoardsShouldThrowException_OwnerNotFound() throws Exception {
        when(skateboardOwnerService.updateOwnerBoards(any(UpdateOwnerBoardsRequest.class))).thenThrow(new OwnerNotFoundException(1L));

        Set<Skateboard> boards = new HashSet<>();
        boards.add(new Skateboard());
        UpdateOwnerBoardsRequest request = new UpdateOwnerBoardsRequest();
        request.setOwnerId(1L);
        request.setBoards(boards);

        mockMvc.perform(post("/owner/updateBoards")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateOwnerNameShouldThrowException_NoOwnerInDatabase() throws Exception {
        when(skateboardOwnerService.updateOwnerName(anyLong(), anyString())).thenThrow(new OwnerNotFoundException(1L));

        mockMvc.perform(put("/owner/1/newName"))
                .andExpect(status().isNotFound());
    }
}
