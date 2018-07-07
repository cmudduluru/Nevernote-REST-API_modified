package com.NevernoteRESTAPI.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.NevernoteRESTAPI.Model.Note;
import com.NevernoteRESTAPI.View.NoteService;


//This Spring MVC - Controller layer exposes the Note REST methods and 
//maps the URI to the methods that perform the CRUD operations
@RestController
public class NoteController {
	
	// Injecting the dependency here
	@Autowired
	private NoteService noteService;
	
	// *** this method calls the noteService createNote method to create 
	// a note using the contents provided for the specified Notebook *** 
	@RequestMapping(method = RequestMethod.POST, value="/notebooks/{notebookName}/notes")
	public void createNote(@RequestBody Note note, @PathVariable String notebookName) {
			noteService.createNote(notebookName, note);
	}
	
	// *** this method returns back the list of Notes associated to a Notebook *** 
	@RequestMapping(method = RequestMethod.GET, value = "/notebooks/{notebookName}/notes")
	public List<Note> retreiveAllNotes(@PathVariable String notebookName){
			return noteService.retreiveAllNotes(notebookName);	
	}
	
	// *** this method returns the Note contents for the specified Note title from a Notebook *** 
	@RequestMapping(method = RequestMethod.GET, value = "/notebooks/{notebookName}/notes/{title}")
	public Note retreiveNote(@PathVariable String notebookName, @PathVariable String title) {
		return noteService.retreiveNote(notebookName, title);
	}
	
	// *** this method returns the filtered Notes that has the given tag; notes associated to a Notebook *** 
	@RequestMapping(method = RequestMethod.GET, value = "/notebooks/{notebookName}/{tag}")
	public List<Note> FilterNotesbyTag(@PathVariable String notebookName, @PathVariable String tag){
		
		return noteService.FilterNotesbyTag(notebookName, tag);		
	}
	
	// *** this method calls the noteService updateNote method to update 
	// a specified note using the contents provided *** 
	@RequestMapping(method = RequestMethod.PUT, value = "/notebooks/{notebookName}/notes/{title}")
	public void updateNote(@RequestBody Note note, @PathVariable String notebookName ,@PathVariable String title) {
		noteService.updateNote(title, note, notebookName);
	}

	// *** this method calls the noteService deleteNote method to 
	// delete a Note for the specified Note title *** 
	@RequestMapping(method = RequestMethod.DELETE, value = "/notebooks/{notebookName}/notes/{title}")
	public void deleteNote(@PathVariable String notebookName, @PathVariable String title) {
		noteService.deleteNote(notebookName, title);
	}

}
