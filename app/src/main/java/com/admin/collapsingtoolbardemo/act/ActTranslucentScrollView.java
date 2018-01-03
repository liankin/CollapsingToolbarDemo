package com.admin.collapsingtoolbardemo.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.admin.collapsingtoolbardemo.R;
import com.admin.collapsingtoolbardemo.view.ActionBarClickListener;
import com.admin.collapsingtoolbardemo.view.TranslucentActionBar;
import com.admin.collapsingtoolbardemo.view.TranslucentScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/1/3.
 */

public class ActTranslucentScrollView extends AppCompatActivity implements ActionBarClickListener,TranslucentScrollView.TranslucentChangedListener{

    @BindView(R.id.lay_header)
    LinearLayout layHeader;

    @BindView(R.id.actionbar)
    TranslucentActionBar actionBar;
    @BindView(R.id.pullzoom_scrollview)
    TranslucentScrollView translucentScrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_translucentscrollview);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        //初始actionBar
        actionBar.setData("测试", 0, null, 0, null, null);
        //开启渐变
        actionBar.setNeedTranslucent();
        //设置状态栏高度
        actionBar.setStatusBarHeight(getStatusBarHeight());

        //设置透明度变化监听
        translucentScrollView.setTranslucentChangedListener(this);
        //关联需要渐变的视图
        translucentScrollView.setTransView(actionBar);
        //设置ActionBar键渐变颜色
        translucentScrollView.setTransColor(getResources().getColor(R.color.colorOrange));
        //关联伸缩的视图
        translucentScrollView.setPullZoomView(layHeader);
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    public int getStatusBarHeight() {
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            return getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    @Override
    public void onTranslucentChanged(int transAlpha) {
        actionBar.tvTitle.setVisibility(transAlpha > 48 ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onLeftClick() {
        Toast.makeText(ActTranslucentScrollView.this,"点击了左侧按钮",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRightClick() {
        Toast.makeText(ActTranslucentScrollView.this,"点击了右侧按钮",Toast.LENGTH_SHORT).show();
    }
}
