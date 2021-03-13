package ch.taskmanager.process;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

public class FIFOProcesses implements Processes {

	private Queue<Process> list = new LinkedList<>();
	private int capacity; 
	
	public FIFOProcesses(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public boolean add(Process process) {
		if(list.size() == capacity) {
			list.remove().kill();
		}
		list.add(process);
		return true;
	}

	@Override
	public Stream<Process> stream() {
		return list.stream();
	}
}
