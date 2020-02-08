package com.detroitlabs.skateboard.controller;

import com.detroitlabs.skateboard.dao.SkateBoardDaoService;
import com.detroitlabs.skateboard.model.SkateBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class SkateBoardController {

    @Autowired
    SkateBoardDaoService skateBoardDaoService;

    @PostMapping(path = "/api/v1/skateboard")
    public ResponseEntity<Object> createSkateBoard(@Valid @RequestBody SkateBoard skateBoard) {
        SkateBoard createSkateBoard = skateBoardDaoService.save(skateBoard);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(createSkateBoard.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
