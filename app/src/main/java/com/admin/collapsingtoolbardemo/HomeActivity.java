package com.admin.collapsingtoolbardemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.admin.collapsingtoolbardemo.act.ActCoordinatorLayout;
import com.admin.collapsingtoolbardemo.act.ActTranslucentScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2018/1/3.
 */

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.btn_coordinator_layout)
    ImageView btnCoordinatorLayout;
    @BindView(R.id.btn_translucent_scroll_view)
    ImageView btnTranslucentScrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_coordinator_layout, R.id.btn_translucent_scroll_view})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_coordinator_layout:
                intent = new Intent(HomeActivity.this,ActCoordinatorLayout.class);
                startActivity(intent);
                break;
            case R.id.btn_translucent_scroll_view:
                intent = new Intent(HomeActivity.this,ActTranslucentScrollView.class);
                startActivity(intent);
                break;
        }
    }
}
