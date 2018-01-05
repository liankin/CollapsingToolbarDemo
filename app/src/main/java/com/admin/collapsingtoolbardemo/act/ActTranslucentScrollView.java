package com.admin.collapsingtoolbardemo.act;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.admin.collapsingtoolbardemo.R;
import com.admin.collapsingtoolbardemo.view.TranslucentToolBarClickListener;
import com.admin.collapsingtoolbardemo.view.TranslucentToolBar;
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
    TranslucentToolBar translucentToolBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_translucentscrollview);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        initView();
    }

    private void initView() {
        // 初始actionBar：
        // public void setData(String strTitle, int resIdLeft, String strLeft,
        // int resIdRight, String strRight, final TranslucentToolBarClickListener listener)
//        actionBar.setData("测试", 0, null, 0, null, null);
        translucentToolBar.setData("个人中心", R.drawable.ic_left_light, "返回", R.drawable.ic_sign, "消息", new TranslucentToolBarClickListener() {
            @Override
            public void onLeftClick() {
//                Toast.makeText(ActTranslucentScrollView.this, "点击了左侧按钮", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onRightClick() {
                Toast.makeText(ActTranslucentScrollView.this, "点击了右侧按钮", Toast.LENGTH_SHORT).show();
            }
        });
        //开启渐变
        translucentToolBar.setNeedTranslucent();
        //设置状态栏(标题栏)高度
//        actionBar.setStatusBarHeight(30);
        translucentToolBar.setStatusBarHeight(getStatusBarHeight());

        //设置透明度变化监听
        translucentScrollView.setTranslucentChangedListener(this);
        //关联需要渐变的视图
        translucentScrollView.setTransView(translucentToolBar);
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
        translucentToolBar.tvTitle.setVisibility(transAlpha > 48 ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
