package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class TodoServer {
    protected int port;
    protected Todos todos;
    protected JsonObject jsonObject;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() throws IOException {
        System.out.println("Starting server at " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port);) { // стартуем сервер один(!) раз
            while (true) { // в цикле(!) принимаем подключения
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                ) {
                    changeTodos(in.readLine());
                    out.println(todos.getAllTasks());
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }

    public void changeTodos(String input) {
        jsonObject = new Gson().fromJson(input, JsonElement.class).getAsJsonObject();
        switch (jsonObject.get("type").getAsString()) {
            case "ADD":
                todos.addTask(jsonObject.get("task").getAsString());
                break;
            case "REMOVE":
                todos.removeTask(jsonObject.get("task").getAsString());
                break;
        }
    }
}
