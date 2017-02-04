package aesthesism.training.activity.fragment.replace;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import aesthesism.training.R;

/**
 * Created by AesthesisM on 26.01.2017.
 */

public class FragmentTwo extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.example_fragment_2,container,false);
    }
}
