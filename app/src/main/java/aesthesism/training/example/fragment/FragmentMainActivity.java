package aesthesism.training.example.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import aesthesism.training.R;

/**
 * Created by AesthesisM on 26.01.2017.
 */

public class FragmentMainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_fragment_activity);
    }

    public void selectFrag(View v){
        Fragment fragment;
        if(v == findViewById(R.id.example_fragment_btn1)){
            fragment = new FragmentOne();
        }else{
            fragment = new FragmentTwo();
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.example_fragment_fragment_place,fragment);
        fragmentTransaction.commit();

    }
}
