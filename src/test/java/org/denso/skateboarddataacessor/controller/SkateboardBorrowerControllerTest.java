package org.denso.skateboarddataacessor.controller;


import org.denso.skateboarddataaccessor.controller.SkateboardBorrowerController;
import org.denso.skateboarddataaccessor.exception.OwnerNotFoundException;
import org.denso.skateboarddataaccessor.model.Skateboard;
import org.denso.skateboarddataaccessor.service.SkateboardBorrowerService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class SkateboardBorrowerControllerTest {

    @InjectMocks
    @Spy
    private SkateboardBorrowerController skateboardBorrowerController;

    @Mock
    private SkateboardBorrowerService skateboardBorrowerService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(skateboardBorrowerController).build();
    }

    @Test
    public void getAllAvailableSkateboardsShouldReturnHttpNotFound_DatabaseIsEmpty() throws Exception {
        when(skateboardBorrowerService.getAllAvailableSkateboards()).thenThrow(new OwnerNotFoundException(1L));
        mockMvc.perform(get("/borrower/all"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getByBrandNameShouldReturnHttpOk_ValidRequestAndRecordExists() throws Exception {
        Skateboard skateboardStub = new Skateboard();
        ResponseEntity expectedResponse = new ResponseEntity<>(skateboardStub, HttpStatus.OK);
        when(skateboardBorrowerService.getBoardsByBrandName(anyString())).thenReturn(expectedResponse);

        mockMvc.perform(get("/borrower/brand/testingBrand"))
                .andExpect(status().isOk());
    }

    @Test
    public void getByBrandNameShouldReturnHttpNotFound_NoRecordExists() throws Exception {
        when(skateboardBorrowerService.getBoardsByBrandName(anyString())).thenThrow(new OwnerNotFoundException(1L));

        mockMvc.perform(get("/borrower/brand/testingBrand"))
                .andExpect(status().isNotFound());
    }

}
