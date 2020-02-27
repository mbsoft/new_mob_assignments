package com.skateboardshare.skateboard_Rest_API.service;

import com.skateboardshare.skateboard_Rest_API.models.Skateboard;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class SkateboardService implements SkateboardServiceinterface
{
    private static List<Skateboard> skateboards = new ArrayList<>();

    public Skateboard saveBoard(Skateboard skateboard) {
        if (!(skateboard.getId() == null)) {
            boolean isPresent = false;
            for (Skateboard skateBoard1 : skateboards) {
                if (Objects.equals(skateBoard1.getId(), skateboard.getId())) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                skateboards.add(skateboard);
            }
        }
        return skateboard;
    }

    public Skateboard findBoard(Integer id) {
        for (Skateboard skateBoard : skateboards) {
            if (skateBoard.getId().equals(id)) {
                return skateBoard;
            }
        }
        return null;
    }

    public List<Skateboard> findAllBoards() {
        return skateboards;
    }

    public Skateboard findByBoardLength(Integer length) {
        for (Skateboard skateboard : skateboards) {
            if ((skateboard.getLength() == length)) {
                return skateboard;
            }
        }
        return null;
    }

    public Skateboard findByBoardWeight(Integer weight) {
        for (Skateboard skateBoard : skateboards) {
            if ((skateBoard.getWeight() == weight)) {
                return skateBoard;
            }
        }
        return null;
    }

    public Skateboard findByBoardBrand(String brand) {
        for (Skateboard skateboard : skateboards) {
            if ((skateboard.getBrand().equals(brand))) {
                return skateboard;
            }
        }
        return null;
    }

    public Skateboard findByBoardLocation(String location)
    {
        for(Skateboard skateboard : skateboards)
        {
            if(skateboard.getLocation().equals(location))
            {
                return skateboard;
            }
        }
        return null;
    }

    public Skateboard findByOwnersName(String ownersName)
    {
        for(Skateboard skateboard : skateboards)
        {
            if(skateboard.getLocation().equalsIgnoreCase(ownersName))
            {
                return skateboard;
            }
        }
        return null;
    }

    public Skateboard findByBorrowersName(String borrowersName)
    {
        for(Skateboard skateboard : skateboards)
        {
            if(skateboard.getLocation().equalsIgnoreCase(borrowersName))
            {
                return skateboard;
            }
        }
        return null;
    }

    public Skateboard deleteSkateboardById(Integer id) {
        Iterator<Skateboard> iterator = skateboards.iterator();
        while (iterator.hasNext()) {
            Skateboard skateBoard = iterator.next();
            if (skateBoard.getId().equals(id)) {
                iterator.remove();
                return skateBoard;
            }
        }
        return null;
    }

    public Skateboard updateSkateboardDetails(Integer id, String ownerName, String brand, Integer length, Integer weight, String location, String borrowersName, Boolean isAvailable) {
        for (Skateboard skateboard : skateboards) {
            if (skateboard.getId().equals(id)) {

                skateboard.setOwnerName(ownerName);
                skateboard.setBrand(brand);
                skateboard.setLength(length);
                skateboard.setWeight(weight);
                skateboard.setLocation(location);
                skateboard.setBorrowerName(borrowersName);
                skateboard.setBoardAvailable(isAvailable);

                return skateboard;
            }
        }
        return null;
    }
}
