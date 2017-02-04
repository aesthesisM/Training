package aesthesism.training.activity.fragment.mutipane;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import aesthesism.training.R;

/**
 * Created by AesthesisM on 30.01.2017.
 */

public class MultiPaneFragmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multipane_fragment_activity);

        int screenOrientation = getResources().getConfiguration().orientation;
        Toast.makeText(this, "orientation : "+screenOrientation, Toast.LENGTH_SHORT).show();
        if(screenOrientation== Configuration.ORIENTATION_PORTRAIT){
            hidePlayerFragment();
        }
    }

    private void hidePlayerFragment() {
        View player = findViewById(R.id.multipane_fragment_player);

        if(player.getVisibility()==View.VISIBLE){
            player.setVisibility(View.GONE);
        }
    }
}
