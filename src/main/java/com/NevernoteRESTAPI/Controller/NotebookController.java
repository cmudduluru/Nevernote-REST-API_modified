package com.NevernoteRESTAPI.Controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.NevernoteRESTAPI.Model.Notebook;
import com.NevernoteRESTAPI.View.NotebookService;
 
// This Spring MVC - Controller layer exposes the Notebook REST methods and 
// maps the URI to the methods that perform the CRUD operations
@RestController
public class NotebookController {

	// Injecting the dependency here
	@Autowired
	private NotebookService notebookService;
	
	// *** this method returns the welcome note to the base URI: http://localhost:9000 ***
		@RequestMapping(method = RequestMethod.GET, value = "/")
		public String retreiveNotebook()
		{
			return notebookService.welcomeNote();
		}
	
	// *** this method calls the notebookService createNotebook method to create 
	// a notebook using the contents provided ***
	@RequestMapping(method=RequestMethod.POST, value="/notebooks")
	public void createNotebook(@RequestBody Notebook notebook) {
		notebookService.createNotebook(notebook);	
	}
	
	// *** this method calls returns back a Notebook content to the URI for the specified Notebook name ***
	@RequestMapping(method = RequestMethod.GET, value = "/notebooks/{name}")
	public Notebook retreiveNotebook(@PathVariable String name)
	{
		return notebookService.retreiveNotebook(name);
	}
	
	// *** this method calls returns back all Notebooks to the URI ***
	@RequestMapping(method=RequestMethod.GET, value="/notebooks")
	public HashMap<String, Notebook> retreiveAllNotebooks(){
		return notebookService.retreiveAllNotebooks();
	}
	
	// *** this method calls the notebookService deleteNotebook method to 
	// delete a Notebook for the specified Notebook name ***
	@RequestMapping(method=RequestMethod.DELETE, value="/notebooks/{name}")
	public void deleteNotebook(@PathVariable String name) {
		notebookService.deleteNotebook(name);
	}
	
}
