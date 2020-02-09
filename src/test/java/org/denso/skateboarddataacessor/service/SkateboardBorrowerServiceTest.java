package org.denso.skateboarddataacessor.service;


import org.denso.skateboarddataaccessor.repository.SkateboardRepository;
import org.denso.skateboarddataaccessor.service.SkateboardBorrowerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SkateboardBorrowerServiceTest {

    @InjectMocks
    @Spy
    private SkateboardBorrowerService skateboardBorrowerService;

    @Mock
    private SkateboardRepository skateboardRepository;

    @Test
    public void getAllAvailableSkateboardsShouldReturnHttpNotFound_NoRecordInDatabase() {
        when(skateboardRepository.findAllByAvailableIsTrue()).thenReturn(new ArrayList<>());
        ResponseEntity expected = new ResponseEntity(HttpStatus.NOT_FOUND);
        assertEquals(skateboardBorrowerService.getAllAvailableSkateboards(), expected);
    }

    @Test
    public void getBoardsByBrandNameShouldReturnHttpNotFound_NoRecordInDatabase() {
        when(skateboardRepository.findByAvailableIsTrueAndBrandEquals(anyString())).thenReturn(new ArrayList<>());
        ResponseEntity expected = new ResponseEntity(HttpStatus.NOT_FOUND);
        assertEquals(skateboardBorrowerService.getAllAvailableSkateboards(), expected);
    }
}
