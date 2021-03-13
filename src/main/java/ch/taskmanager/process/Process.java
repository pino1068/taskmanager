package ch.taskmanager.process;

public class Process {
	
	public static enum priorities {
		LOW, MEDIUM, HIGH
	}

	public final int pid;
	public final priorities priority;
	public boolean alive;

	public Process(int PID, priorities priority) {
		pid = PID;
		this.priority = priority;
	}

	public void kill() {
		alive = false;
	}

	public void start() {
		alive = true;
	}
}
