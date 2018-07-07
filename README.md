
                                        #NEVERNOTE -API
                                        
This project is a Nevernote REST API developed using Java and Maven.

| Base URI | http://localhost:9000/ |

This API provides the following resources mapped to the respective HTTP methods which supports creation, deletion of Notebooks and to retrieve a specific Notebook or all Notebooks. 

| HTTP Method | Available URI	 |   Summary |
| --- | --- | --- |
| POST | /notebooks | To create a notebook |
|  GET | /notebooks/{notebookName} | To get a specific notebook detail |
|  GET | /notebooks | To get the details of all the notebooks |
| DELETE | /notebooks/{notebookName} | To delete a specific notebook |


 Example Model to create a new notebook using POST method and then use GET method to see the contents:
{
  "name": "string"
  "description": "string",
}

This API also provides the following resources mapped to the respective HTTP methods which supports CRUD operations for notes for a specified Notebook.

| HTTP Method | Available URI	 |   Summary |
| --- | --- | --- |
| POST | /notebooks/{notebookName}/notes | To create a note in the specified notebook |
| GET | /notebooks/{notebookName}/notes/{title} | To get a specific note detail in the specified notebook |
| GET | /notebooks/{notebookName}/notes | To get all the notes associated to the specified notebook |
| GET | /notebooks/{notebookName}/{tag} | Given a notebook, get filtered list of notes that contain the given tag string |
| PUT | /notebooks/{notebookName}/notes/{title} | To update a specific note associated with the given notebook |
| DELETE | /notebooks/{notebookName}/notes/{title} | To delete a specific note associated with the given notebook |


Example Model to create a new note using POST method for a specific Notebook
{
  "title": "string",
  "body": "string",
  "tags": ["string", "string", "string"]
  "createdTime": null,
  "lastModified": null

}

Here is the example model that can be seen when GET method is used:
// "tags" - This is a string Array
// "createdTime" - This is set with the LocalDateTime value when a new note is created
// "lastModified" - This is set with the LocalDateTime value when a note is updated
{
  "title": "string",
  "body": "string",
  "tags": [ "string", "string", "string" ], 
  "createdTime": "LocalDateTime",
  "lastModified": null
}

#Tools, technologies used for developing this API: 

Java 1.8.0_171
Maven
Spring framework 2.0.3
JUnit for unit tests
Spring Tool Suite – 3.9.4
Postman HTTP client to test the REST calls
