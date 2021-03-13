package ch.taskmanager;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ch.taskmanager.process.PriorityBasedProcesses;
import ch.taskmanager.process.Process;

public class TaskManagerPriorityBasedTest {

	@Test
	public void exceedCapacity_SkipNewLowerPriority() {
		TaskManager taskManager = new TaskManager(new PriorityBasedProcesses(1));
		
		Process process1 = new Process(1, Process.priorities.HIGH);
		taskManager.add(process1);
		Process process2 = new Process(2, Process.priorities.LOW);
		taskManager.add(process2);
		
		assertTrue(process1.isAlive());
		assertFalse(process2.isAlive());
	}

	@Test
	public void exceedCapacity_SkipNewSamePriority() {
		TaskManager taskManager = new TaskManager(new PriorityBasedProcesses(1));
		
		Process process1 = new Process(1, Process.priorities.HIGH);
		taskManager.add(process1);
		Process process2 = new Process(2, Process.priorities.HIGH);
		taskManager.add(process2);
		
		assertTrue(process1.isAlive());
		assertFalse(process2.isAlive());
	}

	@Test
	public void exceedCapacity_AddNewHigherPriority() {
		TaskManager taskManager = new TaskManager(new PriorityBasedProcesses(1));
		
		Process process1 = new Process(1, Process.priorities.LOW);
		taskManager.add(process1);
		Process process2 = new Process(2, Process.priorities.HIGH);
		taskManager.add(process2);
		
		assertTrue(process1.isAlive());
		assertTrue(process2.isAlive());
	}

}
