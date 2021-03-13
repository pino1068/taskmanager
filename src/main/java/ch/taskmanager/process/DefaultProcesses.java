package ch.taskmanager.process;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class DefaultProcesses implements Processes {
	
	private Queue<Process> list;

	public DefaultProcesses(int capacity) {
		list = new ArrayBlockingQueue<>(capacity);
	}

	@Override
	public void add(Process process) {
		list.add(process);
	}

	@Override
	public int size() {
		return list.size();
	}

}
