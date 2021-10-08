package me.fenfei.vpdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RVFragment extends Fragment {


    private static final String TAG = "RVFragment";
    private static final String SHOW_FLAG = "show_flag";

    public static RVFragment getInstance(String data) {
        Bundle bundle = new Bundle();
        bundle.putString(SHOW_FLAG, data);
        RVFragment emptyFragment = new RVFragment();
        emptyFragment.setArguments(bundle);
        return emptyFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_r_v, container, false);
    }

    private RecyclerView mRecyclerView;
    private TextView mTextView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.rv_data);
        mTextView = view.findViewById(R.id.rv_holder_tv);
        Bundle bundle = getArguments();
        if (null != bundle) {
            mTextView.setText(bundle.getString(SHOW_FLAG));
        }


        PlayGameAdapter mGameAdapter = new PlayGameAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecyclerView.setAdapter(mGameAdapter);
        mRecyclerView.setHasFixedSize(true);
    }
}