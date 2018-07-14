package com.NevernoteRESTAPI.View;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.NevernoteRESTAPI.ExceptionHandling.CustomException;
import com.NevernoteRESTAPI.Model.Note;

//Spring MVC service layer that performs the logic and operations for the Note REST API calls
@Service
public class NoteService {
	
	@Autowired
	private NotebookService notebookService;
	
	//Creating a HashMap object
	public HashMap<String, List<Note>> noteHashMap = new HashMap<String, List<Note>>();
	
        // *** this method creates a Note and sets the created time stamp *** 
	public void createNote(String notebookName, Note note) {
				
		checkIfgivenNotebookExistsInNotebookHashMap(notebookName);
		chechIfgivenNoteTitleIsEmptyinJSON(note);
			
		if(!noteHashMap.containsKey(notebookName)) {
			note.setCreatedTime(LocalDateTime.now());
			noteHashMap.put(notebookName, Arrays.asList(note));
		}
		else {
			 //Getting Collection of values from HashMap
			Collection<Note> values = noteHashMap.get(notebookName);
			
			//Creating an ArrayList of values
			ArrayList<Note> listOfValues = new ArrayList<Note>(values);
		
			checkIfgivenNoteAlreadyExistsInNoteHashMap(listOfValues, note);
			
			// create the new note 
			note.setCreatedTime(LocalDateTime.now());
            listOfValues.add(note);
			noteHashMap.put(notebookName, listOfValues);	
		}	
	}

	// *** this method retrieves all the notes specified for a notebook name *** 
	public List<Note> retreiveAllNotes(String notebookName) {
		checkIfgivenNotebookExistsInNotebookHashMap(notebookName);	
		return noteHashMap.get(notebookName);	
 }
		

	// *** this method retrieves the Note contents for the specified Note title *** 
	public Note retreiveNote(String notebookName, String noteTitle) {
		checkIfgivenNotebookExistsInNotebookHashMap(notebookName);
		checkIfgivenNoteExistsInNoteHashMap(notebookName, noteTitle);
	      return noteHashMap.get(notebookName).stream().filter(n->n.getTitle().equals(noteTitle)).findFirst().get();
	}
	
	
	// *** this method retrieves available Notes filtered using the tag *** 
	public List<Note> FilterNotesbyTag(String notebookName, String tag) {
		checkIfgivenNotebookExistsInNotebookHashMap(notebookName);
		
		//Getting Collection of values from HashMap
		Collection<Note> values = noteHashMap.get(notebookName);
		
		//Creating an ArrayList of values
		ArrayList<Note> listOfValues = new ArrayList<Note>(values);
		
		List<Note> filteredNotes = new ArrayList<>(Arrays.asList());
		
			for( int i=0; i<listOfValues.size(); i++) {
				Note n = listOfValues.get(i);		
				if(Arrays.asList(n.getTags()).contains(tag)) {
				filteredNotes.add(n);
				}					
		}
			return filteredNotes;
	}
	

	// *** this method updates the Note contents for a specified Note title
	// and sets the last modified time stamp *** 
	public void updateNote(String noteTitle, Note note, String notebookName) {
		checkIfgivenNotebookExistsInNotebookHashMap(notebookName);
		checkIfgivenNoteExistsInNoteHashMap(notebookName, noteTitle);
		chechIfgivenNoteTitleIsEmptyinJSON(note);

		//Getting Collection of values from HashMap
		Collection<Note> values = noteHashMap.get(notebookName);
		
		//Creating an ArrayList of values
		ArrayList<Note> listOfValues = new ArrayList<Note>(values);
		
		for(int i=0; i<listOfValues.size();i++) {
			Note n = listOfValues.get(i);
			LocalDateTime dt = n.getCreatedTime();
				if(n.getTitle().equals(noteTitle)) {
					note.setCreatedTime(dt);
					note.setLastModifiedtime(LocalDateTime.now());
					listOfValues.set(i, note);
					noteHashMap.put(notebookName, listOfValues);
				}
			}    
	}
	

	// *** this method deletes the specified Note *** 
	public void deleteNote(String notebookName, String title) {
		checkIfgivenNotebookExistsInNotebookHashMap(notebookName);
		checkIfgivenNoteExistsInNoteHashMap(notebookName, title);
	
		Collection<Note> values = noteHashMap.get(notebookName);
		ArrayList<Note> listOfValues = new ArrayList<Note>(values);
		listOfValues.removeIf(n->n.getTitle().equals(title));
		noteHashMap.put(notebookName, listOfValues);
	}
	
	   // *** Below are the methods that handles exceptions ***
	   // If given notebook name in the request URI doesn't exist, then throw an exception
		private void checkIfgivenNotebookExistsInNotebookHashMap(String notebookName) {
			if(!notebookService.notebookHashMap.containsKey(notebookName)) {
				throw new CustomException("Given notebook '" + notebookName + "' doesn't exist, enter a valid Notebook name");
			}
			
		}
		
		// If the given note has null value in the note title, then throw an exception 
		private void chechIfgivenNoteTitleIsEmptyinJSON(Note note) {
			if(note.getTitle().isEmpty()) {
				throw new CustomException("the JSON entered doesn't have a value for the title, enter a value for title to create a note");
			}
			
		}
		
		// If the given note already exists with the same title, then throw an exception to enter a different title
		private void checkIfgivenNoteAlreadyExistsInNoteHashMap(ArrayList<Note> listOfnotes, Note note){
			for(Note n: listOfnotes) {
				if(n.getTitle().equals(note.getTitle())) {	
					throw new CustomException("Given note '" + note.getTitle() + "' already exists, so create note with a different name");
				}	
			}	
			
		}
		
		// if the given note doesn't exist then, throw resource not found exception
		void checkIfgivenNoteExistsInNoteHashMap(String notebookName, String noteTitle){
			 //Getting Collection of values from HashMap
			Collection<Note> values = noteHashMap.get(notebookName);
			//Creating an ArrayList of values
			ArrayList<Note> listOfValues = new ArrayList<Note>(values);
			ArrayList<String> noteTitles = new ArrayList<>();
			for(Note n: listOfValues) {
				noteTitles.add(n.getTitle());
			}
			
			if(!noteTitles.contains(noteTitle))
			{
				throw new ResourceNotFoundException("Given note title '" + noteTitle + "' doesn't exist");
			}
			
		}
}
