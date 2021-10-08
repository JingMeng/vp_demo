package me.fenfei.vpdemo;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {


    private ViewPager2 mViewPager2;
    private TabLayout mTabLayout;

    private List<Fragment> mFragments;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager2 = findViewById(R.id.main_vp2);
        mTabLayout = findViewById(R.id.main_tab);
        mFragments = new ArrayList<>();

        mFragments.add(EmptyFragment.getInstance("0"));

        if (false) {
            mFragments.add(EmptyFragment.getInstance("1"));
        } else {
            //下面的这个不管使用是vp还是rv都会存在问题
            if (false) {
                mFragments.add(VpFragment.getInstance("1"));
            } else {
                mFragments.add(RVFragment.getInstance("1"));
            }
        }
        mFragments.add(EmptyFragment.getInstance("2"));
        mFragments.add(EmptyFragment.getInstance("3"));
        mFragments.add(EmptyFragment.getInstance("4"));
        Vp2Adapter adapter = new Vp2Adapter(this);
        mViewPager2.setAdapter(adapter);
        adapter.setFragments(mFragments);
        mViewPager2.setUserInputEnabled(false);
        new TabLayoutMediator(mTabLayout,
                mViewPager2,
                false,
                true,
                (tab, position) -> {
                    tab.setText(position + "-tab");
                }).attach();
    }
}