package me.fenfei.vpdemo;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

/**
 * 典型的rv嵌套rv，滑动出现问题
 */
public class MainActivity extends AppCompatActivity {


    private ViewPager2 mViewPager2;
    private TabLayout mTabLayout;

    private List<Fragment> mFragments;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager2 = findViewById(R.id.main_vp2);
//        mViewPager2.setCurrentItem();
        mTabLayout = findViewById(R.id.main_tab);
        mFragments = new ArrayList<>();

        mFragments.add(EmptyFragment.getInstance("0"));

        if (false) {
            mFragments.add(EmptyFragment.getInstance("1"));
        } else {
            //下面的这个不管使用是vp还是rv都会存在问题
            if (true) {
                mFragments.add(VpEmptyFragment.getInstance("1"));
            } else {
                mFragments.add(RVFragment.getInstance("1"));
            }
        }

        RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(this) {
            @Override
            protected int getVerticalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }

            @Override
            protected int getHorizontalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }
        };
//        smoothScroller.setTargetPosition(position);


        mFragments.add(EmptyFragment.getInstance("2"));
        mFragments.add(EmptyFragment.getInstance("3"));
        mFragments.add(EmptyFragment.getInstance("4"));
        Vp2Adapter adapter = new Vp2Adapter(this);
        mViewPager2.setAdapter(adapter);
        adapter.setFragments(mFragments);
        mViewPager2.setUserInputEnabled(false);


        for (int i = 0; i < 5; i++) {
            TabLayout.Tab tab = mTabLayout.newTab();
            tab.setText(i + "-tab");
            mTabLayout.addTab(tab);
        }

        if (false){
            mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    int position = tab.getPosition();
//                smoothScroller.setTargetPosition(position);
//                ((RecyclerView) mViewPager2.getChildAt(0)).getLayoutManager().startSmoothScroll(smoothScroller);

//                这个是没有动画效果的
                    //https://blog.csdn.net/aiyoufang/article/details/82992115
                    RecyclerView child = (RecyclerView) mViewPager2.getChildAt(0);
                    if (false) {
                        child.scrollToPosition(position);
                    } else {
                        LinearLayoutManager mLayoutManager = (LinearLayoutManager) child.getLayoutManager();
                        mLayoutManager.scrollToPositionWithOffset(position, 0);
                    }
                    if (false) {
                        mViewPager2.setCurrentItem(position);
                    }

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }else {
        new TabLayoutMediator(mTabLayout,
                mViewPager2,
                false,
                true,
                (tab, position) -> {
                    tab.setText(position + "-tab");
                }).attach();
        }
        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        });

    }
}