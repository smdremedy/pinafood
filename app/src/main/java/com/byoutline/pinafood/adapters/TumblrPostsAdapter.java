package com.byoutline.pinafood.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.byoutline.pinafood.R;
import com.byoutline.pinafood.api.tumblr.model.Photo;
import com.byoutline.pinafood.api.tumblr.model.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TumblrPostsAdapter extends BaseAdapter {

    public static final int PHOTO_TARGET_SIZE = 300;
    private final Context context;
    private final LayoutInflater inflater;
    private List<Post> posts = new ArrayList<Post>();

    public TumblrPostsAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Post getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.pin_list_item, parent, false);

        Post post = getItem(position);
        ImageView imageView = (ImageView) view.findViewById(R.id.list_item_iv);
        if(post.getPhotos().size() > 0) {
            Photo photo = post.getPhotos().get(0);
            Picasso.with(context).load(photo.getOriginalSize().getUrl())
                    .resize(PHOTO_TARGET_SIZE,PHOTO_TARGET_SIZE).centerCrop()
                    .into(imageView);
        }
        TextView textView = (TextView) view.findViewById(R.id.list_item_tv);
        textView.setText(Html.fromHtml(post.getCaption()).toString().trim());


        return view;

    }

    public void addAll(List<Post> posts) {
        this.posts.clear();
        this.posts.addAll(posts);
        notifyDataSetChanged();
    }
}
