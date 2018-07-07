package com.NevernoteRESTAPI;

import static org.junit.Assert.*;

import javax.ws.rs.core.MediaType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.NevernoteRESTAPI.Controller.NotebookController;
import com.NevernoteRESTAPI.View.NotebookService;

//Unit tests for NotebookController using JUnit, WebMvcTest, SpringBootTest, Mockito
@RunWith(SpringRunner.class)
@WebMvcTest(NotebookController.class)
public class UnitTestNotebookController {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private NotebookService notebookService;
	
    com.NevernoteRESTAPI.Model.Notebook mockNotebook = new com.NevernoteRESTAPI.Model.Notebook("Maths", "This is Maths Notebook");
    String exampleNotebookJson = "{\"name\":\"Maths\",\"description\":\"This is Maths Notebook\"}";

 // Unit testing the createNotebook method
	@Test
	public void createNotebookTest() throws Exception {
		Mockito.doNothing().when(notebookService).createNotebook(mockNotebook);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/notebooks").accept(MediaType.APPLICATION_JSON)
				.content(exampleNotebookJson)
				.contentType(MediaType.APPLICATION_JSON);
		
	    MvcResult result = mockMvc.perform(requestBuilder).andReturn(); 
	    
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}
	
	// Unit testing the retreiveNotebook method 
		@Test
		public void retrieveNotebookTest() throws Exception {
		Mockito.when(notebookService.retreiveNotebook(Mockito.anyString())).thenReturn(mockNotebook);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/notebooks/notebookname").accept(
				MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			
			 JSONAssert.assertEquals(exampleNotebookJson, result.getResponse()
						.getContentAsString(), false);

		}
		
		// Unit testing the deleteNotebook method 
		@Test
		public void deleteNotebookTest() throws Exception {
			Mockito.doNothing().when(notebookService).deleteNotebook(Mockito.anyString());		
			RequestBuilder requestBuilder = MockMvcRequestBuilders.delete
					("/notebooks/notebookName").accept(MediaType.APPLICATION_JSON)
					.content(exampleNotebookJson)
					.contentType(MediaType.APPLICATION_JSON);
			
		    MvcResult result = mockMvc.perform(requestBuilder).andReturn(); 
		    
			MockHttpServletResponse response = result.getResponse();
			
			assertEquals(HttpStatus.OK.value(), response.getStatus());

		}
}
