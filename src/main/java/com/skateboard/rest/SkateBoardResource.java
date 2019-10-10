package com.skateboard.rest;


import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
		SkateBoard skateboard1 = new SkateBoard();
		skateboard1.setId(1);
		skateboard1.setOwnerName("Nandhinee");
		skateboard1.setBoardAvailable(true);
		
		SkateBoard skateboard2 = new SkateBoard();
		skateboard2.setId(2);
		skateboard2.setOwnerName("Karthik");
		skateboard2.setBoardAvailable(false);
		
		SkateBoard skateboard3 = new SkateBoard();
		skateboard3.setId(3);
		skateboard3.setOwnerName("Naidhruv");
		skateboard3.setBoardAvailable(true);
		
		
		
		skateBoards = skateBoardService.getAvailableBoards();
		skateBoards.add(skateboard1);
		skateBoards.add(skateboard2);
		skateBoards.add(skateboard3);
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
	
	
	



}