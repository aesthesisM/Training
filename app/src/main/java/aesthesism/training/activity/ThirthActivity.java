package aesthesism.training.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import aesthesism.training.R;

/**
 * Created by AesthesisM on 25.01.2017.
 */

public class ThirthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirth);

        TextView t = (TextView) findViewById(R.id.act_thirth_tView);

        Intent i = getIntent();
        Bundle extras = i.getExtras();
        String name = null;
        String email = null;
        if (extras != null) {
            name = extras.getString("name");
            email = extras.getString("email");
        }
        t.setText("name : " + name + " | email : " + email);
    }
}
