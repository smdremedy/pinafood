package com.byoutline.pinafood.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.byoutline.pinafood.R;
import com.byoutline.pinafood.api.parse.model.Pin;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PinsAdapter extends RecyclerView.Adapter<PinsAdapter.ViewHolder> {

    public static final int PHOTO_TARGET_SIZE = 300;

    private final Context context;
    private final LayoutInflater inflater;
    private final PinClickedListener pinClickedListener;

    private List<Pin> pins = new ArrayList<Pin>();

    public interface PinClickedListener {
        void pinClicked(Pin pin, View view);
    }

    public PinsAdapter(Context context, PinClickedListener pinClickedListener) {
        this.pinClickedListener = pinClickedListener;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(inflater.inflate(R.layout.pin_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final Pin pin = pins.get(i);
        Picasso.with(context)
                .load(pin.photoUrl)
                .resize(PHOTO_TARGET_SIZE,  PHOTO_TARGET_SIZE)
                .into(viewHolder.pinItemImageImageView);
        viewHolder.pinItemTextView.setText(Html.fromHtml(pin.caption).toString().trim());
        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pinClickedListener.pinClicked(pin, viewHolder.pinItemImageImageView);
            }
        });


    }

    @Override
    public long getItemId(int position) {
        return 0;
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

    static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.pin_item_image_dhiv)
        ImageView pinItemImageImageView;
        @InjectView(R.id.pin_item_text_tv)
        TextView pinItemTextView;
        private View rootView;

        ViewHolder(View view) {
            super(view);
            rootView = view;
            ButterKnife.inject(this, view);
        }
    }

}
