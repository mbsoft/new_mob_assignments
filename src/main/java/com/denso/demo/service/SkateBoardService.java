package com.denso.demo.service;

import com.denso.demo.domain.SkateBoard;

import java.util.List;

public interface SkateBoardService {

    List<SkateBoard> getAllAvailableSkateBoards();

    void addSkateBoard(SkateBoard skateBoard);

    void updateSkateBoard(long id, SkateBoard skateBoard);

    void deleteSkateBoard(long skateBoardId);

    SkateBoard getSkateBoard(long skateBoardId);
}