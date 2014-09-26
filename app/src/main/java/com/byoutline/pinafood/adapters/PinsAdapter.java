package com.byoutline.pinafood.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.byoutline.pinafood.R;
import com.byoutline.pinafood.api.parse.model.Pin;

import java.util.ArrayList;
import java.util.List;

public class PinsAdapter extends BaseAdapter {

    public static final int PHOTO_TARGET_SIZE = 300;
    private final Context context;
    private final LayoutInflater inflater;

    private List<Pin> pins = new ArrayList<Pin>();

    public PinsAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return pins.size();
    }

    @Override
    public Pin getItem(int position) {
        return pins.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.pin_item, parent, false);
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        if(viewHolder == null) {
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        Pin pin = getItem(position);

        return convertView;
    }

    public void addAll(List<Pin> results) {
        pins.clear();
        pins.addAll(results);
        notifyDataSetChanged();
    }

    private class ViewHandler {
    }

    static class ViewHolder {

        ViewHolder(View view) {

        }
    }
}
