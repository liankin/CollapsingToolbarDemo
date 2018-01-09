package com.admin.collapsingtoolbardemo.fr;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.admin.collapsingtoolbardemo.R;

/**
 * Created by admin on 2018/1/8.
 */

public class FrFriend extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frnotice, null);
        TextView textView = (TextView) view.findViewById(R.id.tv_content);
        textView.setText("FrFriend");
        return view;
    }
}
