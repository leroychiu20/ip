package alex.tasklist;

import alex.task.Deadline;
import alex.task.Event;
import alex.task.Task;
import alex.task.Todo;
import alex.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents the list of tasks to be done. A TaskList object corresponds to
 * a list of actions represented by an arraylist.
 */
public class TaskList {
    public static final String LINE = Ui.LINE;
    ArrayList<Task> list;

    public TaskList(ArrayList<Task> tasks) {
        this.list = tasks;
    }

    /**
    * Displays the list of tasks when commanded by the user.
    */
    public void handleList() {
        System.out.println(LINE + "\n" +
                "Here are the tasks in your list: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
        System.out.println(LINE);
    }
    /**
     * Displays the desired task as marked when commanded by the user.
     *
     * @param input input entered by the user
     */
    public void handleMark(String input) {
        int index = Integer.parseInt(input.substring(5)) - 1;
        if (index >= 0 && index < list.size()) {
            Task task = list.get(index);
            System.out.println(LINE);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done: \n" +
                    task.toString());
            System.out.println(LINE);
        } else {
            System.out.println(LINE);
            System.out.println("It seems that task does not exist. Please try again.");
            System.out.println(LINE);
        }
    }
    /**
     * Displays the desired task as unmarked when commanded by the user.
     *
     * @param input input entered by the user
     */
    public void handleUnmark(String input) {
        int index = Integer.parseInt(input.substring(7)) - 1;
        if (index >= 0 && index < list.size()) {
            Task task = list.get(index);
            System.out.println(LINE);
            task.unmark();
            System.out.println("OK, I've marked this task as not done yet: \n" +
                    task.toString());
            System.out.println(LINE);
        } else {
            System.out.println(LINE);
            System.out.println("It seems that task does not exist. Please try again.");
            System.out.println(LINE);
        }
    }
    /**
     * Adds the to-do to the list when commanded by the user.
     * Displays the task as added into the list.
     * If there is no description of the task, it will ask user to try again.
     *
     * @param input input entered by the user
     */
    public void handleTodo(String input) {
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            System.out.println("You missed out some details. Try again");
        } else {
            Todo todo = new Todo(description);
            list.add(todo);
            System.out.println(LINE);
            System.out.println("Got it. I've added this task: \n" + todo.toString()
                    + "\n Now you have " + list.size() + " tasks in the list.");
            System.out.println(LINE);
        }
    }
    /**
     * Adds the deadline to the list when commanded by the user.
     * Displays the task as added into the list.
     * If there is no description of the task, it will ask user to try again.
     * If the date entered by the user is invalid, it will ask user to try again.
     *
     * @param input input entered by the user
     */
    public void handleDeadline(String input) {
        String description = input.substring(8).trim();
        if (description.isEmpty()) {
            System.out.println("You missed out the details of the deadline task. Try again");
        } else {
            try {
                String[] details = description.split("/", 2);
                String task = details[0].trim();
                String dueInter = details[1].substring(2).trim();
                LocalDate dueDate = LocalDate.parse(dueInter);
                if (task.isEmpty() || dueInter.isEmpty()) {
                    System.out.println("You missed out some details. Try again");
                } else {
                    Deadline deadline = new Deadline(task, dueDate);
                    list.add(deadline);
                    System.out.println(LINE);
                    System.out.println("Got it. I've added this task: \n" + deadline.toString()
                            + "\n Now you have " + list.size() + " tasks in the list.");
                    System.out.println(LINE);
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date entered. Use this format: YYYY-MM-DD");
                System.out.println(LINE);
            }
        }
    }
    /**
     * Adds the event to the list when commanded by the user.
     * Displays the task as added into the list.
     * If there is no description of the task, it will ask user to try again.
     * If the date(s) entered by the user is invalid, it will ask user to try again.
     *
     * @param input input entered by the user
     */
    public void handleEvent(String input) {
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            System.out.println("You missed out the details of the event task. Try again");
        } else {
            try {
                String[] details = description.split("/", 3);
                String task = details[0].trim();
                String startInter = details[1].substring(4).trim();
                LocalDate startDate = LocalDate.parse(startInter);
                String endInter = details[2].substring(2).trim();
                LocalDate endDate = LocalDate.parse(endInter);
                if (task.isEmpty() || startInter.isEmpty() || endInter.isEmpty()) {
                    System.out.println("You missed out some details. Try again");
                } else {
                    Event event = new Event(task, startDate, endDate);
                    list.add(event);
                    System.out.println(LINE);
                    System.out.println("Got it. I've added this task: \n" + event.toString()
                            + "\n Now you have " + list.size() + " tasks in the list.");
                    System.out.println(LINE);
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date(s) entered. Use this format: YYYY-MM-DD");
                System.out.println(LINE);
            }
        }
    }
    /**
     * Deletes the desired task from the list when commanded by the user.
     * Displays the task as removed into the list.
     * If the index entered by the user is < -1 or > size of list, it will ask user to try again.
     *
     * @param input input entered by the user
     */
    public void handleDelete(String input) {
        int index = Integer.parseInt(input.substring(7)) - 1;
        if (index >= 0 && index < list.size()) {
            Task task = list.get(index);
            System.out.println(LINE);
            list.remove(index);
            System.out.println("OK, I've deleted this task: \n" + task.toString() +
                    "\n Now you have " + list.size() + " tasks left in the list.");
            System.out.println(LINE);
        } else {
            System.out.println(LINE);
            System.out.println("It seems that task does not exist. Please try again.");
            System.out.println(LINE);
        }
    }
    /**
     * Displays the deadlines that are due on or events that end on the specified date.
     * If the date entered by the user is invalid, it will ask user to try again.
     *
     * @param input input entered by the user
     */
    public void handleDate(String input) {
        try {
            LocalDate dateToFind = LocalDate.parse(input.substring(9).trim());
            @SuppressWarnings("unchecked")
            ArrayList<Task> newList = (ArrayList<Task>) list.clone();
            newList.removeIf(task -> task.dueDate == null);
            System.out.println(LINE);
            for (Task task : newList) {
                if (task.dueDate.isEqual(dateToFind)) {
                    System.out.println(task);
                }
            }
            System.out.println(LINE);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date(s) entered. Use this format: YYYY-MM-DD");
        }
    }
}
