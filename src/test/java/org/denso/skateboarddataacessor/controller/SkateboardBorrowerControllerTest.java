package org.denso.skateboarddataacessor.controller;


import org.denso.skateboarddataaccessor.controller.SkateboardBorrowerController;
import org.denso.skateboarddataaccessor.service.SkateboardBorrowerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SkateboardBorrowerControllerTest {

    @InjectMocks
    @Spy
    private SkateboardBorrowerController skateboardBorrowerController;

    @Mock
    private SkateboardBorrowerService skateboardBorrowerService;


    @Test
    public void getAllAvailableSkateboardsShouldReturnHttpNotFound_DatabaseIsEmpty() {

    }

    @Test
    public void getByBrandNameShouldReturnHttpOk_ValidRequestAndRecordExists() {

    }

    @Test
    public void getByBrandNameShouldReturnHttpNotFound_NoRecordExists() {

    }

}
