package com.skateboardshare.skateboard_Rest_API.service;

import com.skateboardshare.skateboard_Rest_API.models.Skateboard;

import java.util.List;

public interface SkateboardServiceinterface
{
    Skateboard saveBoard(Skateboard skateboard);

    Skateboard findBoard(Integer id);

    List<Skateboard> findAllBoards();

    Skateboard findByBoardLength(Integer length);

    Skateboard findByBoardWeight(Integer weight);

    Skateboard findByBoardBrand(String brand);

    Skateboard findByBoardLocation(String location);

    Skateboard findByBorrowersName(String borrowersName);

    Skateboard findByOwnersName(String ownersName);

    Skateboard deleteSkateboardById(Integer id);

    Skateboard updateSkateboardDetails(Integer id, String ownerName, String brand, Integer length, Integer weight, String location, String borrowersName,Boolean isAvailable);
}
