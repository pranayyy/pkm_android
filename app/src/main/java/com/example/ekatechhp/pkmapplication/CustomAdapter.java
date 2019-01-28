package com.example.ekatechhp.pkmapplication;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    ArrayList<User> userEmailList;
    Context context;
    public CustomAdapter(Context context, ArrayList<User> userEmailList) {
        this.userEmailList = userEmailList;
        this.context = context;
    }
    public int getCount() {
        return this.userEmailList.size();
    }
    public Object getItem(int position) {
        return this.userEmailList.get(position);
    }
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.fragment_one, null);
            holder = new ViewHolder();
            holder.listViewShare = (ListView)convertView.findViewById(R.id.listViewShare);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }
        User us = userEmailList.get(position);
        holder.listViewShare.setFilterText(us.getEmail());
        return convertView;
    }
    public static class ViewHolder {
        ListView listViewShare;
    }
}
