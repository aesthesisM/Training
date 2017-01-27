package aesthesism.training.enums;

/**
 * Created by AesthesisM on 25.01.2017.
 */

public enum ActivityEnum {
    MainActivity("Main Activitiy", "aesthesism.training.activity.MainActivity"), SecondActivity("Second Activity", "aesthesism.training.activity.SecondActivity"), ThirdActivity("Third Activity", "aesthesism.training.activity.ThirthActivity"), DialogActivity("Dialog Activity", "aesthesism.training.activity.DialogActivity"),FragmentActivity("Fragment Activity","aesthesism.training.example.fragment.FragmentMainActivity");
    private String listName;
    private String className;

    ActivityEnum(String listName, String className) {
        this.listName = listName;
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public String getListName() {
        return listName;
    }

}
