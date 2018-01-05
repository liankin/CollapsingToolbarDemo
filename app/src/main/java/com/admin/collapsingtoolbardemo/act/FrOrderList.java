package com.admin.collapsingtoolbardemo.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.admin.collapsingtoolbardemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin on 2017/8/29.
 */

public class FrOrderList extends Fragment {

    @BindView(R.id.tv_content)
    TextView tvContent;
    Unbinder unbinder;

    private static String DATAKEY = "STATE";
    private int mState = 0;

    public static FrOrderList newInstance(int mState) {
        FrOrderList fragment = new FrOrderList();
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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
