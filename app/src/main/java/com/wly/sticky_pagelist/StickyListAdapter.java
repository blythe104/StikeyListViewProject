package com.wly.sticky_pagelist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.emilsjolander.components.stickylistheaders.StickyListHeadersAdapter;

import java.util.ArrayList;
import java.util.Calendar;

import heros.com.xin.stickylistviewproject.R;

public class StickyListAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private ArrayList<String> list;
    private Context mContext;
    private int tempMonth;

    public void init(Context context, ArrayList<String> list) {
        this.list = list;
        this.mContext = context;
        mContext = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView itemView = new TextView(mContext);
        itemView.setText("Item:" + position);
        itemView.setTextSize(20);
        return itemView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        HeaderViewHolder headerHolder;
        if (null == convertView) {
            headerHolder = new HeaderViewHolder();
            //填充部分
            convertView = View.inflate(mContext, R.layout.laout_header_item, null);
            headerHolder.month = (TextView) convertView.findViewById(R.id.tv_month);
            convertView.setTag(headerHolder);
        } else {
            headerHolder = (HeaderViewHolder) convertView.getTag();
        }
        headerHolder.month.setText(month + "");
        return convertView;

    }

    @Override
    public long getHeaderId(int position) {
        return position / 10 + 0x1234;
    }

    static class HeaderViewHolder {
        public TextView month;
    }
}
