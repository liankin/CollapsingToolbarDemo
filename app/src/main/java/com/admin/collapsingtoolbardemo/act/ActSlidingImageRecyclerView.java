package com.admin.collapsingtoolbardemo.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.admin.collapsingtoolbardemo.R;
import com.admin.collapsingtoolbardemo.view.AdImageViewVersion1;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 往上滚动：在图片刚出现时展示顶部部分，随着滚动部分展示全部
 * 往下滚动：在图片刚出现时展示底部部分，随着滚动部分展示全部
 */

public class ActSlidingImageRecyclerView extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_slidingimage_recyclerview);
        ButterKnife.bind(this);

        List<String> mockDatas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mockDatas.add(i + "");
        }
        recyclerView.setLayoutManager(mLinearLayoutManager = new LinearLayoutManager(this));
        recyclerView.setAdapter(new CommonAdapter<String>(ActSlidingImageRecyclerView.this,
                R.layout.item_recyclerview,
                mockDatas) {
            @Override
            protected void convert(ViewHolder holder, String o, int position) {
                //显示7次文本后才显示一次图片
                if (position > 0 && position % 7 == 0) {
                    holder.setVisible(R.id.image_view, true);
                    holder.setVisible(R.id.tv_title, false);
                    holder.setVisible(R.id.tv_content, false);
                } else {
                    holder.setVisible(R.id.image_view, false);
                    holder.setVisible(R.id.tv_title, true);
                    holder.setVisible(R.id.tv_content, true);
                }
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int fPos = mLinearLayoutManager.findFirstVisibleItemPosition();
                int lPos = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                for (int i = fPos; i <= lPos; i++) {
                    View view = mLinearLayoutManager.findViewByPosition(i);
                    AdImageViewVersion1 adImageView = view.findViewById(R.id.image_view);
                    if (adImageView.getVisibility() == View.VISIBLE) {
                        adImageView.setDy(mLinearLayoutManager.getHeight() - view.getTop());
                    }
                }
            }
        });
    }
}
