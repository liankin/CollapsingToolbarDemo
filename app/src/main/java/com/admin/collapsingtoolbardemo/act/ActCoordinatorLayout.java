package com.admin.collapsingtoolbardemo.act;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.admin.collapsingtoolbardemo.R;

public class ActCoordinatorLayout extends AppCompatActivity {

    private ImageView imageView;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_coordinatorlayout);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        imageView = (ImageView) findViewById(R.id.iv);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher_round);
        collapsingToolbarLayout.setTitle("风景如画");
        collapsingToolbarLayout.setCollapsedTitleTextColor(this.getResources().getColor(R.color.colorOrange));
        collapsingToolbarLayout.setExpandedTitleColor(this.getResources().getColor(R.color.colorOrange));
//        imageView.setImageResource(R.drawable.bg_banner);
    }
}
