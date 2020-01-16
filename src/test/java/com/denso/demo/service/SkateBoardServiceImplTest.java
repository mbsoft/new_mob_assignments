package com.denso.demo.service;

import com.denso.demo.domain.SkateBoard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SkateBoardServiceImplTest {

    @InjectMocks
    private SkateBoardServiceImpl classUnderTest;

    @Test
    public void getAllAvailableSkateBoards() {

        List<SkateBoard> actual = classUnderTest.getAllAvailableSkateBoards();

        assertThat(actual).isNull();
    }
}