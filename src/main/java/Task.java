import java.time.LocalDate;

public class Task {
    public String description;
    public boolean isDone;
    public LocalDate dueDate;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void markAsDone() {
        this.isDone = true;
    }
    public void unmark() {
        this.isDone = false;
    }

    public String toBeSavedAsData() {
        return toString();
    }
    public String toString() {
        return description;
    }

}
