package aesthesism.training.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import aesthesism.training.R;

/**
 * Created by AesthesisM on 25.01.2017.
 */

public class SecondActivity extends AppCompatActivity {

    EditText email = null;
    EditText name = null;
    Button btnSend = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        email = (EditText) findViewById(R.id.eTxt_email);
        name = (EditText) findViewById(R.id.eTxt_name);
        btnSend = (Button) findViewById(R.id.btn_send);

        btnSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent thirthActivity = new Intent(getApplicationContext(),ThirthActivity.class);
                Bundle extra = new Bundle();
                extra.putString("name",name.getText().toString());
                extra.putString("email",email.getText().toString());
                thirthActivity.putExtras(extra);
                Log.i("n"," second Activity to 3th | params: email - "+email.getText()+" , name - "+name.getText());
                startActivity(thirthActivity);
            }
        });
    }


}
