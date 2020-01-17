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
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

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

        when(skateBoardRepository.findAll()).thenReturn(singletonList(SkateBoardEntity.builder().ownerName("ownerName").description("description").available(true).build()));

        List<SkateBoard> actual = classUnderTest.getAllAvailableSkateBoards();

        assertThat(actual).hasSize(1);
        assertThat(actual.get(0)).isEqualToComparingFieldByField(SkateBoard.builder().ownerName("ownerName").description("description").build());
    }

    @Test
    public void getAllAvailableSkateBoards_givenNonEmptyListWithUnavailableSkateBoardEntities_shouldReturnEmptyList() {

        when(skateBoardRepository.findAll()).thenReturn(asList(SkateBoardEntity.builder().ownerName("ownerName").description("description").available(false).build(),
                SkateBoardEntity.builder().ownerName("ownerName").description("description").available(true).build()));

        List<SkateBoard> actual = classUnderTest.getAllAvailableSkateBoards();

        assertThat(actual).hasSize(1);
        assertThat(actual.get(0)).isEqualToComparingFieldByField(SkateBoard.builder().ownerName("ownerName").description("description").build());
    }

    @Test
    public void addSkateBoard_givenNullSkateBoard_doNotCallRepositorySave() {

        classUnderTest.addSkateBoard(SkateBoard.builder().ownerName("ownerName").description("description").build());

        verify(skateBoardRepository).save(eq(SkateBoardEntity.builder().ownerName("ownerName").description("description").build()));
    }

    @Test
    public void addSkateBoard_givenSkateBoard_callRepositorySave() {

        classUnderTest.addSkateBoard(SkateBoard.builder().ownerName("ownerName").description("description").build());

        verify(skateBoardRepository).save(eq(SkateBoardEntity.builder().ownerName("ownerName").description("description").build()));
    }

    @Test
    public void updateSkateBoardAvailability_givenNoSkateBoardsOnOwnerName_doNotCallRepositoryUpdate() {

        when(skateBoardRepository.findById(1l)).thenReturn(Optional.empty());

        classUnderTest.updateSkateBoardAvailability(1l, SkateBoard.builder().ownerName("ownerName").description("description").build());

        verify(skateBoardRepository, times(0)).save(any());
    }

    @Test
    public void updateSkateBoardAvailability_givenSkateBoardOwnerName_callRepositoryUpdate() {

        when(skateBoardRepository.findById(1l)).thenReturn(Optional.of(SkateBoardEntity.builder().ownerName("ownerName").description("description").build()));

        classUnderTest.updateSkateBoardAvailability(1l, SkateBoard.builder().ownerName("ownerName1").description("description1").available(true).build());

        verify(skateBoardRepository).save(eq(SkateBoardEntity.builder().ownerName("ownerName1").description("description1").available(true).build()));
    }
}