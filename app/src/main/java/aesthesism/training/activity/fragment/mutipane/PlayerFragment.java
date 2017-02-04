package aesthesism.training.activity.fragment.mutipane;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import aesthesism.training.R;

/**
 * Created by AesthesisM on 30.01.2017.
 */

public class PlayerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.multipane_fragment_player, container, false);
        return view;
    }
}
