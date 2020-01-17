package com.denso.demo.service;

import com.denso.demo.domain.SkateBoard;

import java.util.List;

public interface SkateBoardService {

    List<SkateBoard> getAllAvailableSkateBoards();

    void addSkateBoard(SkateBoard skateBoard);

    void updateSkateBoardAvailability(String name, boolean availability);
}