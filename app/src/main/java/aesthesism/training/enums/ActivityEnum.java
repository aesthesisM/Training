package aesthesism.training.enums;

/**
 * Created by AesthesisM on 25.01.2017.
 */

public enum ActivityEnum {
    MainActivity("Main Activitiy", "aesthesism.training.activity.MainActivity"),
    SecondActivity("Second Activity", "aesthesism.training.activity.SecondActivity"),
    ThirdActivity("Third Activity", "aesthesism.training.activity.ThirthActivity"),
    TestMediaPlayerActivity("Test Media Player Activity","aesthesism.training.activity.TestMediaPlayer"),
    DialogActivity("Dialog Activity", "aesthesism.training.activity.DialogActivity"),
    FragmentReplaceActivity("Fragment Replace Activity", "aesthesism.training.activity.fragment.replace.FragmentMainActivity"),
    FragmentListActivity("Fragment List Activity", "aesthesism.training.activity.fragment.list.ListFragmentActivity"),
    MultiPaneFragmentActivity("MultiPane Fragment Activity", "aesthesism.training.activity.fragment.mutipane.MultiPaneFragmentActivity"),
    MultiPaneFragmentActivity2("MutliPane Fragment Activity 2","aesthesism.training.activity.fragment.ItemListActivity"),
    RecyclerViewActivity("RecyclerView Activity","aesthesism.training.activity.RecycleViewActivity")

    ;
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
