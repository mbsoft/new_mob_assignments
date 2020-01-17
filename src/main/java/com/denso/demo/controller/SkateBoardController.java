package com.denso.demo.controller;

import com.denso.demo.domain.SkateBoard;
import com.denso.demo.service.SkateBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skate-board")
@RequiredArgsConstructor
public class SkateBoardController {

    private final SkateBoardService skateBoardService;

    @GetMapping
    public List<SkateBoard> getSkateBoards() {

        return skateBoardService.getAllAvailableSkateBoards();
    }

    @PostMapping
    public void addSkateBoard(@RequestBody SkateBoard skateBoard) {

        skateBoardService.addSkateBoard(skateBoard);
    }

    @PutMapping("/{boardId}")
    public void updateSkateBoard(@PathVariable long boardId, @RequestBody SkateBoard skateBoard) {

        skateBoardService.updateSkateBoard(boardId, skateBoard);
    }

    @DeleteMapping("/{boardId}")
    public void deleteSkateBoard(@PathVariable long boardId) {

        skateBoardService.deleteSkateBoard(boardId);
    }
}