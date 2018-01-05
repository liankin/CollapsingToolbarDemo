package com.admin.collapsingtoolbardemo.act;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.admin.collapsingtoolbardemo.R;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * CollapsingToolbarLayout可折叠式标题栏：
 * 向上滑动列表，使列表顶部的标题随之滑动并固定于标题栏；向下滑动，标题则恢复原位。
 */
public class ActCoordinatorLayout extends AppCompatActivity {

    @BindView(R.id.iv)
    ImageView imageView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    private SearchView mSearchView;
    private SearchView.SearchAutoComplete searchAutoComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_coordinatorlayout);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        initToolbar();
        collapsingToolbarLayout.setTitle("风景如画");
        collapsingToolbarLayout.setCollapsedTitleTextColor(this.getResources().getColor(R.color.colorOrange));
        collapsingToolbarLayout.setExpandedTitleColor(this.getResources().getColor(R.color.colorOrange));
//        imageView.setImageResource(R.drawable.bg_banner);
    }

    private void initToolbar(){
        //用toolbar替换actionbar
        setSupportActionBar(toolbar);
        //设置导航Icon，必须在setSupportActionBar(toolbar)之后设置
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        //添加菜单点击事件
        //Toolbar返回按钮的点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchAutoComplete.isShown()) {
                    try {
                        //如果搜索框中有文字，则会先清空文字
                        //searchAutoComplete.setText("");
                        Method method = mSearchView.getClass().getDeclaredMethod("onCloseClicked");
                        method.setAccessible(true);
                        method.invoke(mSearchView);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    finish();
                }
            }
        });
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_search:
                        // 因为使用android.support.v7.widget.SearchView类，可以在
                        // onCreateOptionsMenu(Menu menu)中直接设置监听事件
                        Toast.makeText(ActCoordinatorLayout.this,"搜索",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_share:
                        Toast.makeText(ActCoordinatorLayout.this,"分享",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_more:
                        Toast.makeText(ActCoordinatorLayout.this,"更多",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_setting:
                        Toast.makeText(ActCoordinatorLayout.this,"设置",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_exit:
                        Toast.makeText(ActCoordinatorLayout.this,"退出",Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    /**
     * 加载菜单布局的
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载菜单文件
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        final MenuItem item = menu.findItem(R.id.menu_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(item);
        if(mSearchView != null){
            setSearchViewStyle();
        }
        return true;
    }
    /**
     * 让菜单同时显示图标和文字
     */
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    /**
     * 设置SearchView
     */
    public void setSearchViewStyle(){
        //文本输入框
        searchAutoComplete = ( SearchView.SearchAutoComplete) mSearchView.findViewById(R.id.search_src_text);
        if(searchAutoComplete != null){
            searchAutoComplete.setTextColor(this.getResources().getColor(R.color.colorGray));
            searchAutoComplete.setHint("请输入搜索内容");
            searchAutoComplete.setHintTextColor(this.getResources().getColor(R.color.colorGray));
            searchAutoComplete.setTextSize(14);
            //searchAutoComplete.setThreshold(1);//设置触发查询的最少字符数（默认2个字符才会触发查询）
        }
        mSearchView.setQueryHint("请输入搜索内容");//设置输入框提示语
        //设置搜索框有字时显示叉叉，无字时隐藏叉叉
        mSearchView.onActionViewExpanded();
        mSearchView.setIconified(true);
        //修改搜索框控件间的间隔
        LinearLayout search_edit_frame = (LinearLayout) mSearchView.findViewById(R.id.search_edit_frame);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) search_edit_frame.getLayoutParams();
        params.leftMargin = 0;
        params.rightMargin = 0;
        search_edit_frame.setLayoutParams(params);
        //mSearchView.setMaxWidth(500);//设置最大宽度
        //mSearchView.setSubmitButtonEnabled(true);//设置是否显示搜索框展开时的提交按钮
        //搜索图标按钮(打开搜索框的按钮)的点击事件
        mSearchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Open", Toast.LENGTH_SHORT).show();
            }
        });
        //搜索框展开时后面叉叉按钮的点击事件
        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Toast.makeText(getApplicationContext(), "Close", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //搜索框文字变化监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(ActCoordinatorLayout.this,"search："+query,Toast.LENGTH_SHORT).show();
                return false;//true：点击搜索后不收起键盘；false：收起。
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(ActCoordinatorLayout.this,"inputTextChange："+newText,Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
