package ch.taskmanager.process;

import java.util.stream.Stream;

public interface Processes {

	boolean add(Process process);
	Stream<Process> stream();
}
