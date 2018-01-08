package com.admin.collapsingtoolbardemo.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.admin.collapsingtoolbardemo.R;
import com.admin.collapsingtoolbardemo.adapter.MyListAdapter;
import com.admin.collapsingtoolbardemo.view.AdImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2017/8/29.
 */

public class FrFoodList extends Fragment {

    @BindView(R.id.tv_content)
    TextView tvContent;

    Unbinder unbinder;
    private static String DATAKEY = "STATE";
    @BindView(R.id.list_view)
    ListView listView;
    private int mState = 0;

    private MyListAdapter myListAdapter;

    public static FrFoodList newInstance(int mState) {
        FrFoodList frFoodList = new FrFoodList();
        Bundle bundle = new Bundle();
        bundle.putInt(DATAKEY, mState);
        frFoodList.setArguments(bundle);
        return frFoodList;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mState = getArguments().getInt(DATAKEY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_list, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    public void initView() {
        switch (mState) {
            case 0:
                tvContent.setText("一大波坚果即将来袭~~~");
                break;
            case 1:
                tvContent.setText("好吃又香，美味抵挡不住(^_^)");
                break;
            case 2:
                tvContent.setText("浪漫气息，粉嫩可爱(*_*)");
                break;
        }
        tvContent.setText("ListView的item可侧滑");
        //tvContent.setVisibility(View.GONE);
        setList();
    }

    public void setList() {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            stringList.add(i + "");
        }
        myListAdapter = new MyListAdapter(this.getContext());
        myListAdapter.initData(0,stringList);
        listView.setAdapter(myListAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
