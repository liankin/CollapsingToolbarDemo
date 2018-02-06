package com.admin.collapsingtoolbardemo.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.admin.collapsingtoolbardemo.R;
import com.admin.collapsingtoolbardemo.utils.ToastUtil;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 使用第三方侧滑菜单
 * Created by admin on 2018/1/18.
 */

public class ActSlidingMenuThread extends AppCompatActivity {

    @BindView(R.id.btn_menu)
    TextView btnMenu;
    @BindView(R.id.title)
    TextView title;

    private SlidingMenu menu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_slidingmenu_thread);
        ButterKnife.bind(this);
        title.setText("首页");
        initMenu();
    }

    /**
     * 初始化菜单栏
     */
    private void initMenu() {
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.menu_shadow);
        // 设置滑动菜单视图的宽度
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //为侧滑菜单设置布局
        menu.setMenu(R.layout.layout_slidingmenu_menu);
        View lLayoutHeader = findViewById(R.id.layout_header);
        lLayoutHeader.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.layout_header:
                    ToastUtil.showMessage(ActSlidingMenuThread.this, "这是我的头像。。。");
                    break;
            }
        }
    };

    @OnClick({R.id.btn_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_menu:
                menu.toggle();
                break;
        }
    }
}
