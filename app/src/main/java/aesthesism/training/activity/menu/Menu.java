package aesthesism.training.activity.menu;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import aesthesism.training.R;
import aesthesism.training.enums.ActivityEnum;

/**
 * Created by AesthesisM on 25.01.2017.
 */

public class Menu extends ListActivity {


    public static ActivityEnum[] menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        menu = ActivityEnum.values();
        String[] menuItems = new String[menu.length];
        for(int i=0;i<menuItems.length;i++){
            menuItems[i] = menu[i].getListName();
        }
        setListAdapter(new ArrayAdapter<String>(Menu.this, R.layout.simple_list_item_1,menuItems ));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        try {
            Class clickedClass = Class.forName(menu[position].getClassName());
            Intent ourIntent = new Intent(getApplicationContext(), clickedClass);
            startActivity(ourIntent);
        } catch (ClassNotFoundException e) {
            //for now do nothing.
            //maybes Toast.maketext would be worth to add but rather not to use it here...
        }

    }
}
