package ch.taskmanager;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import ch.taskmanager.process.DefaultProcesses;
import ch.taskmanager.process.Process;

public class TaskManagerProcessTest {

	@Test
	public void list() throws Exception {
		TaskManager tasks = new TaskManager(new DefaultProcesses(3));
		tasks.add(new Process(2, Process.priorities.LOW));
		tasks.add(new Process(3, Process.priorities.HIGH));
		tasks.add(new Process(1, Process.priorities.LOW));
		
		List<Process> list = tasks.list();
		
		assertEquals(3, list.size());
		assertEquals(2, list.get(0).getPid());
		assertEquals(3, list.get(1).getPid());
		assertEquals(1, list.get(2).getPid());
	}

	@Test
	public void listByTime() throws Exception {
		TaskManager tasks = new TaskManager(new DefaultProcesses(3));
		tasks.add(new Process(2, Process.priorities.LOW));
		tasks.add(new Process(3, Process.priorities.HIGH));
		tasks.add(new Process(1, Process.priorities.MEDIUM));

		List<Process> list = tasks.listByTime();
		
		assertEquals(3, list.size());
		assertEquals(2, list.get(0).getPid());
		assertEquals(3, list.get(1).getPid());
		assertEquals(1, list.get(2).getPid());
	}

	@Test
	public void listByPriority() throws Exception {
		TaskManager tasks = new TaskManager(new DefaultProcesses(3));
		tasks.add(new Process(2, Process.priorities.LOW));
		tasks.add(new Process(3, Process.priorities.HIGH));
		tasks.add(new Process(1, Process.priorities.MEDIUM));

		List<Process> list = tasks.listByPriority();
		
		assertEquals(3, list.size());
		assertEquals(3, list.get(0).getPid());
		assertEquals(1, list.get(1).getPid());
		assertEquals(2, list.get(2).getPid());
	}

	@Test
	public void listById() throws Exception {
		TaskManager tasks = new TaskManager(new DefaultProcesses(3));
		tasks.add(new Process(2, Process.priorities.LOW));
		tasks.add(new Process(3, Process.priorities.HIGH));
		tasks.add(new Process(1, Process.priorities.MEDIUM));

		List<Process> list = tasks.listById();
		
		assertEquals(3, list.size());
		assertEquals(1, list.get(0).getPid());
		assertEquals(2, list.get(1).getPid());
		assertEquals(3, list.get(2).getPid());
	}
}
