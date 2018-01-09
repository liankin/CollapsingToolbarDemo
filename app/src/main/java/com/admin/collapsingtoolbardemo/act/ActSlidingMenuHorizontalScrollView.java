package com.admin.collapsingtoolbardemo.act;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.admin.collapsingtoolbardemo.R;
import com.admin.collapsingtoolbardemo.view.SlidingMenu;
import com.admin.collapsingtoolbardemo.view.TranslucentScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 菜单在内容布局上层，从左测滑出来、滑入，内容布局随之向右向左滑动；
 * PagerViewTab有图标和文字，PagerView不可左右滑动，只可点击tab切换；
 */

public class ActSlidingMenuHorizontalScrollView extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.layout_header)
    LinearLayout layoutHeader;
    @BindView(R.id.translucent_scroll_view)
    TranslucentScrollView translucentScrollView;
    @BindView(R.id.img_open_sliding_menu)
    ImageView imgOpenSlidingMenu;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.tab_notice)
    RadioButton tabNotice;
    @BindView(R.id.tab_friend)
    RadioButton tabFriend;
    @BindView(R.id.tab_relax)
    RadioButton tabRelax;
    @BindView(R.id.tab_bar)
    RadioGroup tabBar;
    @BindView(R.id.layout_sliding_menu)
    SlidingMenu layoutSlidingMenu;

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private FrFood frFood;
    private FrOrder frOrder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_slidingmenu_horizontalscrollview);
        ButterKnife.bind(this);

//        //使顶部系统状态栏透明
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
//        translucentScrollView.setPullZoomView(layoutHeader);//关联伸缩的视图
//        layoutSlidingMenu.closeMenu();
//        imgOpenSlidingMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                layoutSlidingMenu.toggleMenu();
//            }
//        });
        tabNotice.setChecked(true);
        tabBar.setOnCheckedChangeListener(this);
        initFragmentPagers();
    }

    public void initFragmentPagers() {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        frFood = new FrFood();
        transaction.add(R.id.frame_layout, frFood);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.tab_notice:
                FragmentTransaction ft1 = manager.beginTransaction();
                hideAll(ft1);
                if (frFood != null) {
                    ft1.show(frFood);
                } else {
                    frFood = new FrFood();
                    ft1.add(R.id.frame_layout, frFood);
                }
                ft1.commit();
                break;
            case R.id.tab_friend:
                FragmentTransaction ft2 = manager.beginTransaction();
                hideAll(ft2);
                if (frOrder != null) {
                    ft2.show(frOrder);
                } else {
                    frOrder = new FrOrder();
                    ft2.add(R.id.frame_layout, frOrder);
                }
                ft2.commit();
                break;
            case R.id.tab_relax:
                FragmentTransaction ft3 = manager.beginTransaction();
                hideAll(ft3);
                if (frFood != null) {
                    ft3.show(frFood);
                } else {
                    frFood = new FrFood();
                    ft3.add(R.id.frame_layout, frFood);
                }
                ft3.commit();
                break;
        }
    }

    private void hideAll(FragmentTransaction ft) {
        if (ft == null) {
            return;
        }
        if (frFood != null) {
            ft.hide(frFood);
        }
        if (frOrder != null) {
            ft.hide(frOrder);
        }
    }
}
