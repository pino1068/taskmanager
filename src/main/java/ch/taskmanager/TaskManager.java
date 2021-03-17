package ch.taskmanager;

import static java.util.Comparator.comparing;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	
	public List<Process> list() {
		return toList(list.stream());
	}

	public List<Process> listByTime() {
		return list();
	}

	public List<Process> listByPriority() {
		return toList(
				list.stream()
				.sorted(comparing(Process::getPriority).reversed())
				);
	}

	public List<Process> listById() {
		return toList(
				list.stream()
				.sorted(comparing(Process::getPid))
				);
	}

	private List<Process> toList(Stream<Process> stream) {
		return stream.collect(Collectors.toList());
	}
	
	public void killByPid(int pid) {
		list
		.stream()
		.filter(it -> it.getPid() == pid)
		.findFirst()
		.ifPresent(it -> {
			it.kill();
		});
	}
	
	public void killByPriority(int priority) {
		list
		.stream()
		.filter(it -> it.getPriority() == priority)
		.forEach(it -> {
			it.kill();
		});
	}
	
	public void killAll() {
		list
		.stream()
		.forEach(it -> {
			it.kill();
		});
	}

	public static TaskManager createDefault(int capacity) {
		return new TaskManager(Processes.blocking(capacity));
	}

	public static TaskManager createFIFO(int capacity) {
		return new TaskManager(Processes.FIFO(capacity));
	}

	public static TaskManager createPriorityBased(int capacity) {
		return new TaskManager(Processes.priorityBased(capacity));
	}
}
