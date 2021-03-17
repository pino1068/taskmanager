package ch.taskmanager;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ch.taskmanager.process.Process;

public class TaskManagerListTest {
	
	private TaskManager taskManager;

	@Before
	public void setup() {
		taskManager = TaskManager.createDefault(4);
		taskManager.add(new Process(2, Process.priorities.LOW));
		taskManager.add(new Process(3, Process.priorities.HIGH));
		taskManager.add(new Process(1, Process.priorities.MEDIUM));
	}

	@Test
	public void list() {
		
		List<Process> list = taskManager.list();
		
		assertEquals(2, list.get(0).getPid());
		assertEquals(3, list.get(1).getPid());
		assertEquals(1, list.get(2).getPid());
	}

	@Test
	public void listByTime() {

		List<Process> list = taskManager.listByTime();
		
		assertEquals(2, list.get(0).getPid());
		assertEquals(3, list.get(1).getPid());
		assertEquals(1, list.get(2).getPid());
	}

	@Test
	public void listByPriority() {

		List<Process> list = taskManager.listByPriority();
		
		assertEquals(3, list.get(0).getPid());
		assertEquals(1, list.get(1).getPid());
		assertEquals(2, list.get(2).getPid());
	}

	@Test
	public void listById() {

		List<Process> list = taskManager.listById();
		
		assertEquals(1, list.get(0).getPid());
		assertEquals(2, list.get(1).getPid());
		assertEquals(3, list.get(2).getPid());
	}
}
