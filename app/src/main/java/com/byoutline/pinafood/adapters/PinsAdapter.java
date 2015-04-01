package com.byoutline.pinafood.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.byoutline.pinafood.R;
import com.byoutline.pinafood.api.parse.model.Pin;
import com.squareup.otto.Bus;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PinsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int PHOTO_TARGET_SIZE = 300;
    private final Context context;
    private final LayoutInflater inflater;
    private final Bus bus;

    private List<Pin> pins = new ArrayList<Pin>();

    public PinsAdapter(Context context, Bus bus) {
        this.bus = bus;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        int type = getItemViewType(i);
        if(type == 0) {
            return new ViewHolderFirst(inflater.inflate(R.layout.pin_item, viewGroup, false));
        } else {
            return new ViewHolderSecond(inflater.inflate(R.layout.pin_item, viewGroup, false));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vh, int i) {
        if(getItemViewType(i) == 0) {

            ViewHolderFirst viewHolder = (ViewHolderFirst) vh;
            Pin pin = pins.get(i);
            viewHolder.pin = pin;
            Picasso.with(context)
                    .load(pin.photoUrl)
                    .resize(PHOTO_TARGET_SIZE,  PHOTO_TARGET_SIZE)
                    .into(viewHolder.pinItemImageImageView);
            viewHolder.pinItemTextView.setText(Html.fromHtml(pin.caption).toString().trim());

        } else {
            ViewHolderSecond viewHolder = (ViewHolderSecond) vh;
            Pin pin = pins.get(i);
            Picasso.with(context)
                    .load(pin.photoUrl)
                    .resize(PHOTO_TARGET_SIZE,  PHOTO_TARGET_SIZE)
                    .into(viewHolder.pinItemImageImageView);
            viewHolder.pinItemTextView.setText(Html.fromHtml(pin.caption).toString().trim());

        }


    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }


    @Override
    public int getItemCount() {
        return pins.size();
    }


    public void addAll(List<Pin> results) {
        pins.clear();
        pins.addAll(results);
        notifyDataSetChanged();
    }


    class ViewHolderFirst extends RecyclerView.ViewHolder implements View.OnClickListener {

        @InjectView(R.id.pin_item_image_dhiv)
        ImageView pinItemImageImageView;
        @InjectView(R.id.pin_item_text_tv)
        TextView pinItemTextView;

        public Pin pin;

        public ViewHolderFirst(View view) {
            super(view);
            ButterKnife.inject(this, view);
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

        }
    }

    static class ViewHolderSecond extends RecyclerView.ViewHolder {

        @InjectView(R.id.pin_item_image_dhiv)
        ImageView pinItemImageImageView;
        @InjectView(R.id.pin_item_text_tv)
        TextView pinItemTextView;

        public ViewHolderSecond(View view) {
            super(view);
            ButterKnife.inject(this, view);
            pinItemTextView.setBackgroundColor(Color.BLUE);
        }
    }
}
