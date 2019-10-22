package ru.namibios.bdofishbot.bot.state;


import java.util.ArrayDeque;
import java.util.Queue;

public class SideTaskContainer {

    public enum Task {
        INVENTORY, SKIP_CALENDAR, UPTIME
    }

    private static Queue<Task> tasks = new ArrayDeque<>();

    public static Queue<Task> getInstance(){

            if (tasks == null) {
                tasks = new ArrayDeque<>();
            }

            return tasks;
    }

}