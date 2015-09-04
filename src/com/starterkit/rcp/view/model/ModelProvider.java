package com.starterkit.rcp.view.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ModelProvider {
	INSTANCE;
	private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(
			this);
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}
	protected void firePropertyChange(String propertyName, Object oldValue,
			Object newValue) {
		changeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}
	
	private List<Task> tasks;

	private ModelProvider() {
		tasks = new ArrayList<Task>();
	}

	public List<Task> getTasks() {
		return tasks;
	}
	
	public void addTask(Task task){
		firePropertyChange("tasks", tasks, tasks.add(task));
	}
	
	public void removeTask(Task task){
		List<Task> oldTasks = new ArrayList<>();
		oldTasks.addAll(tasks);
		tasks.remove(tasks.indexOf(task));
		firePropertyChange("tasks", oldTasks, tasks);
	}
	


}
