package ch.taskmanager;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class TaskManager {
	
	private Queue<Process> list;

	public TaskManager(int capacity) {
		list = new ArrayBlockingQueue<Process>(capacity);
	}

	public void add(Process process) {
		list.add(process);
	}
	
	public int size() {
		return list.size();
	}

}
