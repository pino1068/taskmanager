package ch.taskmanager;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ch.taskmanager.process.DefaultProcesses;
import ch.taskmanager.process.Process;

public class TaskManagerProcessTest {
	
	private TaskManager taskManager;

	@Before
	public void setup() {
		taskManager = new TaskManager(new DefaultProcesses(4));
		taskManager.add(new Process(2, Process.priorities.LOW));
		taskManager.add(new Process(3, Process.priorities.HIGH));
		taskManager.add(new Process(1, Process.priorities.MEDIUM));
	}

	@Test
	public void list() throws Exception {
		
		List<Process> list = taskManager.list();
		
		assertEquals(2, list.get(0).getPid());
		assertEquals(3, list.get(1).getPid());
		assertEquals(1, list.get(2).getPid());
	}

	@Test
	public void listByTime() throws Exception {

		List<Process> list = taskManager.listByTime();
		
		assertEquals(2, list.get(0).getPid());
		assertEquals(3, list.get(1).getPid());
		assertEquals(1, list.get(2).getPid());
	}

	@Test
	public void listByPriority() throws Exception {

		List<Process> list = taskManager.listByPriority();
		
		assertEquals(3, list.get(0).getPid());
		assertEquals(1, list.get(1).getPid());
		assertEquals(2, list.get(2).getPid());
	}

	@Test
	public void listById() throws Exception {

		List<Process> list = taskManager.listById();
		
		assertEquals(1, list.get(0).getPid());
		assertEquals(2, list.get(1).getPid());
		assertEquals(3, list.get(2).getPid());
	}
	
	@Test
	public void killById() throws Exception {

		taskManager.killByPid(2);
		
		List<Process> list = taskManager.list();
		assertEquals(false, list.get(0).isAlive());
		assertEquals(true, list.get(1).isAlive());
		assertEquals(true, list.get(2).isAlive());
	}
	
	@Test
	public void killByPriority() throws Exception {
		taskManager.add(new Process(4, Process.priorities.MEDIUM));

		taskManager.killByPriority(Process.priorities.MEDIUM);
		
		List<Process> list = taskManager.list();
		assertEquals(true, list.get(0).isAlive());
		assertEquals(true, list.get(1).isAlive());
		assertEquals(false, list.get(2).isAlive());
		assertEquals(false, list.get(3).isAlive());
	}

	@Test
	public void killAll() throws Exception {

		taskManager.killAll();
		
		List<Process> list = taskManager.list();
		assertEquals(3, list.size());
		assertEquals(false, list.get(0).isAlive());
		assertEquals(false, list.get(1).isAlive());
		assertEquals(false, list.get(2).isAlive());
	}
}
