package com.example.thinkpad.t47checklisthead.fragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.thinkpad.t47checklisthead.R;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by thinkpad on 2018/2/8.
 */

public class HeadFragment extends Fragment {
    @BindViews({R.id.button1,R.id.button2,R.id.button3,R.id.button4})
    List<Button> buttonList;
    public HeadFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_head, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    //统一实现的点击事件
    public void onClick(View view) {
        switch (view.getId()) {
                case R.id.button1:
                Toast.makeText(getActivity(),"Button1",Toast.LENGTH_SHORT).show();
                break;
                case R.id.button2:
                Toast.makeText(getActivity(),"Button2",Toast.LENGTH_SHORT).show();
                break;
                case R.id.button3:
                Toast.makeText(getActivity(),"Button3",Toast.LENGTH_SHORT).show();
                break;
                case R.id.button4:
                Toast.makeText(getActivity(),"Button4",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @OnClick({R.id.button1,R.id.button2,R.id.button3,R.id.button4})
    public void thisIsClicked(Button view) {
        onClick(view);
    }
}
