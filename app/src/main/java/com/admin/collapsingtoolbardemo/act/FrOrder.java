package com.admin.collapsingtoolbardemo.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.admin.collapsingtoolbardemo.R;
import com.admin.collapsingtoolbardemo.view.AdImageView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2017/8/29.
 */

public class FrOrder extends Fragment {

    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    Unbinder unbinder;
    private static String DATAKEY = "STATE";
    private int mState = 0;
    private LinearLayoutManager mLinearLayoutManager;

    public static FrOrder newInstance(int mState) {
        FrOrder fragment = new FrOrder();
        Bundle bundle = new Bundle();
        bundle.putInt( DATAKEY, mState);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mState = getArguments().getInt( DATAKEY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_list, null);
        unbinder = ButterKnife.bind(this, view);
        initView( );
        setList();
        return view;
    }

    public void initView( ){
        switch ( mState ){
            case 0:
                tvContent.setText( "一大波坚果即将来袭~~~" );
                break;
            case 1:
                tvContent.setText( "好吃又香，美味抵挡不住(^_^)" );
                break;
            case 2:
                tvContent.setText( "浪漫气息，粉嫩可爱(*_*)" );
                break;
        }
        tvContent.setVisibility(View.GONE);
    }

    public void setList() {
        List<String> mockDatas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mockDatas.add(i + "");
        }
        recyclerView.setLayoutManager(mLinearLayoutManager = new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(new CommonAdapter<String>(this.getContext(),
                R.layout.item_recyclerview,
                mockDatas) {
            @Override
            protected void convert(ViewHolder holder, String o, int position) {
                //显示7次文本后才显示一次图片
                if (position > 0 && position % 7 == 0) {
                    holder.setVisible(R.id.image_view, true);
                    holder.setVisible(R.id.tv_title, false);
                    holder.setVisible(R.id.tv_content, false);
                } else {
                    holder.setVisible(R.id.image_view, false);
                    holder.setVisible(R.id.tv_title, true);
                    holder.setVisible(R.id.tv_content, true);
                }
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int fPos = mLinearLayoutManager.findFirstVisibleItemPosition();
                int lPos = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                for (int i = fPos; i <= lPos; i++) {
                    View view = mLinearLayoutManager.findViewByPosition(i);
                    AdImageView adImageView = view.findViewById(R.id.image_view);
                    if (adImageView.getVisibility() == View.VISIBLE) {
                        adImageView.setDy(mLinearLayoutManager.getHeight() - view.getTop());
                    }
                }
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
