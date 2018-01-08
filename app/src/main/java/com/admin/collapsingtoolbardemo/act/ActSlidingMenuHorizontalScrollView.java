package com.admin.collapsingtoolbardemo.act;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.admin.collapsingtoolbardemo.R;
import com.admin.collapsingtoolbardemo.view.PagerSlidingTabStrip;
import com.admin.collapsingtoolbardemo.view.SlidingMenu;
import com.admin.collapsingtoolbardemo.view.TranslucentScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 菜单在内容布局上层，从左测滑出来、滑入，内容布局随之向右向左滑动；
 * PagerViewTab有图标和文字，PagerView不可左右滑动，只可点击tab切换；
 */

public class ActSlidingMenuHorizontalScrollView extends AppCompatActivity {

    @BindView(R.id.layout_sliding_menu)
    SlidingMenu layoutSlidingMenu;
    @BindView(R.id.layout_header)
    LinearLayout layoutHeader;
    @BindView(R.id.translucent_scroll_view)
    TranslucentScrollView translucentScrollView;
    @BindView(R.id.img_open_sliding_menu)
    ImageView imgOpenSlidingMenu;
    @BindView(R.id.pager_view)
    ViewPager pagerView;
    @BindView(R.id.pager_tab)
    PagerSlidingTabStrip pagerTab;

    private final String[] TABS = {"消息", "好友", "空间"};
    private final int[] ICONS = {R.mipmap.ic_launcher_round, R.mipmap.ic_launcher_round,
            R.mipmap.ic_launcher_round};
    private final int[] SELECTED_ISONS = {R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher};
    private MyPagerAdpater adpater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_slidingmenu_horizontalscrollview);
        ButterKnife.bind(this);

        //使顶部系统状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        initPagerView();
        translucentScrollView.setPullZoomView(layoutHeader);//关联伸缩的视图
        layoutSlidingMenu.closeMenu();
        imgOpenSlidingMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutSlidingMenu.toggleMenu();
            }
        });
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

    class MyPagerAdpater extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTextTabProvider{

        public MyPagerAdpater(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return FrFoodList.newInstance(0);
                case 1:
                    return FrOrderList.newInstance(1);
                case 2:
                    return FrOrderList.newInstance(2);
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TABS[position];
        }

        @Override
        public int getCount() {
            return TABS.length;
        }

        @Override
        public int getPageIconResId(int position) {
            return ICONS[position];
        }

        @Override
        public int getPageSelectedIconResId(int position) {
            return SELECTED_ISONS[position];
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
