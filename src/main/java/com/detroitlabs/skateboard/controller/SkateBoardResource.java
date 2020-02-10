package com.detroitlabs.skateboard.controller;

import com.detroitlabs.skateboard.exception.SkateBoardNotFoundException;
import com.detroitlabs.skateboard.model.SkateBoard;
import com.detroitlabs.skateboard.service.SkateBoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class SkateBoardResource {

    @Autowired
    private SkateBoardServiceImpl skateBoardServiceImpl;

    @PostMapping(path = "createSkateboard")
    public ResponseEntity<Object> createSkateBoard(@Valid @RequestBody SkateBoard skateBoard) {
        SkateBoard createSkateBoard = skateBoardServiceImpl.save(skateBoard);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(createSkateBoard.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "skateboards/{id}")
    public SkateBoard retrieveSkateBoard(@PathVariable Integer id) {
        SkateBoard skateBoard = skateBoardServiceImpl.findOne(id);

        if (skateBoard == null)
            throw new SkateBoardNotFoundException("SkateBoard not found with id " + id);

        return skateBoard;
    }

    @GetMapping(path = "skateboards")
    public List<SkateBoard> retrieveAllSkateboards() {
        return skateBoardServiceImpl.findAll();
    }

    @GetMapping("skateboards/byLength/{length}")
    public SkateBoard retrieveSkateBoardByLength(@PathVariable Integer length) {
        SkateBoard skateBoard = skateBoardServiceImpl.findByLength(length);

        if (skateBoard == null)
            throw new SkateBoardNotFoundException("SkateBoard not found with length " + length);

        return skateBoard;
    }

    @GetMapping("skateboards/byWeight/{weight}")
    public SkateBoard retrieveSkateBoardByWeight(@PathVariable Integer weight) {
        SkateBoard skateBoard = skateBoardServiceImpl.findByWeight(weight);

        if (skateBoard == null)
            throw new SkateBoardNotFoundException("SkateBoard not found with weight " + weight);

        return skateBoard;
    }

    @GetMapping("skateboards/byBrand/{brand}")
    public SkateBoard retrieveSkateBoardByBrand(@PathVariable String brand) {
        SkateBoard skateBoard = skateBoardServiceImpl.findByBrand(brand);

        if (skateBoard == null)
            throw new SkateBoardNotFoundException("SkateBoard not found with brand " + brand);

        return skateBoard;
    }

    @DeleteMapping("skateboards/{id}")
    public ResponseEntity<Object> deleteSkateBoard(@PathVariable Integer id) {
        SkateBoard skateBoard = skateBoardServiceImpl.deleteSkateBoardById(id);

        if (skateBoard == null)
            throw new SkateBoardNotFoundException("SkateBoard not found with id " + id);

        return ResponseEntity.status(200).build();
    }

    @PutMapping("skateboards/{id}/{ownerName}/{brand}/{length}/{weight}/{isAvailable}")
    public ResponseEntity<SkateBoard> updateSkateBoard(@PathVariable(value = "id") Integer id,
                                                       @PathVariable(value = "ownerName") String ownerName,
                                                       @PathVariable(value = "brand") String brand,
                                                       @PathVariable(value = "length") Integer length,
                                                       @PathVariable(value = "weight") Integer weight,
                                                       @PathVariable(value = "isAvailable") Boolean isAvailable
    ) {
        SkateBoard skateBoard = skateBoardServiceImpl.findOne(id);

        if (skateBoard == null)
            throw new SkateBoardNotFoundException("SkateBoard not found with id " + id);

        SkateBoard updateSkateBoard = skateBoardServiceImpl.updateSkateBoardDetails(id, ownerName, brand,  length, weight, isAvailable);

        return ResponseEntity.ok(updateSkateBoard);
    }
}
