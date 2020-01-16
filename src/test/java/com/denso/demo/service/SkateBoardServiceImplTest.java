package com.denso.demo.service;

import com.denso.demo.repository.SkateBoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SkateBoardServiceImplTest {

    @InjectMocks
    private SkateBoardServiceImpl classUnderTest;
    @Mock
    private SkateBoardRepository repository;

    @Test
    public void getAllAvailableSkateBoards() {

        classUnderTest.getAllAvailableSkateBoards();

        verify(repository).findAll();
    }
}