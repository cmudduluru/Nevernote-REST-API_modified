package com.NevernoteRESTAPI.View;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.NevernoteRESTAPI.ExceptionHandling.CustomException;
import com.NevernoteRESTAPI.ExceptionHandling.ResourceNotFoundException;
import com.NevernoteRESTAPI.Model.Notebook;

// Spring MVC service layer that performs the logic and operations for the Notebook REST API Calls
@Service
public class NotebookService {
	
	public HashMap<String, Notebook> notebookHashMap = new HashMap<>();


	// *** this method sends the welcome note to the base URI *** 
	public String welcomeNote() {
		return "Hello, this is NeverNote REST API";
	}
	

	// *** this method creates a Notebook *** 
	public void createNotebook(Notebook notebook) {
		// if the given notebook has empty name, then don't create the notebook
		if(!notebook.getName().isEmpty())
		{
		// create a notebook only if the given notebook doesn't exist in the notebookHashMap
		if(!notebookHashMap.containsKey(notebook.getName())) {
			notebookHashMap.put(notebook.getName(), notebook);	
			}
		
		else {
			String name1 = notebook.getName();
			throw new CustomException("Given notebook name '" + name1 + "' already exists, create notebook with a different name");
		}
		
		}
		
		else throw new CustomException("the JSON entered doesn't have a value for name, give a name to create Notebook");
			
	}
				
	
	// *** this method returns the Notebook contents for the specified Notebook name *** 
	public Notebook retreiveNotebook(String name) {
		if(!notebookHashMap.containsKey(name))
		{
			throw new ResourceNotFoundException("Given notebook name '" + name + "' doesn't exist");
		}
		else {
			return notebookHashMap.get(name);
		}
	}
	

	// *** this method returns all the notebooks *** 
	public HashMap<String, Notebook> retreiveAllNotebooks() {
		return notebookHashMap;
	}
	

	// *** this method deletes the specified Notebook *** 
	public void deleteNotebook(String name) {
		
		if(!notebookHashMap.containsKey(name))
		{
			throw new ResourceNotFoundException("Given notebook name '" + name + "' doesn't exist");
		}
		else {
			notebookHashMap.remove(name);
		}
		
	}
	
}
