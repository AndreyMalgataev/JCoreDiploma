package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    public Set<String> todos;

    public Todos() {
        this.todos = new HashSet<>();
    }

    public boolean getSingleTask(String task) {
        return this.todos.contains(task);
    }

    public void addTask(String task) {
        this.todos.add(task);
    }

    public void removeTask(String task) {
        this.todos.remove(task);
    }

    public String getAllTasks() {
        return this.todos.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.joining(" "));
    }

}
