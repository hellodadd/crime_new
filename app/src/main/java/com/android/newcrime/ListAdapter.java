package com.android.newcrime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.newcrime.databases.CrimeItem;
import com.android.newcrime.utils.DateTimePicker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zwb on 2017/3/11.
 */

public class ListAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater myInflater;
    private List<CrimeItem> items;

    public ListAdapter(Context context, List<CrimeItem> items){
        myInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.items = items;
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(convertView==null){
            convertView = myInflater.inflate(R.layout.listview, null);
            holder = new ViewHolder(
                    (ImageView)convertView.findViewById(R.id.list_icon),
                    (TextView)convertView.findViewById(R.id.list_item_name),
                    (TextView)convertView.findViewById(R.id.list_item_time)
            );
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        CrimeItem item = (CrimeItem)getItem(position);
        holder.icon.setBackground(mContext.getResources().getDrawable(R.drawable.no_completed));
        holder.caseName.setText(item.getCaseName());
        holder.caseTime.setText(DateTimePicker.getCurrentDashTime(item.getCaseStartTime()));
        return convertView;
    }

    private class ViewHolder{
        ImageView icon;
        TextView caseName;
        TextView caseTime;
        public ViewHolder(ImageView icon, TextView caseName, TextView caseTime){
            this.icon = icon;
            this.caseName = caseName;
            this.caseTime = caseTime;
        }
    }
}
