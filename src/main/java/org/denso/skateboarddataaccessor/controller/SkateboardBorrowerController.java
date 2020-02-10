package org.denso.skateboarddataaccessor.controller;


import lombok.extern.slf4j.Slf4j;
import org.denso.skateboarddataaccessor.model.Skateboard;
import org.denso.skateboarddataaccessor.model.request.FindBySpecsFilterRequest;
import org.denso.skateboarddataaccessor.service.SkateboardBorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/borrower")
@Slf4j
public class SkateboardBorrowerController {

    private final SkateboardBorrowerService skateboardBorrowerService;

    @Autowired
    public SkateboardBorrowerController(SkateboardBorrowerService skateboardBorrowerService) {
        this.skateboardBorrowerService = skateboardBorrowerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Skateboard>> getAllAvailable() {
        return skateboardBorrowerService.getAllAvailableSkateboards();
    }

    @GetMapping("/brand/{brandName}")
    public ResponseEntity<List<Skateboard>> getByBrandName(@PathVariable("brandName") String brandName) {
        log.info("Received request to find available skateboards with brand name: {}", brandName);
        return skateboardBorrowerService.getBoardsByBrandName(brandName);
    }

    @PostMapping("/search")
    public ResponseEntity searchByFilter(@RequestBody FindBySpecsFilterRequest request) {
        log.info("Received request to search for skateboards based on filter: {}", request);
        return skateboardBorrowerService.searchForBoards(request);
    }
}
