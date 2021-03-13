package ch.taskmanager;

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
				.sorted(Comparator.comparing(Process::getPriority).reversed())
				);
	}

	public List<Process> listById() {
		return toList(
				list.stream()
				.sorted(Comparator.comparing(Process::getPid))
				);
	}

	private List<Process> toList(Stream<Process> stream) {
		return stream.collect(Collectors.toList());
	}
}
