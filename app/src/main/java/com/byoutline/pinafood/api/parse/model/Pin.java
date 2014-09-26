package com.byoutline.pinafood.api.parse.model;

import com.byoutline.pinafood.api.tumblr.model.Post;
import com.byoutline.pinafood.api.tumblr.model.OriginalSize;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class Pin {

    public String caption;
    public String linkUrl;
    public String photoUrl;
    public long photoWidth;
    public long photoHeight;

    @SerializedName("ACL")
    public JsonObject acl;

    public Pin(Post item, String userObjectId) {
        caption = item.getCaption();
        linkUrl = item.getLinkUrl();
        if(item.getPhotos().size() > 0) {
            OriginalSize originalSize = item.getPhotos().get(0).getOriginalSize();

            photoUrl = originalSize.getUrl();
            photoWidth = originalSize.getWidth();
            photoHeight = originalSize.getHeight();
        }

        acl = new JsonObject();
        JsonObject permisions = new JsonObject();
        permisions.addProperty("read", true);
        permisions.addProperty("write", true);
        acl.add(userObjectId, permisions);
    }

    @Override
    public String toString() {
        return "Pin{" +
                "caption='" + caption + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", photoWidth=" + photoWidth +
                ", photoHeight=" + photoHeight +
                ", acl=" + acl +
                '}';
    }
}
