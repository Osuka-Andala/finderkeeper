package com.osukaandalafinderkeeper.finderkeeper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by osuka on 10/12/15.
 */
public class NewRecyclerViewAdapter extends ArrayAdapter<String> {
    private Context context;
    private String[] navDrawerItems;
    private int[] navDrawerIcons;


    public NewRecyclerViewAdapter(Context context, int resource, String[] navDrawerItems, int[] navDrawerIcons){
        super(context, resource);
        this.context = context;
        this.navDrawerItems = navDrawerItems;
        this.navDrawerIcons = navDrawerIcons;
    }

    private class AdapterViewHolder{
        private TextView tv_NavTitle;
        private ImageView iv_NavIcon;

        private AdapterViewHolder(View v){
            tv_NavTitle = (TextView) v.findViewById(R.id.tv_NavTitle);
            iv_NavIcon = (ImageView) v.findViewById(R.id.iv_NavIcon);
        }
    }

    @Override
    public int getCount() {
        return navDrawerItems.length;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        AdapterViewHolder holder;

        if (row==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.drawer_item_layout, parent,false);
            holder=new AdapterViewHolder(row);
            row.setTag(holder);
        }
        else{
            holder = (AdapterViewHolder) row.getTag();
        }
        holder.tv_NavTitle.setText(navDrawerItems [position]);
        holder.iv_NavIcon.setImageResource(navDrawerIcons[position]);
        holder.tv_NavTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0) {
                    Intent intent = new Intent(context, Profile.class);
                    context.startActivity(intent);
                }

                if (position == 1) {
                    Intent intent = new Intent(context, ScheduledRides.class);
                    context.startActivity(intent);
                }

                if (position == 2) {
                    Intent intent = new Intent(context, NewsRoom.class);
                    context.startActivity(intent);
                }

                if (position == 3) {
                    Intent intent = new Intent(context, HowIsTraffic.class);
                    context.startActivity(intent);
                }

                if (position == 4) {
                    Intent intent = new Intent(context, Rate.class);
                    context.startActivity(intent);
                }

                if (position == 5) {
                    Intent intent = new Intent(context, Help.class);
                    context.startActivity(intent);
                }

                if (position == 6) {
                    Intent intent = new Intent(context, Settings.class);
                    context.startActivity(intent);
                }

                if (position == 7) {
                    Intent intent = new Intent(context, Logout.class);
                    context.startActivity(intent);
                }
            }
        });
        return row;


    }
}
