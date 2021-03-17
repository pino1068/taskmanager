package ch.taskmanager.process;

import java.util.stream.Stream;

public interface Processes {

	boolean add(Process process);
	Stream<Process> stream();
	
	static Processes blocking(int capacity) {
		return new BlockingProcesses(capacity);
	}
	
	static Processes FIFO(int capacity) {
		return new FIFOProcesses(capacity);
	}
	
	static Processes priorityBased(int capacity) {
		return new PriorityBasedProcesses(capacity);
	}
}
