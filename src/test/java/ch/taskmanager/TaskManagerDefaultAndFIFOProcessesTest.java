package ch.taskmanager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ch.taskmanager.process.DefaultProcesses;
import ch.taskmanager.process.FIFOProcesses;
import ch.taskmanager.process.PriorityBasedProcesses;
import ch.taskmanager.process.Process;

public class TaskManagerDefaultAndFIFOProcessesTest {

	@Test
	public void createWithCapacity() throws Exception {
		TaskManager taskManager = new TaskManager(new DefaultProcesses(1));
		
		assertEquals(0, taskManager.size());
	}

	@Test
	public void createProcess() throws Exception {
		Process process = new Process(1, Process.priorities.LOW);
		
		assertEquals(1, process.getPid());
		assertEquals(Process.priorities.LOW, process.getPriority());
	}

	@Test
	public void addProcess() throws Exception {
		TaskManager taskManager = new TaskManager(new DefaultProcesses(1));
		
		Process process = aProcess(1);
		taskManager.add(process);
		
		assertEquals(1, taskManager.size());
		assertEquals(true, process.isAlive());
	}

	@Test(expected = IllegalStateException.class)
	public void exceedCapacityWithDefault() {
		TaskManager taskManager = new TaskManager(new DefaultProcesses(1));
	
		taskManager.add(aProcess(1));
		taskManager.add(aProcess(2));
	}
	
	@Test
	public void exceedCapacityWithFIFO() throws Exception {
		TaskManager taskManager = new TaskManager(new FIFOProcesses(1));
	
		Process process1 = aProcess(1);
		taskManager.add(process1);
		Process process2 = aProcess(2);
		taskManager.add(process2);
		
		assertFalse(process1.isAlive());
		assertTrue(process2.isAlive());
	}

	private Process aProcess(int PID) {
		return new Process(PID, Process.priorities.LOW);
	}
}
