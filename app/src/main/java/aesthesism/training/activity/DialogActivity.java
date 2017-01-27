package aesthesism.training.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import aesthesism.training.R;

/**
 * Created by AesthesisM on 25.01.2017.
 */

public class DialogActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);


        Button btn1 = (Button) findViewById(R.id.activityDialog_btn_1);
        Button btn2 = (Button) findViewById(R.id.activityDialog_btn_2);
        Button btn3 = (Button) findViewById(R.id.activityDialog_btn_3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(DialogActivity.this).create();
                dialog.setTitle("Alert Dialog");
                dialog.setMessage("Welcome to my android training");
                dialog.setIcon(null);
                dialog.setButton(DialogInterface.BUTTON1, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "You clicked on Ok button", Toast.LENGTH_LONG).show();
                    }
                });
                dialog.show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(DialogActivity.this).create();
                dialog.setTitle("Confirm to send");
                dialog.setMessage("Are you sure to send all of these :p");
                dialog.setIcon(null);
                //yes button
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Haha you clicked yes :)", Toast.LENGTH_LONG).show();
                    }
                });
                //no button
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Noooo. we wont send all of this xd", Toast.LENGTH_LONG).show();
                    }
                });
                dialog.show();
            }

        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(DialogActivity.this).create();
                dialog.setTitle("How dare you...");
                dialog.setMessage("Which one of them is Cancel button .x.");
                dialog.setIcon(null);

                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Yes",Toast.LENGTH_LONG).show();
                    }
                });
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"No",Toast.LENGTH_LONG).show();
                    }
                });
                dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Cancel YEAHHH",Toast.LENGTH_LONG).show();
                    }
                });

                dialog.show();
            }
        });

    }
}
