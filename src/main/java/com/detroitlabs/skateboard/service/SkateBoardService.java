package com.detroitlabs.skateboard.service;

import com.detroitlabs.skateboard.model.SkateBoard;

import java.util.List;

public interface SkateBoardService {
    public SkateBoard save(SkateBoard skateBoard);

    public SkateBoard findOne(Integer id);

    public List<SkateBoard> findAll();

    public SkateBoard findByLength(Integer length);

    public SkateBoard findByWeight(Integer weight);

    public SkateBoard findByBrand(String brand);

    public SkateBoard deleteSkateBoardById(Integer id);

    public SkateBoard updateSkateBoardDetails(Integer id, String ownerName, String brand, Integer length, Integer weight, Boolean isAvailable);
}
