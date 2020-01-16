package com.denso.demo.controller;

import com.denso.demo.domain.SkateBoard;
import com.denso.demo.service.SkateBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}