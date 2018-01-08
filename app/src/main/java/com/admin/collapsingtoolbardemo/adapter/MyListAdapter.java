package com.admin.collapsingtoolbardemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.admin.collapsingtoolbardemo.R;
import com.admin.collapsingtoolbardemo.utils.ToastUtil;
import com.admin.collapsingtoolbardemo.view.AdImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/1/8.
 */

public class MyListAdapter extends BaseAdapter<String> {

    public MyListAdapter(Context context) {
        super(context);
    }

    @Override
    protected View getItemView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_swipemenu, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.itemContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showMessage(mContext, "itemContent");
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showMessage(mContext, "delete");
            }
        });
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showMessage(mContext, "edit");
            }
        });
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.image_view)
        AdImageView imageView;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.item_content)
        RelativeLayout itemContent;
        @BindView(R.id.btn_edit)
        Button btnEdit;
        @BindView(R.id.btn_delete)
        Button btnDelete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
