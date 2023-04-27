import java.util.HashSet;
import java.util.GregorianCalendar;
import java.util.Set;

public class ToDoItem {

    private String task;
    private GregorianCalendar dueDate;
    private String assignee;
    private boolean isPriority;

    private boolean isFinished;
    private HashSet<String> tags;

    public ToDoItem(String task) {
        this.task = task;
        isFinished = false;
        tags = new HashSet<>();
    }

    public void setDueDate(GregorianCalendar dueDate){
        this.dueDate = dueDate;
    }

    public void setAssignee(String assignee){
        this.assignee = assignee;
    }

    public void setPriority (boolean priority){
        this.isPriority = priority;
    }

    public void setTask (String task){
        this.task = task;
    }

    public void addTag (String tag){
        this.tags.add(tag);
    }

    public GregorianCalendar getDueDate(){
        return dueDate;
    }

    public String getAssignee(){
        return assignee;
    }

    public boolean getIsPriority(){
        return isPriority;
    }

    public boolean containsTag(String tag){
        return tags.contains(tag);
    }

    public Set<String> getTags(){
        return tags;
    }

    public void setFinishTask(){
        isFinished = true;
    }

    public boolean getIsFinished(){
        return isFinished;
    }

}
