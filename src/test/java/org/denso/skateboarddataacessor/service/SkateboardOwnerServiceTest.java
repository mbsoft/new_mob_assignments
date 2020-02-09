package org.denso.skateboarddataacessor.service;


import org.denso.skateboarddataaccessor.exception.OwnerNotFoundException;
import org.denso.skateboarddataaccessor.model.request.UpdateOwnerBoardsRequest;
import org.denso.skateboarddataaccessor.repository.OwnerRepository;
import org.denso.skateboarddataaccessor.service.SkateboardOwnerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class SkateboardOwnerServiceTest {

    @InjectMocks
    @Spy
    private SkateboardOwnerService skateboardOwnerService;

    @Mock
    private OwnerRepository ownerRepository;


    @Test(expected = OwnerNotFoundException.class)
    public void deleteOwnerShouldThrowException_OwnerDoesNotExist() throws Exception {
        when(ownerRepository.findById(anyLong())).thenReturn(null);

        skateboardOwnerService.deleteOwner(anyLong());
    }

    @Test(expected = OwnerNotFoundException.class)
    public void updateOwnerBoardsShouldThrowException_OwnerDoesNotExist() throws Exception {
        when(ownerRepository.findById(anyLong())).thenReturn(null);

        skateboardOwnerService.updateOwnerBoards(any(UpdateOwnerBoardsRequest.class));
    }

    @Test(expected = OwnerNotFoundException.class)
    public void updateOwnerNameShouldThrowException_OwnerDoesNotExist() throws Exception {
        when(ownerRepository.findById(anyLong())).thenReturn(null);

        skateboardOwnerService.updateOwnerName(1L, "new name");
    }
}
