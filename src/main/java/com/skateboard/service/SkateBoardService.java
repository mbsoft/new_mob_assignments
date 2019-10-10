package com.skateboard.service;

import java.util.ArrayList;

import com.skateboard.dao.SkateBoardDao;
import com.skateboard.model.SkateBoard;

public class SkateBoardService {
	
	SkateBoardDao skateBoardDao = new SkateBoardDao();

	public ArrayList<SkateBoard> getAvailableBoards() {
			
		return skateBoardDao.getAvailableBoards();
	}

	public SkateBoard getSkateBoardById(int skateboardId) {
		
		return skateBoardDao.getSkateBoardById(skateboardId);
	}

	public SkateBoard createSkateBoard(SkateBoard skateBoard) {
		int newSkateBoardId = skateBoardDao.createSkateBoard(skateBoard);
		if(newSkateBoardId > 0 ) {
			skateBoard.setId(newSkateBoardId);
		}
		
		return skateBoard;
	}
	
	
			

}
