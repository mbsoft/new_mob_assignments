package com.detroitlabs.skateboard.dao;

import com.detroitlabs.skateboard.model.SkateBoard;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SkateBoardDaoService {
    private static List<SkateBoard> skateBoards = new ArrayList<>();
    private static int skateBoardsCount = 3;

    static {
        skateBoards.add(new SkateBoard(1, "Rohan", "Adidas", 40, 20, true));
        skateBoards.add(new SkateBoard(2, "James", "Nike", 50, 22, true));
        skateBoards.add(new SkateBoard(3, "Eva", "Puma", 48, 24, true));
    }

    public SkateBoard save(SkateBoard skateBoard) {
        if (skateBoard.getId() == null) {
            skateBoard.setId(++skateBoardsCount);
        }
        skateBoards.add(skateBoard);
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
}
