package com.skateboardshare.skateboard_Rest_API.controller;

import com.skateboardshare.skateboard_Rest_API.models.Skateboard;
import com.skateboardshare.skateboard_Rest_API.service.SkateboardServiceinterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@Slf4j
public class SkateboardController {

    @Autowired
    private SkateboardServiceinterface skateboardServiceinterface;


    @GetMapping({"", "/", "/index"})
    public String getIndexPage() {
        return "index";
    }

    @GetMapping(path = "skateboards/{id}")
    public Skateboard retrieveSkateboard(@PathVariable Integer id) {
        Skateboard skateboard = skateboardServiceinterface.findBoard(id);

        log.info("SkateBoard is found with id: " + id);
        return skateboard;
    }

    @GetMapping(path = "skateboards")
    public List<Skateboard> retrieveAllSkateboards() {
        return skateboardServiceinterface.findAllBoards();
    }

    @GetMapping("skateboards/byLength/{length}")
    public Skateboard retrieveSkateBoardByLength(@PathVariable Integer length) {
        Skateboard skateboard = skateboardServiceinterface.findByBoardLength(length);


        log.info("Skateboard is found with length: " + length);
        return skateboard;
    }

    @GetMapping("skateboards/byWeight/{weight}")
    public Skateboard retrieveSkateboardByWeight(@PathVariable Integer weight) {
        Skateboard skateboard = skateboardServiceinterface.findByBoardWeight(weight);


        log.info("SkateBoard is found with weight: " + weight);
        return skateboard;
    }

    @GetMapping("skateboards/byBrand/{brand}")
    public Skateboard retrieveSkateBoardByBrand(@PathVariable String brand) {
        Skateboard skateboard = skateboardServiceinterface.findByBoardBrand(brand);

        log.info("SkateBoard is found with brand: " + brand);
        return skateboard;
    }

    @GetMapping("skateboards/bylocation/{location}")
    public Skateboard retrieveSkateBoardByLocation(@PathVariable String location) {
        Skateboard skateboard = skateboardServiceinterface.findByBoardLocation(location);

        log.info("SkateBoard is found with location: " + location);
        return skateboard;
    }

    @GetMapping("skateboards/owner/{ownerName}")
    public Skateboard retrieveSkateBoardByOwnersName(@PathVariable String ownerName) {
        Skateboard skateboard = skateboardServiceinterface.findByOwnersName(ownerName);

        log.info("SkateBoard is found under owners Name: " + ownerName);
        return skateboard;
    }

    @GetMapping("skateboards/borrower/{borrowersName}")
    public Skateboard retrieveSkateBoardByBorrowersName(@PathVariable String borrowersName) {
        Skateboard skateboard = skateboardServiceinterface.findByOwnersName(borrowersName);

        log.info("SkateBoard is found under Borrowers Name: " + borrowersName);
        return skateboard;
    }

    @DeleteMapping("skateboards/{id}")
    public ResponseEntity<Object> deleteSkateBoard(@PathVariable Integer id) {
        Skateboard skateBoard = skateboardServiceinterface.deleteSkateboardById(id);

        log.info("SkateBoard is deleted with id: " + id);
        return ResponseEntity.status(200).build();
    }

    @PutMapping("skateboards/{id}/{ownerName}/{brand}/{length}/{weight}/{location}/{borrowersName}/{isAvailable}")
    public ResponseEntity<Skateboard> updateSkateBoard(@PathVariable(value = "id") Integer id,
                                                       @PathVariable(value = "ownerName") String ownerName,
                                                       @PathVariable(value = "brand") String brand,
                                                       @PathVariable(value = "length") Integer length,
                                                       @PathVariable(value = "weight") Integer weight,
                                                       @PathVariable(value = "location") String location,
                                                       @PathVariable(value = "borrowersName") String borrowersName,
                                                       @PathVariable(value = "isAvailable") Boolean isAvailable) {
        Skateboard skateboard = skateboardServiceinterface.findBoard(id);

        Skateboard updateSkateBoard = skateboardServiceinterface.updateSkateboardDetails(id, ownerName, brand, length, weight, location, borrowersName, isAvailable);

        log.info("SkateBoard details updated for id: " + skateboard.getId());
        return ResponseEntity.ok(updateSkateBoard);
    }
}
