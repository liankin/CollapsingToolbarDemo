package com.admin.collapsingtoolbardemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.admin.collapsingtoolbardemo.act.ActCoordinatorLayout;
import com.admin.collapsingtoolbardemo.act.ActSlidingImageRecyclerView;
import com.admin.collapsingtoolbardemo.act.ActSystemSlidingMenuNoTitle;
import com.admin.collapsingtoolbardemo.act.ActTranslucentScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2018/1/3.
 */

public class HomeActivity extends AppCompatActivity {


    @BindView(R.id.tv_coordinator_layout)
    TextView tvCoordinatorLayout;
    @BindView(R.id.tv_translucent_scroll_view)
    TextView tvTranslucentScrollView;
    @BindView(R.id.tv_sliding_image_recycler_view)
    TextView tvSlidingImageRecyclerView;
    @BindView(R.id.tv_system_sliding_menu_no_title)
    TextView tvSystemSlidingMenuNoTitle;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_coordinator_layout, R.id.tv_translucent_scroll_view,
            R.id.tv_sliding_image_recycler_view, R.id.tv_system_sliding_menu_no_title})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.tv_coordinator_layout:
                intent = new Intent(HomeActivity.this, ActCoordinatorLayout.class);
                startActivity(intent);
                break;
            case R.id.tv_translucent_scroll_view:
                intent = new Intent(HomeActivity.this, ActTranslucentScrollView.class);
                startActivity(intent);
                break;
            case R.id.tv_sliding_image_recycler_view:
                intent = new Intent(HomeActivity.this, ActSlidingImageRecyclerView.class);
                startActivity(intent);
                break;
            case R.id.tv_system_sliding_menu_no_title:
                intent = new Intent(HomeActivity.this, ActSystemSlidingMenuNoTitle.class);
                startActivity(intent);
                break;
        }
    }
}
