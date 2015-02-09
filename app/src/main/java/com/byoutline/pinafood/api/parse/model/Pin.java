package com.byoutline.pinafood.api.parse.model;

import com.byoutline.pinafood.api.tumblr.model.Post;
import com.byoutline.pinafood.api.tumblr.model.OriginalSize;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pin implements Serializable {

    public String caption;
    public String linkUrl;
    public String photoUrl;
    public long photoWidth;
    public long photoHeight;

    @SerializedName("ACL")
    public transient JsonObject acl;

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
        JsonObject permissions = new JsonObject();
        permissions.addProperty("read", true);
        permissions.addProperty("write", true);
        acl.add(userObjectId, permissions);
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
