package ch.taskmanager;

public class Process {
	
	public static enum priorities {
		LOW, MEDIUM, HIGH
	}

	final int pid;
	final priorities priority;

	public Process(int PID, priorities priority) {
		pid = PID;
		this.priority = priority;
	}
}
