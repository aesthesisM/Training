package aesthesism.training.activity.fragment.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import aesthesism.training.R;

/**
 * Created by AesthesisM on 27.01.2017.
 */

public class ListFragmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_fragment_main);
    }
}
