package com.admin.collapsingtoolbardemo.act;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.admin.collapsingtoolbardemo.R;
import com.admin.collapsingtoolbardemo.view.PagerSlidingTabStrip;
import com.admin.collapsingtoolbardemo.view.TranslucentScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/1/5.
 */

public class ActSlidingMenu extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.img_open_sliding_menu)
    ImageView imgOpenSlidingMenu;
    @BindView(R.id.layout_header)
    LinearLayout layoutHeader;
    @BindView(R.id.translucent_scroll_view)
    TranslucentScrollView translucentScrollView;
    @BindView(R.id.layout_drawer)
    DrawerLayout layoutDrawer;
    @BindView(R.id.pager_view)
    ViewPager pagerView;
    @BindView(R.id.pager_tab)
    PagerSlidingTabStrip pagerTab;

    String[] tabs = {"坚果", "肉脯", "果冻"};
    private MyPagerAdpater adpater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_slidingmenu);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        //关联伸缩的视图
        translucentScrollView.setPullZoomView(layoutHeader);

        toolbar.setTitle("个人中心");//设置Toolbar标题
        toolbar.setTitleTextColor(this.getResources().getColor(R.color.colorWhite)); //设置标题颜色
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imgOpenSlidingMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutDrawer.isDrawerOpen(Gravity.LEFT)) {
                    layoutDrawer.closeDrawer(Gravity.LEFT);
                } else {
                    layoutDrawer.openDrawer(Gravity.LEFT);
                }
            }
        });

        adpater = new MyPagerAdpater(getSupportFragmentManager());
        pagerView.setOffscreenPageLimit(4);
        pagerView.setAdapter(adpater);
        pagerTab.setViewPager(pagerView);
        pagerTab.setIndicatorHeight(5);//选中后底部横线高度
        pagerTab.setIndicatorColor(getResources().getColor(R.color.colorPrimary));//选中后底部横线和文字颜色
        pagerTab.setDividerColor(getResources().getColor(R.color.noColor));//选项之间竖着的分割线颜色
        pagerTab.setTextColor(getResources().getColor(R.color.colorDeepGray));//未选中的文字颜色
        pagerTab.setTextSize(50);//文字字体大小
        pagerTab.setShouldExpand(true);//设置选项是否平铺占满整行
        pagerTab.notifyDataSetChanged();
    }
    class MyPagerAdpater extends FragmentPagerAdapter {

        public MyPagerAdpater(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return FrOrderList.newInstance( 0);
                case 1:
                    return FrFoodList.newInstance( 1);
                case 2:
                    return FrOrderList.newInstance( 2);
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }
        @Override
        public int getCount() {
            return tabs.length;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}