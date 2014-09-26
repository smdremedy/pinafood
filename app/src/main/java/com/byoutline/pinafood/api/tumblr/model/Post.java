
package com.byoutline.pinafood.api.tumblr.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

import com.byoutline.pinafood.api.tumblr.model.Photo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Post {

    @SerializedName("blog_name")
    @Expose
    private String blogName;
    @Expose
    private Long id;
    @SerializedName("post_url")
    @Expose
    private String postUrl;
    @Expose
    private String slug;
    @Expose
    private String type;
    @Expose
    private String date;
    @Expose
    private Long timestamp;
    @Expose
    private String state;
    @Expose
    private String format;
    @SerializedName("reblog_key")
    @Expose
    private String reblogKey;
    @Expose
    private List<String> tags = new ArrayList<String>();
    @SerializedName("short_url")
    @Expose
    private String shortUrl;
    @SerializedName("post_author")
    @Expose
    private String postAuthor;
    @SerializedName("featured_in_tag")
    @Expose
    private List<String> featuredInTag = new ArrayList<String>();
    @Expose
    private List<Object> highlighted = new ArrayList<Object>();
    @Expose
    private Boolean bookmarklet;
    @SerializedName("note_count")
    @Expose
    private Long noteCount;
    @SerializedName("source_url")
    @Expose
    private String sourceUrl;
    @SerializedName("source_title")
    @Expose
    private String sourceTitle;
    @Expose
    private String caption;
    @SerializedName("link_url")
    @Expose
    private String linkUrl;
    @SerializedName("image_permalink")
    @Expose
    private String imagePermalink;
    @Expose
    private List<Photo> photos = new ArrayList<Photo>();
    @SerializedName("photoset_layout")
    @Expose
    private String photosetLayout;

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getReblogKey() {
        return reblogKey;
    }

    public void setReblogKey(String reblogKey) {
        this.reblogKey = reblogKey;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(String postAuthor) {
        this.postAuthor = postAuthor;
    }

    public List<String> getFeaturedInTag() {
        return featuredInTag;
    }

    public void setFeaturedInTag(List<String> featuredInTag) {
        this.featuredInTag = featuredInTag;
    }

    public List<Object> getHighlighted() {
        return highlighted;
    }

    public void setHighlighted(List<Object> highlighted) {
        this.highlighted = highlighted;
    }

    public Boolean getBookmarklet() {
        return bookmarklet;
    }

    public void setBookmarklet(Boolean bookmarklet) {
        this.bookmarklet = bookmarklet;
    }

    public Long getNoteCount() {
        return noteCount;
    }

    public void setNoteCount(Long noteCount) {
        this.noteCount = noteCount;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getSourceTitle() {
        return sourceTitle;
    }

    public void setSourceTitle(String sourceTitle) {
        this.sourceTitle = sourceTitle;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getImagePermalink() {
        return imagePermalink;
    }

    public void setImagePermalink(String imagePermalink) {
        this.imagePermalink = imagePermalink;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public String getPhotosetLayout() {
        return photosetLayout;
    }

    public void setPhotosetLayout(String photosetLayout) {
        this.photosetLayout = photosetLayout;
    }

}
