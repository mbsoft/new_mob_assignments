package com.skateboard.model;

public class SkateBoard {
	
	private int id;
	private String ownerName;
	private String boardType;
	private int weight;
	private int length;
	private boolean isBoardAvailable;
	 
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getBoardType() {
		return boardType;
	}
	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public boolean isBoardAvailable() {
		return isBoardAvailable;
	}
	public void setBoardAvailable(boolean isBoardAvailable) {
		this.isBoardAvailable = isBoardAvailable;
	}
	

}
