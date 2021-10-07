package me.fenfei.vpdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * 使用这个界面做测试，也不存在首页的卡顿了，一切都显示的很自然了
 */
public class EmptyFragment extends Fragment {


    private static final String SHOW_FLAG = "show_flag";
    private static final String TAG = "EmptyFragment";

    public static EmptyFragment getInstance(String data) {

        Bundle bundle = new Bundle();
        bundle.putString(SHOW_FLAG, data);
        EmptyFragment emptyFragment = new EmptyFragment();
        emptyFragment.setArguments(bundle);
        return emptyFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_empty, container, false);
    }


    private String mParamData;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = view.findViewById(R.id.empty_holder_tv);
        Bundle bundle = getArguments();
        if (null != bundle) {
            textView.setText(mParamData = bundle.getString(SHOW_FLAG));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "=========这个界面被唤醒了===========" + mParamData);

    }
}
