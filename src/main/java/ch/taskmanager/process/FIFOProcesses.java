package ch.taskmanager.process;

import java.util.LinkedList;
import java.util.Queue;

public class FIFOProcesses implements Processes {

	private Queue<Process> list = new LinkedList<>();
	private int capacity; 
	
	public FIFOProcesses(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public void add(Process process) {
		if(list.size() == capacity) {
			list.remove().kill();
		}
		list.add(process);
	}

	@Override
	public int size() {
		return list.size();
	}
}
