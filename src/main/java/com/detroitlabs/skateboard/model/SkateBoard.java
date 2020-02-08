package com.detroitlabs.skateboard.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about SkateBoard")
public class SkateBoard {
    private int id;
    private String ownerName;
    private String boardType;
    private int weight;
    private int length;
    private boolean isBoardAvailable;

    public SkateBoard() {
    }

    public SkateBoard(int id, String ownerName, String boardType, int weight, int length, boolean isBoardAvailable) {
        this.id = id;
        this.ownerName = ownerName;
        this.boardType = boardType;
        this.weight = weight;
        this.length = length;
        this.isBoardAvailable = isBoardAvailable;
    }

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

    public void setBoardAvailable(boolean boardAvailable) {
        isBoardAvailable = boardAvailable;
    }
}