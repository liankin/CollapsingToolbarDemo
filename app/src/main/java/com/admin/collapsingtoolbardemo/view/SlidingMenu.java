package com.admin.collapsingtoolbardemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import com.admin.collapsingtoolbardemo.R;

public class SlidingMenu extends HorizontalScrollView {
    //自定义View布局中内嵌的最外层的LinearLayout
    private LinearLayout mWapper;
    //菜单布局
    private ViewGroup mMenu;
    //内容布局
    private ViewGroup mContent;
    //屏幕宽度
    private int mScreenWidth;
    //菜单距屏幕右侧的距离,单位dp
    private int mMenuRightPadding = 50;
    //菜单的宽度
    private int mMenuWidth;
    //定义标志,保证onMeasure只执行一次
    private boolean once = false;
    //菜单是否是打开状态
    private boolean isOpen = false;
    //是否是抽屉式
    private boolean isDrawerType = false;
    /**
     * 在代码中使用new时会调用此方法
     * @param context
     */
    public SlidingMenu(Context context) {
        this(context, null);
    }

    /**
     * 未使用自定义属性时默认调用
     * @param context
     * @param attrs
     */
    public SlidingMenu(Context context, AttributeSet attrs) {
        //调用三个参数的构造方法
        this(context, attrs, 0);

    }

    /**
     * 当使用了自定义属性时会调用此方法
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //获取我们自定义的属性
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.SlidingMenu, defStyleAttr, 0);
        int n = typedArray.getIndexCount();
        //遍历每一个属性
        for (int i = 0; i < n; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr){
                //对我们自定义属性的值进行读取
                case R.styleable.SlidingMenu_rightPadding:
                    //如果在应用样式时没有赋值则使用默认值50,如果有值则直接读取
                    mMenuRightPadding = typedArray.getDimensionPixelSize(attr,
                            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mMenuRightPadding, context.getResources().getDisplayMetrics()));
                    break;
                case R.styleable.SlidingMenu_drawerType:
                    isDrawerType = typedArray.getBoolean(attr, false);
                    break;
                default:
                    break;
            }
        }
        //释放
        typedArray.recycle();

        //通过以下步骤拿到屏幕宽度的像素值
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        mScreenWidth = displayMetrics.widthPixels;
    }

    /**
     * 设置子View的宽和高
     * 设置自身的宽和高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!once){
            once = true;
            mWapper = (LinearLayout) getChildAt(0);
            mMenu = (ViewGroup) mWapper.getChildAt(0);
            mContent = (ViewGroup) mWapper.getChildAt(1);
            //菜单和内容区域的高度都可以保持默认match_parent
            //菜单宽度 = 屏幕宽度 - 菜单距屏幕右侧的间距
            mMenuWidth = mMenu.getLayoutParams().width = mScreenWidth - mMenuRightPadding;
            mContent.getLayoutParams().width = mScreenWidth;
            //当设置了其中的菜单的宽高和内容区域的宽高之后,最外层的LinearLayout的mWapper就自动设置好了
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 通过设置偏移量将Menu隐藏
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        super.onLayout(changed, l, t, r, b);
        if (changed){
            //布局发生变化时调用(水平滚动条向右移动menu的宽度,则正好将menu隐藏)
            this.scrollTo(mMenuWidth, 0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        //按下和移动使用HorizontalScrollView的默认处理
        switch (action){
            case MotionEvent.ACTION_UP:
                //隐藏在左边的位置
                int scrollX = getScrollX();
                if (scrollX > mMenuWidth / 2){
                    //隐藏的部分较大, 平滑滚动不显示菜单
                    this.smoothScrollTo(mMenuWidth, 0);
                    isOpen = false;
                }else{
                    //完全显示菜单
                    this.smoothScrollTo(0, 0);
                    isOpen = true;
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 打开菜单
     */
    public void openMenu(){
        if (!isOpen){
            this.smoothScrollTo(0, 0);
            isOpen = true;
        }
    }

    /**
     * 关闭菜单
     */
    public void closeMenu(){
        if (isOpen){
            this.smoothScrollTo(mMenuWidth, 0);
            isOpen = false;
        }
    }

    /**
     * 切换菜单
     */
    public void toggleMenu(){
        if (isOpen){
            closeMenu();
        }else{
            openMenu();
        }
    }

    /**
     * 滚动发生时调用
     * @param l  getScrollX()
     * @param t
     * @param oldl
     * @param oldt
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (isDrawerType){
            float scale = l * 1.0f / mMenuWidth;  //1 ~ 0
            //调用属性动画,设TranslationX
            mMenu.setTranslationX(mMenuWidth * scale);
        }
    }
}