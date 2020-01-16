package com.denso.demo.service;

import com.denso.demo.domain.SkateBoard;
import com.denso.demo.repository.SkateBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkateBoardServiceImpl implements SkateBoardService {

    private final SkateBoardRepository skateBoardRepository;

    @Override
    public List<SkateBoard> getAllAvailableSkateBoards() {
        skateBoardRepository.findAll();
        return null;
    }
}
