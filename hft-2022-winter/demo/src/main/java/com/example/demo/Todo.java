package com.example.demo;

import java.io.Serializable;

public class Todo implements Serializable{

    private String todo;
    private int priority = 2;

    public Todo(){}

    public Todo(String todo){

        this.todo = todo;

    }
    public Todo(String todo, int prio){

        this.todo = todo;
        this.priority = prio;

    }

    public String getTodo() {
        return todo;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((todo == null) ? 0 : todo.hashCode());
        result = prime * result + priority;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Todo other = (Todo) obj;
        if (todo == null) {
            if (other.todo != null)
                return false;
        } else if (!todo.equals(other.todo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Todo [todo=" + todo + ", priority=" + priority + "]";
    }
    
}
