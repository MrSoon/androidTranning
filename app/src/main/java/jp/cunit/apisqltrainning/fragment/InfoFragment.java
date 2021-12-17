package jp.cunit.apisqltrainning.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import jp.cunit.apisqltrainning.R;

public class InfoFragment extends Fragment {
    @Nullable

    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.infofragment, container, false);
    }
}
