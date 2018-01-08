package com.admin.collapsingtoolbardemo.act;

import android.content.Context;
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
import android.view.Display;
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
 * 菜单在内容布局上层，从左测滑出来、滑入，内容布局不动；
 * PagerViewTab只有文字，PagerView可左右滑动；
 */

public class ActSlidingMenuDrawerLayoutNoTitle extends AppCompatActivity {

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
    @BindView(R.id.layout_home_content)
    LinearLayout layoutHomeContent;
    @BindView(R.id.layout_sliding_menu)
    LinearLayout layoutSlidingMenu;
    private MyPagerAdpater adpater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_slidingmenu_drawerlayout_no_title);
        ButterKnife.bind(this);

        //使顶部系统状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        initSlidingMenu();
        initPagerView();
    }

    /**
     * 滑动菜单
     */
    public void initSlidingMenu(){
        translucentScrollView.setPullZoomView(layoutHeader);//关联伸缩的视图
        //设置Toolbar标题、标题颜色，必须在setSupportActionBar之前设置
        //toolbar.setTitle("个人中心");
        //toolbar.setTitleTextColor(this.getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbar);
        //getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        //当左侧菜单向右滑动时，右侧主布局随之向右滑动
//        layoutDrawer.addDrawerListener(new DrawerLayout.DrawerListener() {
//            @Override
//            public void onDrawerSlide(View drawerView, float slideOffset) {
//                // 获取屏幕的宽高
//                WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//                Display display = manager.getDefaultDisplay();
//                // 设置右面的布局位置
//                // 根据左面菜单的right作为右面布局的left
//                // 左面的right+屏幕的宽度（或者right的宽度这里是相等的）为右面布局的right
//                layoutHomeContent.layout(layoutSlidingMenu.getRight(), 0,
//                        layoutSlidingMenu.getRight() + display.getWidth(), display.getHeight());
//            }
//
//            @Override
//            public void onDrawerOpened(View drawerView) {
//
//            }
//
//            @Override
//            public void onDrawerClosed(View drawerView) {
//
//            }
//
//            @Override
//            public void onDrawerStateChanged(int newState) {
//
//            }
//        });
    }

    /**
     * PagerView相关
     */
    public void initPagerView(){
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
            switch (position) {
                case 0:
                    return FrOrderList.newInstance(0);
                case 1:
                    return FrOrderList.newInstance(1);
                case 2:
                    return FrOrderList.newInstance(2);
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