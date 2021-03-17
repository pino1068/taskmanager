package ch.taskmanager.process;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Stream;

class BlockingProcesses implements Processes {
	
	private Queue<Process> list;

	public BlockingProcesses(int capacity) {
		list = new ArrayBlockingQueue<>(capacity);
	}

	@Override
	public boolean add(Process process) {
		list.add(process);
		return true;
	}

	@Override
	public Stream<Process> stream() {
		return list.stream();
	}

}
