package ch.taskmanager;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ch.taskmanager.process.Process;

public class TaskManagerKillProcessTest {
	
	private TaskManager taskManager;

	@Before
	public void setup() {
		taskManager = TaskManager.createDefault(4);
		taskManager.add(new Process(2, Process.priorities.LOW));
		taskManager.add(new Process(3, Process.priorities.HIGH));
		taskManager.add(new Process(1, Process.priorities.MEDIUM));
	}

	@Test
	public void killById() {

		taskManager.killByPid(2);
		
		List<Process> list = taskManager.list();
		assertEquals(false, list.get(0).isAlive());
		assertEquals(true, list.get(1).isAlive());
		assertEquals(true, list.get(2).isAlive());
	}
	
	@Test
	public void killByPriority() {
		taskManager.add(new Process(4, Process.priorities.MEDIUM));

		taskManager.killByPriority(Process.priorities.MEDIUM);
		
		List<Process> list = taskManager.list();
		assertEquals(true, list.get(0).isAlive());
		assertEquals(true, list.get(1).isAlive());
		assertEquals(false, list.get(2).isAlive());
		assertEquals(false, list.get(3).isAlive());
	}

	@Test
	public void killAll() {

		taskManager.killAll();
		
		List<Process> list = taskManager.list();
		assertEquals(3, list.size());
		assertEquals(false, list.get(0).isAlive());
		assertEquals(false, list.get(1).isAlive());
		assertEquals(false, list.get(2).isAlive());
	}
}
