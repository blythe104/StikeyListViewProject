package heros.com.xin.stickylistviewproject;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.emilsjolander.components.stickylistheaders.StickyListHeadersListView;
import com.emilsjolander.components.stickylistheaders.StickyListHeadersListView.OnHeaderClickListener;
import com.emilsjolander.components.stickylistheaders.StickyListHeadersListView.OnLoadingMoreLinstener;
import com.wly.sticky_pagelist.StickyListAdapter;

import java.util.ArrayList;

public class MainActivity extends Activity implements OnHeaderClickListener, AdapterView.OnItemClickListener
        , OnLoadingMoreLinstener {

    ArrayList<String> list;
    StickyListAdapter adapter;
    StickyListHeadersListView stickyLV;
    private LayoutInflater inflater;
    private RelativeLayout moredata;
    private View progressBarView;
    private TextView progressBarTextView;
    private AnimationDrawable loadingAnimation; //加载更多，动画
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<String>();
        for (int i = 1; i <= 15; i++) {
            list.add(i + "");
        }
        adapter = new StickyListAdapter();
        adapter.init(this, list);

        inflater = LayoutInflater.from(this);
        moredata = (RelativeLayout) inflater.inflate(R.layout.moredata, null);

        stickyLV = (StickyListHeadersListView) this.findViewById(R.id.stickyList);
        progressBarView = (View) moredata.findViewById(R.id.loadmore_foot_progressbar);
        progressBarTextView = (TextView) moredata.findViewById(R.id.loadmore_foot_text);
        loadingAnimation = (AnimationDrawable) progressBarView.getBackground();
        stickyLV.addFooterView(moredata);
        stickyLV.setAdapter(adapter);
         //item点击事件
        //   stickyLV.setOnItemClickListener(this);
        //header点击事件
        //    stickyLV.setOnHeaderClickListener(this);
        stickyLV.setLoadingMoreListener(this);
    }

    public void loadingFinished() {

        if (null != loadingAnimation && loadingAnimation.isRunning()) {
            loadingAnimation.stop();
        }
        progressBarView.setVisibility(View.INVISIBLE);
        progressBarTextView.setVisibility(View.INVISIBLE);
        isLoading = false;

        adapter.notifyDataSetChanged();
    }

    @Override
    public void OnLoadingMore() {
        progressBarView.setVisibility(View.VISIBLE);
        progressBarTextView.setVisibility(View.VISIBLE);

        loadingAnimation.start();

        if (!isLoading) {
            isLoading = true;
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    for (int i = 0; i < 5; i++) {
                        list.add((Math.random() * 40) + "");
                    }
                    loadingFinished();
                }
            }, 1200);
        }
    }

    @Override
    public void onHeaderClick(StickyListHeadersListView l, View header,
                              int itemPosition, long headerId, boolean currentlySticky) {
        Toast.makeText(this, "header：" + headerId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Toast.makeText(this, "item：" + position, Toast.LENGTH_SHORT).show();
    }
}
