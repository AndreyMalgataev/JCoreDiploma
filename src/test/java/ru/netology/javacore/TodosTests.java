package ru.netology.javacore;

import org.junit.jupiter.api.*;

public class TodosTests {
    private static Todos todos = new Todos();

    @BeforeEach
    public void beforeEachMethod() {
        System.out.println("Start test");
        todos = new Todos();
    }

    @Test
    public void testAddTask() {
        String a = "A";
        todos.addTask(a);
        System.out.println(todos.getAllTasks());
        Assertions.assertTrue(todos.getSingleTask(a));
    }

    @Test
    public void testRemoveTask() {
        String a = "A";
        todos.addTask(a);
        Assertions.assertTrue(todos.getSingleTask(a));
        todos.removeTask(a);
        Assertions.assertFalse(todos.getSingleTask(a));
    }

    @Test
    public void testGetAllTasks() {
        String[] strings = {"A", "B", "C"};
        String expected = String.join(" ", strings);
        for(int i = strings.length - 1; i >= 0; i--) {
            todos.addTask(strings[i]);
        }
        System.out.println(todos.getAllTasks());
        Assertions.assertEquals(todos.getAllTasks(), expected);

    }
}
