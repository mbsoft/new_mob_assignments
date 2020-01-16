package com.denso.demo.service;

import com.denso.demo.domain.SkateBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkateBoardServiceImpl implements SkateBoardService {

    @Override
    public List<SkateBoard> getAllAvailableSkateBoards() {
        return null;
    }
}
