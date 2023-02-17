package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        System.out.println("Welcome to Task (stream) manager\n");
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        System.out.println();
        System.out.println("Printing deadlines");
        //printDeadlines(tasksData);
        printDeadlines(tasksData);

        //System.out.println("Total number of deadlines: " + countDeadlinesUsingStreams(tasksData));
        //System.out.println("Before Sorting");
        //printDeadlinesUsingStream(tasksData);
        //System.out.println("After Sorting");
        //sortDeadlinesUsingStream(tasksData);
        //printData(tasksData);
        //printDataUsingStream(tasksData);
        ArrayList<Task> filteredList = filterTaskListUsingStreams(tasksData, "11");
        printDataUsingStream(filteredList);

    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    public static void printData(ArrayList<Task> tasksData) {
        System.out.println("Print data using iteration:");
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }
    public static void printDataUsingStream(ArrayList<Task> tasks) {

        tasks.parallelStream()      //convert to stream
                .forEach(System.out::println);

    }
    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }
    public static void printDeadlinesUsingStream(ArrayList<Task> tasks) {
        System.out.println("Print deadlines using streams");
        tasks.stream()
                .filter(t -> t instanceof Deadline) //filter takes a predicate/condition
                .forEach(System.out::println);
    }

    public static int countDeadlinesUsingStreams(ArrayList<Task> tasks) {
        int count = (int) tasks.stream()
                .filter(t -> t instanceof Deadline)
                .count();
        return count;
    }

    public static void sortDeadlinesUsingStream(ArrayList<Task> tasks) {
        tasks.stream()
                .filter(t -> t instanceof Deadline)
                .sorted((a, b) -> a.getDescription().compareToIgnoreCase(b.getDescription()))
                .forEach(System.out::println);
    }

    public static ArrayList<Task> filterTaskListUsingStreams(ArrayList<Task> tasks, String filterString) {
        ArrayList<Task> filteredList= (ArrayList<Task>) tasks.stream()
                .filter(t -> t.getDescription().contains(filterString))
                .collect(Collectors.toList());
        return filteredList;
    }
}
