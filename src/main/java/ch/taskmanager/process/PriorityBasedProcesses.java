package ch.taskmanager.process;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

class PriorityBasedProcesses implements Processes {
	
	private List<Process> list;
	private int capacity;

	public PriorityBasedProcesses(int capacity) {
		this.capacity = capacity;
		list = new ArrayList<>();
	}

	@Override
	public boolean add(Process process) {
		if(list.size() == capacity) {
			if(list.stream().allMatch(it -> it.hasLowerPriorityThen(process))) {
				list.stream()
				.filter(it -> it.hasLowerPriorityThen(process))
				.sorted(Comparator.comparingInt(Process::getPid))
				.findFirst()
				.ifPresent(it -> {
					list.remove(it);
					list.add(process);
				});
			}
		}else {
			list.add(process);
		}
		return list.contains(process);
	}

	@Override
	public Stream<Process> stream() {
		return list.stream();
	}
}
