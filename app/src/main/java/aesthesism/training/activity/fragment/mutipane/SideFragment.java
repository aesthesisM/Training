package aesthesism.training.activity.fragment.mutipane;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import aesthesism.training.R;

/**
 * Created by AesthesisM on 30.01.2017.
 */

public class SideFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.multipane_fragment_side,container,false);
    }
}
