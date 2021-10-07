package me.fenfei.vpdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;


public class VpFragment2 extends Fragment {


    private static final String TAG = "VpFragment";
    private static final String SHOW_FLAG = "show_flag";

    public static VpFragment2 getInstance(String data) {
        Bundle bundle = new Bundle();
        bundle.putString(SHOW_FLAG, data);
        VpFragment2 emptyFragment = new VpFragment2();
        emptyFragment.setArguments(bundle);
        return emptyFragment;
    }

    private ViewPager2 mViewPager2;
    private TabLayout mTabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vp, container, false);
    }

    private List<Fragment> mFragments;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager2 = view.findViewById(R.id.vp_vp2);
        mTabLayout = view.findViewById(R.id.vp_tab);
        TextView textView = view.findViewById(R.id.vp_holder_tv);
        Bundle bundle = getArguments();
        if (null != bundle) {
            textView.setText(bundle.getString(SHOW_FLAG));
        }
        mFragments = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            mFragments.add(VpFragment.getInstance(String.valueOf(i)));
        }
        mViewPager2.setUserInputEnabled(false);
        Vp2Adapter adapter = new Vp2Adapter(this);
        mViewPager2.setAdapter(adapter);
        adapter.setFragments(mFragments);
        new TabLayoutMediator(mTabLayout,
                mViewPager2,
                false,
                true,
                (tab, position) -> {
                    tab.setText(position + "-tab");
                }).attach();
    }
}