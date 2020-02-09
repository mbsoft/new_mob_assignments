package org.denso.skateboarddataacessor.controller;


import org.denso.skateboarddataaccessor.controller.SkateboardOwnerController;
import org.denso.skateboarddataaccessor.exception.OwnerNotFoundException;
import org.denso.skateboarddataaccessor.service.SkateboardOwnerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SkateboardOwnerControllerTest {

    @InjectMocks
    @Spy
    private SkateboardOwnerController skateboardOwnerController;

    @Mock
    private SkateboardOwnerService skateboardOwnerService;


    @Test
    public void readOwnerShouldReturnHttpNotFound_NoRecordInDatabase() {

    }

    @Test(expected = OwnerNotFoundException.class)
    public void updateOwnerBoardsShouldThrowException_OwnerNotFound() {

    }

    @Test(expected = OwnerNotFoundException.class)
    public void updateOwnerNameShouldThrowException_NoOwnerInDatabase() {

    }

    @Test
    public void deleteOwnerShouldReturnHttpNotFound_NoRecordInDatabase() {

    }

    @Test(expected = OwnerNotFoundException.class)
    public void addBoardToOwnerShouldThrowException_NoOwnerInDatabase() {

    }
}
