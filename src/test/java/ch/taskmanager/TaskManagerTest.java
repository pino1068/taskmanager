package ch.taskmanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ch.taskmanager.process.DefaultProcesses;
import ch.taskmanager.process.FIFOProcesses;
import ch.taskmanager.process.PriorityBasedProcesses;
import ch.taskmanager.process.Process;

public class TaskManagerTest {

	@Test
	public void createWithCapacity() throws Exception {
		TaskManager taskManager = new TaskManager(new DefaultProcesses(1));
		
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
		TaskManager taskManager = new TaskManager(new DefaultProcesses(1));
		
		Process process = oneProcess(1);
		taskManager.add(process);
		
		assertEquals(1, taskManager.size());
		assertEquals(true, process.alive);
	}

	@Test(expected = IllegalStateException.class)
	public void exceedCapacityWithDefault() {
		TaskManager taskManager = new TaskManager(new DefaultProcesses(1));
	
		taskManager.add(oneProcess(1));
		taskManager.add(oneProcess(2));
	}
	
	@Test
	public void exceedCapacityWithFIFO() throws Exception {
		TaskManager taskManager = new TaskManager(new FIFOProcesses(1));
	
		Process process1 = oneProcess(1);
		taskManager.add(process1);
		Process process2 = oneProcess(2);
		taskManager.add(process2);
		
		assertFalse(process1.alive);
		assertTrue(process2.alive);
	}

	private Process oneProcess(int PID) {
		return new Process(PID, Process.priorities.LOW);
	}
}
