package jp.cunit.apisqltrainning.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import jp.cunit.apisqltrainning.fragment.DetailFragment;
import jp.cunit.apisqltrainning.fragment.HomeFragment;
import jp.cunit.apisqltrainning.fragment.InfoFragment;

public class ViewpageAdapter extends FragmentStateAdapter {

    public ViewpageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull

    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new InfoFragment();
            case 2:
                return new DetailFragment();
            default:
                return new HomeFragment();
        }

    }

    @Override
    public int getItemCount() {
        //we have 3 fragment
        return 3;
    }
}
