package ch.taskmanager;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ch.taskmanager.process.FIFOProcesses;
import ch.taskmanager.process.Process;

public class TaskManagerFIFOTest {

	@Test
	public void exceedCapacityWithFIFO() {
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
