package duke;

/**
 * Class handling Ui responses to user interactions.
 */
public class Ui {
    // private final static String INDENT_LINE = "____________________________________________________________";

    /**
     * Returns a given String in a format characteristic of the Ui
     * @param toDisplay the String to be formatted
     * @return the formatted String
     */
    private String getFormatted(String toDisplay) {
        return toDisplay;
        // return INDENT_LINE + "\n" + toDisplay + "\n" + INDENT_LINE;
    }

    /**
     * Returns the String representation of the list of tasks
     * @param taskList taskList the list of tasks
     * @param isMatchingList boolean representing whether the list is a keyword-matched list
     * @return the String representation of the list of tasks
     */
    private String getTaskList(TaskList taskList, boolean isMatchingList) {
        assert (!taskList.isEmpty()) : "TaskList is empty"; // ensure list is not empty
        String addString = " ";
        if (isMatchingList) {
            addString = " matching ";
        }
        StringBuilder taskListStringBuilder = new StringBuilder("Here are the"
                + addString + "tasks in your list:\n");
        for (int i = 0; i < taskList.size() - 1; i++) {
            taskListStringBuilder.append(i + 1).append(".").append(taskList.get(i)).append("\n");
        }
        String taskListString = taskListStringBuilder.toString();
        taskListString += (taskList.size() + "." + taskList.get(taskList.size() - 1));
        return taskListString;
    }

    /**
     * Returns the formatted list of tasks
     * @param taskList the list of tasks
     * @return the formatted String representation of the list of tasks
     */
    public String getFormattedTaskList(TaskList taskList) {
        if (!taskList.isEmpty()) {
            return getFormatted(getTaskList(taskList, false));
        } else {
            return "There are no tasks in your list";
        }
    }

    /**
     * Returns the formatted list of matching tasks
     * @param taskList the list of matching tasks
     * @param isMatchingList boolean representing whether the list is a keyword-matched list
     * @return the formatted list of matching tasks
     */
    public String getFormattedTaskList(TaskList taskList, boolean isMatchingList) {
        assert isMatchingList : "Should be a keyword-matched list";

        if (!taskList.isEmpty()) {
            return getFormatted(getTaskList(taskList, true));
        } else {
            return "There are no matching tasks in your list";
        }
    }

    /**
     * Returns the message to be shown at startup
     * @return the formatted String containing the message to be shown at startup
     */
    public String getInitMessage() {
        return getFormatted("Hello! I'm Vincent\n" + "What can I do for you?");
    }

    /**
     * Returns the message to be shown at exit
     * @return the formatted String containing the message to be shown at exit
     */
    public String getByeMessage() {
        return getFormatted("Bye. Hope to see you again soon!");
    }

    /**
     * Returns a message to be shown on Task deletion
     * @param deletedTask the deleted Task
     * @param taskList the list of tasks
     * @return the message to be shown on Task deletion
     */
    public String getDeleteMessage(Task deletedTask, TaskList taskList) {
        int len = taskList.size();
        return getFormatted("Noted. I've removed this task:\n" + deletedTask + "\nNow you have "
                + len + " tasks in the list.\n\n") + getFormattedTaskList(taskList);
    }

    /**
     * Returns a message to be shown ofter marking a Task as done
     * @param task the Task marked as done
     * @param index the index of the Task marked as done
     * @return the message to be shown after marking a Task as done
     */
    public String getMarkMessage(Task task, int index) {
        return getFormatted("Nice! I've marked this task as done:\n" + (index + 1)
                + "." + task);
    }

    /**
     * Returns a message to be shown ofter marking a Task as undone
     * @param task the Task marked as undone
     * @param index the index of the Task marked as undone
     * @return the message to be shown after marking a Task as undone
     */
    public String getUnmarkMessage(Task task, int index) {
        return getFormatted("OK, I've marked this task as not done yet:\n" + (index + 1)
                + "." + task);
    }

    /**
     * Returns a message to be shown after adding a Task to the list
     * @param task the Task added to the list
     * @param taskList the list of tasks
     * @return the message to be shown after adding a Task to the list
     */
    public String getAddMessage(Task task, TaskList taskList) {
        int len = taskList.size();
        return getFormatted("Got it. I've added this task:\n" + task
                + "\nNow you have " + len + " tasks in the list.\n\n") + getFormattedTaskList(taskList);
    }
}
