package duke;

import java.util.Scanner;

public class Parser {
    private TaskList taskList;
    private Ui ui;

    /**
     * Parameterized constructor to create a Parser
     * @param taskList the TaskList which has to be parsed
     * @param ui the Ui of the agent on which messages have to be displayed
     */
    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Displays the current list of tasks
     */
    private void showList() {
        ui.showTaskList();
    }

    /**
     * Marks the Task at a specified index as done
     * @param index the index of the Task to be marked as done
     */
    private void mark(int index) {
        taskList.mark(index);
        ui.showMarkMessage(index);
    }

    /**
     * Marks the Task at a specified index as undone
     * @param index the index of the Task to be marked as undone
     */
    private void unmark(int index) {
        taskList.unmark(index);
        ui.showUnmarkMessage(index);
    }

    /**
     * Deletes the Task at a specified index
     * @param index the index of the Task to be deleted
     */
    private void delete(int index) {
        Task deletedTask = taskList.delete(index);
        ui.showDeleteMessage(deletedTask);
    }

    /**
     * Adds a Todo to the TaskList
     * @param task the String representing the Todo to be added
     * @throws DukeException if the description of the Todo is empty
     */
    private void addTodo(String task) throws DukeException {
        String description = task.substring(5);
        if (description.trim().equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        taskList.add(new Todo(description));
    }

    /**
     * Adds a Deadline to the TaskList
     * @param task the String representing the Deadline to be added
     */
    private void addDeadline(String task) {
        String[] arr = task.split("/");
        String description = arr[0].substring(9);
        String by = arr[1].substring(3);
        taskList.add(new Deadline(description, by));
    }

    /**
     * Adds an Event to the TaskList
     * @param task the String representing the Event to be added
     */
    private void addEvent(String task) {
        String[] arr = task.split("/");
        String description = arr[0].substring(6);
        String from = arr[1].substring(5);
        String to = arr[2].substring(3);
        taskList.add(new Event(description, from, to));
    }

    /**
     * Parses user input until the termination command "bye" is entered
     */
    public void parse() {
        Scanner sc = new Scanner(System.in);

        try {
            while (true) {
                String task = sc.nextLine();
                if (task.equals("bye")) {
                    ui.showByeMessage();
                    break;
                } else if (task.equals("list")) {
                    showList();
                } else if (task.startsWith("mark")) {
                    int index = Integer.parseInt(task.substring(5)) - 1;
                    mark(index);
                } else if (task.startsWith("unmark")) {
                    int index = Integer.parseInt(task.substring(7)) - 1;
                    unmark(index);
                } else if (task.startsWith("delete")) {
                    int index = Integer.parseInt(task.substring(7)) - 1;
                    delete(index);
                }
                else {
                    if (task.startsWith("todo")) {
                        addTodo(task);
                    } else if (task.startsWith("deadline")) {
                        addDeadline(task);
                    } else if (task.startsWith("event")) {
                        addEvent(task);
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    ui.showAddMessage();
                }

            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
