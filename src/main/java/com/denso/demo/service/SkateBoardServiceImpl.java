package com.denso.demo.service;

import com.denso.demo.domain.SkateBoard;
import com.denso.demo.repository.SkateBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkateBoardServiceImpl implements SkateBoardService {

    private final SkateBoardRepository skateBoardRepository;

    @Override
    public List<SkateBoard> getAllAvailableSkateBoards() {

        return skateBoardRepository.findAll()
                .stream()
                .map(entity -> SkateBoard.builder()
                        .name(entity.getName())
                        .description(entity.getDescription()).build())
                .collect(Collectors.toList());
    }
}
