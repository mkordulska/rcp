package com.starterkit.rcp.view.model;

public class Task extends ModelObject {
    private String task;

    private boolean done;
    
    public Task(){
    	
    }

    public Task(String task, boolean done){
    	this.task = task;
    	this.done = done;
    }
    
    public String getTask() {
          return task;
    }

    public void setTask(String task) {
          firePropertyChange("task", this.task, this.task = task);
    }

    public boolean getDone() {
          return done;
    }

    public void setDone(boolean done) {
          firePropertyChange("done", this.done, this.done = done);
    }
}
