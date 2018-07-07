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
		
		// create note to the given notebook if it exists in notebookHashMap, otherwise throw exception
		if(notebookService.notebookHashMap.containsKey(notebookName)) {
		
		if(!noteHashMap.containsKey(notebookName)) {
			note.setCreatedTime(LocalDateTime.now());
			noteHashMap.put(notebookName, Arrays.asList(note));
		}
		else {
			//Getting Collection of values from HashMap
			Collection<Note> values = noteHashMap.get(notebookName);
			
			//Creating an ArrayList of values
			ArrayList<Note> listOfValues = new ArrayList<Note>(values);
			note.setCreatedTime(LocalDateTime.now());
            listOfValues.add(note);
			noteHashMap.put(notebookName, listOfValues);
		}	
		
		}
		
		else throw new CustomException("Given notebook '" + notebookName + "' doesn't exist, so cannot create a note");
	
	}

	// *** this method retrieves all the notes specified for a notebook name *** 
	public List<Note> retreiveAllNotes(String notebookName) {
	// check if the given notebook exists in notebookHashMap, otherwise throw exception
	if(notebookService.notebookHashMap.containsKey(notebookName)) {
		return noteHashMap.get(notebookName);	
	}
	else throw new CustomException("Given notebook '" + notebookName + "' doesn't exist, so cannot retrive any notes");	
   }
		

	// *** this method retrieves the Note contents for the specified Note title *** 
	public Note retreiveNote(String notebookName, String noteTitle) {
		// check if the given notebook exists in notebookHashMap, otherwise throw exception
		if(notebookService.notebookHashMap.containsKey(notebookName)) {
	      return noteHashMap.get(notebookName).stream().filter(n->n.getTitle().equals(noteTitle)).findFirst().get();
		}
		
		else throw new CustomException("Given notebook '" + notebookName + "' doesn't exist, so cannot retrive any note");	
	}
	
	
	// *** this method retrieves available Notes filtered using the tag *** 
	public List<Note> FilterNotesbyTag(String notebookName, String tag) {
		// check if the given notebook exists in notebookHashMap, otherwise throw exception
	    if(notebookService.notebookHashMap.containsKey(notebookName)) {
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
			return filteredNotes ;
	}
		else throw new CustomException("Given notebook '" + notebookName + "' doesn't exist, so cannot retreive anything");
	}
	

	// *** this method updates the Note contents for a specified Note title
	// and sets the last modified time stamp *** 
	public void updateNote(String noteTitle, Note note, String notebookName) {
		
		// check if the given notebook exists in notebookHashMap, otherwise throw exception
	    if(notebookService.notebookHashMap.containsKey(notebookName)) {
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
	    else throw new CustomException("Given notebook '" + notebookName + "' doesn't exist, so cannot update anything");
	}
	

	// *** this method deletes the specified Note *** 
	public void deleteNote(String notebookName, String title) {
		// check if the given notebook exists in notebookHashMap, otherwise throw exception
	    if(notebookService.notebookHashMap.containsKey(notebookName)) {
		Collection<Note> values = noteHashMap.get(notebookName);
		ArrayList<Note> listOfValues = new ArrayList<Note>(values);
		listOfValues.removeIf(n->n.getTitle().equals(title));
		noteHashMap.put(notebookName, listOfValues);
				}
	    
	    else throw new CustomException("Given notebook '" + notebookName + "' doesn't exist, so cannot delete anything");
	}

}
