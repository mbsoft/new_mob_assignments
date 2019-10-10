package com.skateboard.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.skateboard.model.SkateBoard;
import com.skateboard.service.SkateBoardService;

@RunWith(MockitoJUnitRunner.class)
public class SkateBoardResourceTest {
	
	@InjectMocks
	SkateBoardResource skateBoardResource;
	
	@Mock
	SkateBoardService skateBoardService;
	
	ArrayList<SkateBoard> skateBoards = new ArrayList<>();
	
	@Before
	public void setup() {
		
		
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
		
		skateBoards.add(skateboard1);
		skateBoards.add(skateboard2);
		skateBoards.add(skateboard3);
		
	}
	

	@Test
	public void getSkateBoard_ReturnsSuccessfulCode_IfFound() {
		SkateBoard skateboard1 = new SkateBoard();
		skateboard1.setId(1);
		skateboard1.setOwnerName("Nandhinee");
		skateboard1.setBoardAvailable(true);
		
		int expectedResponse = 200;
		
		when(skateBoardService.getSkateBoardById(anyInt())).thenReturn(skateboard1);
		
		Response actualResponse = skateBoardResource.getSkateBoard(1);
		
		assertEquals(expectedResponse,actualResponse.getStatus());
		
	}
	
	@Test
	public void getSkateBoard_ReturnsNotFoundCode_IfSkateBoardIsNull() {
		SkateBoard skateboard1 = new SkateBoard();
		skateboard1.setId(1);
		skateboard1.setOwnerName("Nandhinee");
		skateboard1.setBoardAvailable(true);
		
		int expectedResponse = 404;
		
		when(skateBoardService.getSkateBoardById(anyInt())).thenReturn(null);
		
		Response actualResponse = skateBoardResource.getSkateBoard(1);
		
		assertEquals(expectedResponse,actualResponse.getStatus());
		
	}
	
	@Test
	public void getSkateBoard_ReturnsNotFoundCode_IfSkateBoardIdIsNotFound() {
		SkateBoard skateboard1 = new SkateBoard();
		
		int expectedResponse = 404;
		
		when(skateBoardService.getSkateBoardById(anyInt())).thenReturn(skateboard1);
		
		Response actualResponse = skateBoardResource.getSkateBoard(1);
		
		assertEquals(expectedResponse,actualResponse.getStatus());
		
	}

	@Test
	public void getSkateBoard_returnsCorrectSkateBoard() {
		SkateBoard skateboard1 = new SkateBoard();
		skateboard1.setId(1);
		skateboard1.setOwnerName("Nandhinee");
		skateboard1.setBoardAvailable(true);
		
		when(skateBoardService.getSkateBoardById(anyInt())).thenReturn(skateboard1);
		
		Response response = skateBoardResource.getSkateBoard(1);
		SkateBoard board = (SkateBoard) response.getEntity();
		
		
		assertEquals(board.getId(),skateboard1.getId());
	}
	
	@Test
	public void getAvailableBoards_returnsAvailableBoards() {
		ArrayList<SkateBoard> availableSkateBoards = new ArrayList<>();
		
		SkateBoard skateboard1 = new SkateBoard();
		skateboard1.setId(1);
		skateboard1.setOwnerName("Nandhinee");
		skateboard1.setBoardAvailable(true);
		
		SkateBoard skateboard2 = new SkateBoard();
		skateboard2.setId(3);
		skateboard2.setOwnerName("Naidhruv");
		skateboard2.setBoardAvailable(true);
		
		
		
		when(skateBoardService.getAvailableBoards()).thenReturn(availableSkateBoards);
		
		Response response = skateBoardResource.getAvailableBoards();
		
		@SuppressWarnings("unchecked")
		ArrayList<SkateBoard> responseBoardList = (ArrayList<SkateBoard>) response.getEntity();
		
		for(SkateBoard skateboard: responseBoardList) {
			assertEquals(true,skateboard.isBoardAvailable());
		}
	}
	
	@Test
	public void createSkateBoard_returnsCreatedSuccessfullyCode_WhenIdIsSet() {
		SkateBoard skateboard1 = new SkateBoard();
		skateboard1.setId(1);
		skateboard1.setOwnerName("Nandhinee");
		skateboard1.setBoardAvailable(true);
		
		int expectedResponse = 201;
		
		when(skateBoardService.createSkateBoard(Mockito.any())).thenReturn(skateboard1);
		
		Response actualResponse = skateBoardResource.createSkateBoard(skateboard1);
		
		assertEquals(expectedResponse,actualResponse.getStatus());
	}
	@Test
	public void createSkateBoard_returnsErrorCode_WhenCreationFailsAndIdIsNotSet() {
		SkateBoard skateboard1 = new SkateBoard();
		skateboard1.setOwnerName("Nandhinee");
		skateboard1.setBoardAvailable(true);
		
		int expectedResponse = 500;
		
		when(skateBoardService.createSkateBoard(Mockito.any())).thenReturn(skateboard1);
		
		Response actualResponse = skateBoardResource.createSkateBoard(skateboard1);
		
		assertEquals(expectedResponse,actualResponse.getStatus());
	}
	
	@Test
	public void changeBoardAvailability_returnsErrorCode_WhenUpdationFails() {
		int expectedResponse = 404;
		when(skateBoardService.changeBoardAvailability(anyInt())).thenReturn(false);
		
		Response actualResponse =skateBoardResource.changeBoardAvailability(1);
		assertEquals(expectedResponse,actualResponse.getStatus());
	}
	
	@Test
	public void changeBoardAvailability_returnsSuccessfulCode_WhenUpdationIsSuccessful() {
		int expectedResponse = 200;
		when(skateBoardService.changeBoardAvailability(anyInt())).thenReturn(true);
		
		Response actualResponse =skateBoardResource.changeBoardAvailability(1);
		assertEquals(expectedResponse,actualResponse.getStatus());
	}
}
