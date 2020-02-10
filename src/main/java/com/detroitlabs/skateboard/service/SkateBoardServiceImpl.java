package com.detroitlabs.skateboard.service;

import com.detroitlabs.skateboard.model.SkateBoard;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class SkateBoardServiceImpl implements SkateBoardService {
    private static List<SkateBoard> skateBoards = new ArrayList<>();
    private static int skateBoardsCount = 3;

    static {
        skateBoards.add(new SkateBoard(1, "Rohan", "Adidas", 40, 20, true));
        skateBoards.add(new SkateBoard(2, "James", "Nike", 50, 22, true));
        skateBoards.add(new SkateBoard(3, "Eva", "Puma", 48, 24, true));
    }

    public SkateBoard save(SkateBoard skateBoard) {
        if (!(skateBoard.getId() == null)) {
            Boolean isPresent = false;
            for (SkateBoard skateBoard1 : skateBoards) {
                if (skateBoard1.getId() == skateBoard.getId()) {
                    isPresent = true;
                }
            }
            if (!isPresent) {
                skateBoards.add(skateBoard);
            }
        }
        return skateBoard;
    }

    public SkateBoard findOne(Integer id) {
        for (SkateBoard skateBoard : skateBoards) {
            if (skateBoard.getId().equals(id)) {
                return skateBoard;
            }
        }
        return null;
    }

    public List<SkateBoard> findAll() {
        return skateBoards;
    }

    public SkateBoard findByLength(Integer length) {
        for (SkateBoard skateBoard : skateBoards) {
            if ((skateBoard.getLength() == length)) {
                return skateBoard;
            }
        }
        return null;
    }

    public SkateBoard findByWeight(Integer weight) {
        for (SkateBoard skateBoard : skateBoards) {
            if ((skateBoard.getWeight() == weight)) {
                return skateBoard;
            }
        }
        return null;
    }

    public SkateBoard findByBrand(String brand) {
        for (SkateBoard skateBoard : skateBoards) {
            if ((skateBoard.getBrand().equals(brand))) {
                return skateBoard;
            }
        }
        return null;
    }

    public SkateBoard deleteSkateBoardById(Integer id) {
        Iterator<SkateBoard> iterator = skateBoards.iterator();
        while (iterator.hasNext()) {
            SkateBoard skateBoard = iterator.next();
            if (skateBoard.getId().equals(id)) {
                iterator.remove();
                return skateBoard;
            }
        }
        return null;
    }

    public SkateBoard updateSkateBoardDetails(Integer id, String ownerName, String brand, Integer length, Integer weight, Boolean isAvailable) {
        for (SkateBoard skateBoard : skateBoards) {
            if (skateBoard.getId().equals(id)) {

                skateBoard.setOwnerName(ownerName);
                skateBoard.setBrand(brand);
                skateBoard.setLength(length);
                skateBoard.setWeight(weight);
                skateBoard.setBoardAvailable(isAvailable);

                return skateBoard;
            }
        }
        return null;
    }
}
