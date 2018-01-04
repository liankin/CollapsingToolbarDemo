package com.admin.collapsingtoolbardemo.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.admin.collapsingtoolbardemo.R;
import com.admin.collapsingtoolbardemo.view.ActionBarClickListener;
import com.admin.collapsingtoolbardemo.view.TranslucentActionBar;
import com.admin.collapsingtoolbardemo.view.TranslucentScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 沉浸式状态栏、actionBar渐变、scrollView顶部伸缩
 */

public class ActTranslucentScrollView extends AppCompatActivity implements TranslucentScrollView.TranslucentChangedListener {

    @BindView(R.id.layout_header)
    RelativeLayout layoutHeader;
    @BindView(R.id.translucent_scroll_view)
    TranslucentScrollView translucentScrollView;
    @BindView(R.id.translucent_action_bar)
    TranslucentActionBar translucentActionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_translucentscrollview);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        // 初始actionBar：
        // public void setData(String strTitle, int resIdLeft, String strLeft,
        // int resIdRight, String strRight, final ActionBarClickListener listener)
//        actionBar.setData("测试", 0, null, 0, null, null);
        translucentActionBar.setData("个人中心", R.drawable.ic_left_light, "返回", R.drawable.ic_sign, "消息", new ActionBarClickListener() {
            @Override
            public void onLeftClick() {
                Toast.makeText(ActTranslucentScrollView.this, "点击了左侧按钮", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightClick() {
                Toast.makeText(ActTranslucentScrollView.this, "点击了右侧按钮", Toast.LENGTH_SHORT).show();
            }
        });
        //开启渐变
        translucentActionBar.setNeedTranslucent();
        //设置状态栏(标题栏)高度
//        actionBar.setStatusBarHeight(30);
        translucentActionBar.setStatusBarHeight(getStatusBarHeight());

        //设置透明度变化监听
        translucentScrollView.setTranslucentChangedListener(this);
        //关联需要渐变的视图
        translucentScrollView.setTransView(translucentActionBar);
        //设置ActionBar键渐变颜色
        translucentScrollView.setTransColor(getResources().getColor(R.color.colorPrimary));
        //关联伸缩的视图
        translucentScrollView.setPullZoomView(layoutHeader);
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    public int getStatusBarHeight() {
        //获取dimen文件中status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", getPackageName());
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            return getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    @Override
    public void onTranslucentChanged(int transAlpha) {
        translucentActionBar.tvTitle.setVisibility(transAlpha > 48 ? View.VISIBLE : View.GONE);
    }

}
