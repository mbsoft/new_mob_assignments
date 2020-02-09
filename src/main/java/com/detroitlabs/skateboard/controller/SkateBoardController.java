package com.detroitlabs.skateboard.controller;

import com.detroitlabs.skateboard.dao.SkateBoardDaoService;
import com.detroitlabs.skateboard.exception.SkateBoardNotFoundException;
import com.detroitlabs.skateboard.model.SkateBoard;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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

    @GetMapping(path = "/api/v1/skateboard/{id}")
    public SkateBoard retrieveSkateBoard(@PathVariable Integer id) {
        SkateBoard skateBoard = skateBoardDaoService.findOne(id);

        if (skateBoard == null)
            throw new SkateBoardNotFoundException("SkateBoard not found with id " + id);

        return skateBoard;
    }

    @GetMapping(path = "/users")
    public List<SkateBoard> retrieveAllUsers() {
        return skateBoardDaoService.findAll();
    }

    @GetMapping("/api/v1/skateboard/byLength/{length}")
    public SkateBoard retrieveSkateBoardByLength(@PathVariable Integer length) {
        SkateBoard skateBoard = skateBoardDaoService.findByLength(length);

        if (skateBoard == null)
            throw new SkateBoardNotFoundException("SkateBoard not found with length " + length);

        return skateBoard;
    }

    @GetMapping("/api/v1/skateboard/byWeight/{weight}")
    public SkateBoard retrieveSkateBoardByWeight(@PathVariable Integer weight) {
        SkateBoard skateBoard = skateBoardDaoService.findByWeight(weight);

        if (skateBoard == null)
            throw new SkateBoardNotFoundException("SkateBoard not found with weight " + weight);

        return skateBoard;
    }

    @GetMapping("/api/v1/skateboard/byBrand/{brand}")
    public SkateBoard retrieveSkateBoardByBrand(@PathVariable String brand) {
        SkateBoard skateBoard = skateBoardDaoService.findByBrand(brand);

        if (skateBoard == null)
            throw new SkateBoardNotFoundException("SkateBoard not found with brand " + brand);

        return skateBoard;
    }
}
