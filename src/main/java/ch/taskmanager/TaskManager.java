package ch.taskmanager;

import ch.taskmanager.process.Process;
import ch.taskmanager.process.Processes;

public class TaskManager {
	
	private Processes list;

	public TaskManager(Processes list) {
		this.list = list;
	}

	public void add(Process process) {
		if(list.add(process)) {
			process.start();
		}
	}
	
	public int size() {
		return list.size();
	}
}
