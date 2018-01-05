package com.admin.collapsingtoolbardemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.admin.collapsingtoolbardemo.R;

public class TitleBar extends RelativeLayout {

    private TextView back;
    private TextView title;
    private ImageView rightImg;

    public TitleBar(Context context) {
        super(context,null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_titlebar, this,true);
        back = (TextView) view.findViewById(R.id.back);
        title = (TextView) view.findViewById(R.id.title);
        rightImg = (ImageView) findViewById(R.id.title_right_img);
    }
    public void setTitle(String str){
        title.setText(str);
    }
    public void setBackText(String str){
        back.setText(str);}
    public void setBackListener(OnClickListener listener){
        back.setVisibility(VISIBLE);
        back.setOnClickListener(listener);
    }
    public void setRightImg(int resId,OnClickListener listener){
        rightImg.setImageResource(resId);
        rightImg.setVisibility(VISIBLE);
        rightImg.setOnClickListener(listener);
    }
}
