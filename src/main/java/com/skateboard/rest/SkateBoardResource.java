package com.skateboard.rest;


import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.skateboard.model.SkateBoard;
import com.skateboard.service.SkateBoardService;



@Path("skateboard")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SkateBoardResource {
	
	SkateBoardService skateBoardService = new  SkateBoardService();
	
	@GET
	public Response getAvailableBoards() {
		ArrayList<SkateBoard> skateBoards = new ArrayList<>();
		
		skateBoards = skateBoardService.getAvailableBoards();
		
		return Response
			      .status(Response.Status.OK)
			      .entity(skateBoards)
			      .build();
		
	}
	@Path("/{id}")
	@GET
	public Response getSkateBoard(@PathParam("id")Integer skateboardId) {
		SkateBoard board = new SkateBoard();
		Status status = null;
		board = skateBoardService.getSkateBoardById(skateboardId);
		
		if(board != null && board.getId() > 0) {
			status =	Response.Status.OK ;
		}
		else {
			status = Response.Status.NOT_FOUND;
		}
		return Response
			      .status(status)
			      .entity(board)
			      .build();
		
	}
	
	@POST
	public Response createSkateBoard(SkateBoard skateBoard) {
		SkateBoard board = new SkateBoard();
		Status status = null;
		
		board = skateBoardService.createSkateBoard(skateBoard);
		if(board != null && board.getId() > 0) {
			status =	Response.Status.CREATED ;
		}
		else {
			status = Response.Status.INTERNAL_SERVER_ERROR;
		}
		return Response
			      .status(status)
			      .entity(board)
			      .build();
	}
	
	
	@Path("/{id}")
	@PUT
	public Response changeBoardAvailability(@PathParam("id")Integer skateboardId) {
		
		boolean isUpdated = skateBoardService.changeBoardAvailability(skateboardId);
		Status status = null;
		if(isUpdated) {
			
			status =	Response.Status.OK ;
		}
		else {
			status = Response.Status.NOT_FOUND;
		}
		return Response
			      .status(status)
			      .build();
	}
	
	



}