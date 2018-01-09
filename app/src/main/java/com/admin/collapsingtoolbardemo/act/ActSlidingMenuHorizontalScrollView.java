package com.admin.collapsingtoolbardemo.act;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
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
import com.admin.collapsingtoolbardemo.fr.FrFriend;
import com.admin.collapsingtoolbardemo.fr.FrNotice;
import com.admin.collapsingtoolbardemo.fr.FrShare;
import com.admin.collapsingtoolbardemo.view.SlidingMenu;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 侧滑菜单（仿QQ）：
 * 1.菜单在内容布局上层，从左测滑出来、滑入，内容布局随之向右向左滑动；
 * 2.使用RadioGroup实现，FragmentTab有图标和文字，Fragment之间不可左右滑动，只可点击tab切换；
 */
public class ActSlidingMenuHorizontalScrollView extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.tab_bar)
    RadioGroup tabBar;
    @BindView(R.id.img_open_sliding_menu)
    ImageView imgOpenSlidingMenu;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.layout_home_content)
    LinearLayout layoutHomeContent;
    @BindView(R.id.layout_sliding_menu)
    SlidingMenu layoutSlidingMenu;

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private FrNotice frNotice;
    private FrFriend frFriend;
    private FrShare frShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_slidingmenu_horizontalscrollview);
        ButterKnife.bind(this);

        //使顶部系统状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        layoutSlidingMenu.closeMenu();
        imgOpenSlidingMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutSlidingMenu.toggleMenu();
            }
        });
        layoutHomeContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutSlidingMenu.closeMenu();
            }
        });

        RadioButton tabHome = (RadioButton) tabBar.getChildAt(0);
        tabHome.setChecked(true);
        tabBar.setOnCheckedChangeListener(this);
        initFragment();
    }

    private void initFragment() {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        frNotice = new FrNotice();
        transaction.add(R.id.frame_layout, frNotice);
        transaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.tab_notice:
                FragmentTransaction ft1 = manager.beginTransaction();
                hideAll(ft1);
                if (frNotice != null) {
                    ft1.show(frNotice);
                } else {
                    frNotice = new FrNotice();
                    ft1.add(R.id.frame_layout, frNotice);
                }
                ft1.commit();
                break;
            case R.id.tab_friend:
                FragmentTransaction ft2 = manager.beginTransaction();
                hideAll(ft2);
                if (frFriend != null) {
                    ft2.show(frFriend);
                } else {
                    frFriend = new FrFriend();
                    ft2.add(R.id.frame_layout, frFriend);
                }
                ft2.commit();
                break;
            case R.id.tab_relax:
                FragmentTransaction ft3 = manager.beginTransaction();
                hideAll(ft3);
                if (frShare != null) {
                    ft3.show(frShare);
                } else {
                    frShare = new FrShare();
                    ft3.add(R.id.frame_layout, frShare);
                }
                ft3.commit();
                break;
        }
    }

    private void hideAll(FragmentTransaction ft) {
        if (ft == null) {
            return;
        }
        if (frNotice != null) {
            ft.hide(frNotice);
        }
        if (frFriend != null) {
            ft.hide(frFriend);
        }
        if (frShare != null) {
            ft.hide(frShare);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
