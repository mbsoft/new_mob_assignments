package com.denso.demo.controller;

import com.denso.demo.domain.SkateBoard;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/skate-board")
public class SkateBoardController {

    @GetMapping
    public List<SkateBoard> getSkateBoards() {
        return null;
    }
}