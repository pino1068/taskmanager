package ch.taskmanager;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ch.taskmanager.process.Process;

public class TaskManagerDefaultTest {
	
	private TaskManager taskManager;

	@Before
	public void setup() {
		taskManager = TaskManager.createDefault(1);
	}

	@Test
	public void createWithCapacity() {
		assertEquals(0, taskManager.list().size());
	}

	@Test
	public void createProcess() {
		Process process = new Process(1, Process.priorities.LOW);
		
		assertEquals(1, process.getPid());
		assertEquals(Process.priorities.LOW, process.getPriority());
	}

	@Test
	public void addProcess() {
		Process process = aProcess(1);
		taskManager.add(process);
		
		assertEquals(1, taskManager.list().size());
		assertEquals(true, process.isAlive());
	}

	@Test(expected = IllegalStateException.class)
	public void exceedCapacityWithDefault() {
		taskManager.add(aProcess(1));
		taskManager.add(aProcess(2));
	}

	private Process aProcess(int PID) {
		return new Process(PID, Process.priorities.LOW);
	}
}
