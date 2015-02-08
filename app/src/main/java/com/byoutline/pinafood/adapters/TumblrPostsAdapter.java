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
import com.byoutline.pinafood.api.tumblr.model.Photo;
import com.byoutline.pinafood.api.tumblr.model.Post;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TumblrPostsAdapter extends RecyclerView.Adapter<TumblrPostsAdapter.ViewHolder> {

    public static final int PHOTO_TARGET_SIZE = 300;
    private final Context context;
    private final OnPostClickListener onPostClickListener;
    private final LayoutInflater inflater;
    private List<Post> posts = new ArrayList<Post>();

    public interface OnPostClickListener {
        void postClicked(Post post);
    }

    public TumblrPostsAdapter(Context context, OnPostClickListener onPostClickListener) {
        this.context = context;
        this.onPostClickListener = onPostClickListener;
        this.inflater = LayoutInflater.from(context);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.pin_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Post post = posts.get(i);

        if(post.getPhotos().size() > 0) {
            Photo photo = post.getPhotos().get(0);
            Picasso.with(context).load(photo.getOriginalSize().getUrl())
                    .resize(PHOTO_TARGET_SIZE,PHOTO_TARGET_SIZE).centerCrop()
                    .into(viewHolder.imageView);
        }

        viewHolder.textView.setText(Html.fromHtml(post.getCaption()).toString().trim());
        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPostClickListener.postClicked(post);
            }
        });

    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void addAll(List<Post> posts) {
        this.posts.clear();
        this.posts.addAll(posts);
        notifyDataSetChanged();
    }

    public Post getItem(int position) {
        return posts.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textView;
        private final View rootView;

        public ViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.list_item_iv);
            textView = (TextView) itemView.findViewById(R.id.list_item_tv);
        }
    }
}
