package com.denso.demo.controller;

import com.denso.demo.domain.SkateBoard;
import com.denso.demo.service.SkateBoardService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.ACCEPTED;

@RestController
@RequestMapping("/skate-board")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class SkateBoardController {

    private final SkateBoardService skateBoardService;

    @GetMapping
    @ApiOperation(value = "Get all available skate boards", notes = "Retrieve all available skate boards")
    public List<SkateBoard> getSkateBoards() {

        return skateBoardService.getAllAvailableSkateBoards();
    }

    @PostMapping
    @ResponseStatus(ACCEPTED)
    public void addSkateBoard(@RequestBody SkateBoard skateBoard) {

        skateBoardService.addSkateBoard(skateBoard);
        log.info("Skate board has been added");
    }

    @PutMapping("/{boardId}")
    @ResponseStatus(ACCEPTED)
    public void updateSkateBoard(@PathVariable long boardId, @RequestBody SkateBoard skateBoard) {

        skateBoardService.updateSkateBoard(boardId, skateBoard);

        log.info("Skate board has been updated");
    }

    @DeleteMapping("/{boardId}")
    public void deleteSkateBoard(@PathVariable long boardId) {

        skateBoardService.deleteSkateBoard(boardId);

        log.info("Skate board with id {} has been delete", boardId);

    }

    @GetMapping("/{boardId}")
    public SkateBoard getSkateBoard(@PathVariable long boardId) {

        return skateBoardService.getSkateBoard(boardId);
    }
}