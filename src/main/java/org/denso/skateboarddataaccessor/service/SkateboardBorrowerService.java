package org.denso.skateboarddataaccessor.service;

import org.denso.skateboarddataaccessor.model.Skateboard;
import org.denso.skateboarddataaccessor.model.request.FindBySpecsFilterRequest;
import org.denso.skateboarddataaccessor.repository.SkateboardRepository;
import org.denso.skateboarddataaccessor.repository.specification.SkateboardSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class SkateboardBorrowerService {

    private final SkateboardRepository skateboardRepository;

    @Autowired
    public SkateboardBorrowerService(SkateboardRepository skateboardRepository) {
        this.skateboardRepository = skateboardRepository;
    }

    public ResponseEntity<List<Skateboard>> getAllAvailableSkateboards() {
        List<Skateboard> results = skateboardRepository.findAllByAvailableIsTrue();
        if (results.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    public ResponseEntity<List<Skateboard>> getBoardsByBrandName(String brandName) {
        List<Skateboard> results = skateboardRepository.findByAvailableIsTrueAndBrandEquals(brandName);
        if (results.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    public ResponseEntity searchForBoards(FindBySpecsFilterRequest filter) {
        SkateboardSpecification skateboardSpecification = new SkateboardSpecification(filter);
        return new ResponseEntity<>(skateboardRepository.findAll((Pageable) where(skateboardSpecification)), HttpStatus.OK);
    }
}
