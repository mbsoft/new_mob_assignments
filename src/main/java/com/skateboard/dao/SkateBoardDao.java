package com.skateboard.dao;

import java.util.ArrayList;

import com.skateboard.model.SkateBoard;

public class SkateBoardDao {

	public ArrayList<SkateBoard> getAvailableBoards() {
		//TODO write logic to get all skateboards from db with isBoardAvailability to true
		return new ArrayList<SkateBoard>();
	}

	public SkateBoard getSkateBoardById(int skateboardId) {
		//TODO write logic to return board with id = skateboardId 
		return new SkateBoard();
	}

	public int createSkateBoard(SkateBoard skateBoard) {
		int id = 0;
		// TODO write logic to insert into table and get the new id and set it to id
		return id;
	}
	
	
	

}
