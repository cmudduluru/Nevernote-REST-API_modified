package com.NevernoteRESTAPI;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.NevernoteRESTAPI.Controller.NotebookController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTestNotebookController {

	@Autowired
	private NotebookController notebookController;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void notebookSmokeTest() throws Exception{
		assertThat(notebookController).isNotNull();
	}

}
