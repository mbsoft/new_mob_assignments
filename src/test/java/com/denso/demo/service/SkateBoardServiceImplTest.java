package com.denso.demo.service;

import com.denso.demo.domain.SkateBoard;
import com.denso.demo.entity.SkateBoardEntity;
import com.denso.demo.repository.SkateBoardRepository;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SkateBoardServiceImplTest {

    @InjectMocks
    private SkateBoardServiceImpl classUnderTest;
    @Mock
    private SkateBoardRepository skateBoardRepository;

    @Test
    public void getAllAvailableSkateBoards_givenEmptySkateBoardList_shouldReturnEmpty() {

        when(skateBoardRepository.findAll()).thenReturn(Lists.emptyList());

        List<SkateBoard> actual = classUnderTest.getAllAvailableSkateBoards();

        assertThat(actual).isEmpty();
    }

    @Test
    public void getAllAvailableSkateBoards_givenAvailableSkateBoardList_shouldReturnSkateBoard() {

        when(skateBoardRepository.findAll()).thenReturn(singletonList(SkateBoardEntity.builder().name("name").description("description").available(true).build()));

        List<SkateBoard> actual = classUnderTest.getAllAvailableSkateBoards();

        assertThat(actual).hasSize(1);
        assertThat(actual.get(0)).isEqualToComparingFieldByField(SkateBoard.builder().name("name").description("description").build());
    }

    @Test
    public void getAllAvailableSkateBoards_givenNonEmptyUnavailableSkateBoardEntities_shouldReturnEmptyList() {

        when(skateBoardRepository.findAll()).thenReturn(singletonList(SkateBoardEntity.builder().name("name").description("description").available(false).build()));

        List<SkateBoard> actual = classUnderTest.getAllAvailableSkateBoards();

        assertThat(actual).isEmpty();
    }
}