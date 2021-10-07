package me.fenfei.vpdemo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

/**
 * 这个不需要纠结，就是这么用的
 * <p>
 * RecyclerView.Adapter<FragmentViewHolder>
 */
public class Vp2Adapter extends FragmentStateAdapter {


    private static final String TAG = "Vp2Adapter";
    private List<Fragment> mFragments;

    public void setFragments(List<Fragment> fragments) {
        mFragments = fragments;
        notifyDataSetChanged();
    }

    public Vp2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public Vp2Adapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public Vp2Adapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.i(TAG, "==================="+position);
        return mFragments.get(position);
    }

    @Override
    public int getItemCount() {
        return null == mFragments ? 0 : mFragments.size();
    }
}
