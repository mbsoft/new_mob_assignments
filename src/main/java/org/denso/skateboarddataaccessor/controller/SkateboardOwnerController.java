package org.denso.skateboarddataaccessor.controller;


import lombok.extern.slf4j.Slf4j;
import org.denso.skateboarddataaccessor.exception.OwnerNotFoundException;
import org.denso.skateboarddataaccessor.model.Owner;
import org.denso.skateboarddataaccessor.model.request.AddBoardToOwnerRequest;
import org.denso.skateboarddataaccessor.model.request.UpdateOwnerBoardsRequest;
import org.denso.skateboarddataaccessor.service.SkateboardOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/owner")
@Slf4j
public class SkateboardOwnerController {

    private final SkateboardOwnerService skateboardOwnerService;

    @Autowired
    public SkateboardOwnerController(SkateboardOwnerService skateboardOwnerService) {
        this.skateboardOwnerService = skateboardOwnerService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> create(@Valid @RequestBody Owner owner) {
        log.info("Received request to create owner: {}", owner);
        return skateboardOwnerService.addOwner(owner);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> read(@PathVariable("id") Long id) throws OwnerNotFoundException {
        log.info("Received request to retrieve owner of id: {}", id);
        return skateboardOwnerService.getOwnerById(id);
    }

    @PostMapping("/updateBoards")
    public ResponseEntity<String> updateOwnerBoards(@Valid @RequestBody UpdateOwnerBoardsRequest request) throws OwnerNotFoundException {
        log.info("Received request to update owner's boards: {}", request);
        return skateboardOwnerService.updateOwnerBoards(request);
    }

    @PutMapping(value = "/{id}/{newName}")
    public ResponseEntity updateOwnerName(@PathVariable("id") Long id, @PathVariable("newName") String newName) throws OwnerNotFoundException {
        return skateboardOwnerService.updateOwnerName(id, newName);
    }

    @PutMapping
    public ResponseEntity<String> addBoardToOwner(@Valid @RequestBody AddBoardToOwnerRequest request) throws OwnerNotFoundException {
        log.info("Received request to add board owner: {}", request);
        return skateboardOwnerService.addBoardToOwner(request);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) throws OwnerNotFoundException {
        log.info("Received request to delete owner with id: {}", id);
        return skateboardOwnerService.deleteOwner(id);
    }

    @GetMapping("/byName/{ownerName}")
    public ResponseEntity<List<Owner>> searchByOwner(@PathVariable("ownerName") String ownerName) {
        log.info("Received request to find owners by name: {}", ownerName);
        return skateboardOwnerService.getOwnersByName(ownerName);
    }
}
