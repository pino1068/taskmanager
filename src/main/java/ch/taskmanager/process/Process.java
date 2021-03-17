package ch.taskmanager.process;

public class Process {
	
	public interface priorities {
		static final int LOW = 1;
		static final int MEDIUM = 2;
		static final int HIGH = 3;
	}

	private final int pid;
	private final int priority;
	private boolean alive;

	public int getPid() {
		return pid;
	}

	public int getPriority() {
		return priority;
	}

	public boolean isAlive() {
		return alive;
	}

	public Process(int PID, int priority) {
		pid = PID;
		this.priority = priority;
	}

	public void kill() {
		alive = false;
	}

	public void start() {
		alive = true;
	}

	public boolean hasLowerPriorityThen(Process process) {
		return getPriority() < process.getPriority();
	}
}
