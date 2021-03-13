package ch.taskmanager;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TaskManagerTest {

	@Test
	public void createWithCapacity() throws Exception {
		TaskManager taskManager = new TaskManager(1);
		
		assertEquals(0, taskManager.size());
	}

	@Test
	public void createProcess() throws Exception {
		Process process = new Process(1, Process.priorities.LOW);
		
		assertEquals(1, process.pid);
		assertEquals(Process.priorities.LOW, process.priority);
	}

	@Test
	public void addProcess() throws Exception {
		TaskManager taskManager = new TaskManager(1);
		
		taskManager.add(oneProcess(1));
		
		assertEquals(1, taskManager.size());
	}

	@Test(expected = IllegalStateException.class)
	public void exeedCapacity() throws Exception {
		TaskManager taskManager = new TaskManager(1);
	
		taskManager.add(oneProcess(1));
		taskManager.add(oneProcess(2));
		
	}

	private Process oneProcess(int PID) {
		return new Process(PID, Process.priorities.LOW);
	}
}
