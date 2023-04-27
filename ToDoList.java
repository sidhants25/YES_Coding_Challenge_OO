import java.util.*;

public class ToDoList {

    private String title;
    private LinkedList<ToDoItem> items;

    //HashMap was used to try to solve the hard question. Methods below reflect that
    private HashMap<String, LinkedList<ToDoItem>> groupTags;

    public ToDoList(String title){
        this.title = title;
        items = new LinkedList<>();
        groupTags = new HashMap<>();
    }


    //adds the item to the list. for each tag the item has, the item is added to the corresponding tags value
    //in the hashmap
    public void addItem (ToDoItem item){
        items.add(item);
        for(String tag : item.getTags()){
            if(groupTags.containsKey(tag)){
                groupTags.get(tag).add(item);
            }
            else{
                LinkedList<ToDoItem> list = new LinkedList<>();
                list.add(item);
                groupTags.put(tag,list);
            }
        }
        Collections.sort(items, Comparator.comparing(ToDoItem::getDueDate));
    }

    //adds a specified tag to the hashamp
    public void addTag(String tag){
        if(groupTags.containsKey(tag)){
            return;
        }
        else{
            groupTags.put(tag, new LinkedList<>());
        }
    }

    //returns all items that share the tag in a list or null if the tag doesn't exist
    public List<ToDoItem> findItemsWithTag(String tag){
        if(!groupTags.containsKey(tag)){
            return null;
        }
        else{
            return groupTags.get(tag);
        }
    }

    public void editTask(ToDoItem item, String task){
        if (!items.contains(item)){
            return;
        }
        ToDoItem curr = items.get(items.indexOf(item));
        curr.setTask(task);
    }

    public void editAssignee(ToDoItem item, String assignee){
        if (!items.contains(item)){
            return;
        }
        ToDoItem curr = items.get(items.indexOf(item));
        curr.setAssignee(assignee);
    }

    public void editDueDate(ToDoItem item, GregorianCalendar dueDate){
        if (!items.contains(item)){
            return;
        }
        ToDoItem curr = items.get(items.indexOf(item));
        curr.setDueDate(dueDate);
        Collections.sort(items, Comparator.comparing(ToDoItem::getDueDate));
    }

    //Task 1 Easy - takes two lists and returns a sorted list with all elements by their due date. Calander
    //class was used to determine relative positioning
    public static List<ToDoItem> mergeList(List<ToDoItem> list1,List<ToDoItem> list2){
        LinkedList<ToDoItem> merged = new LinkedList<>(list1);
        merged.addAll(list2);
        Collections.sort(merged, new Comparator<ToDoItem>() {
            @Override
            public int compare(ToDoItem o1, ToDoItem o2) {
                if(o1.getDueDate() == null && o2.getDueDate() == null){
                    return 0;
                }
                else if(o1.getDueDate()==null){
                    return 1;
                }
                else if(o2.getDueDate()==null){
                    return -1;
                }
                else{
                    return o1.getDueDate().compareTo(o2.getDueDate());
                }
            }
        });

        return merged;
    }

    //Task 2: Easy - returns a new list bounded by dueDates using the Calandar class sorting
    public List<ToDoItem> filterByRangeList(GregorianCalendar endDate,
                                                   GregorianCalendar startDate ){
        LinkedList<ToDoItem> filter = new LinkedList<>();
        for(ToDoItem item : items){
            if(item.getDueDate()!=null && (item.getDueDate().compareTo(endDate)) <= 0
                && (item.getDueDate().compareTo(startDate)) >= 0){
                filter.add(item);
            }
        }
        Collections.sort(filter, Comparator.comparing(ToDoItem::getDueDate));
        return filter;
    }

    //Task 3: Easy - returns a list of items that have the same specified date, priority and assignee
    public List<ToDoItem> filterByAssignments(GregorianCalendar date, boolean priority, String assignee){
        LinkedList<ToDoItem> filter = new LinkedList<>();
        for(ToDoItem item : items){
            if(item.getDueDate().equals(date) && item.getIsPriority()==priority && item.getAssignee().equals(assignee)) {
                filter.add(item);
            }
        }
        Collections.sort(filter, Comparator.comparing(ToDoItem::getDueDate));
        return filter;
    }

    //given a ToDo list and a set of tags, output the items in the list that have exactly those tags
    //may not be relevant to problem --> was a first attempt at the hard question
    public List<ToDoItem> filterByTags(Set<String> tags){
        LinkedList<ToDoItem> filter = new LinkedList<>();
        for(ToDoItem item : items){
            if (item.getTags().equals(tags)){
                filter.add(item);
            }
        }
        Collections.sort(filter, Comparator.comparing(ToDoItem::getDueDate));
        return filter;
    }

}
