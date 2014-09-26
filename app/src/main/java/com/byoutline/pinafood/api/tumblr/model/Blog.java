
package com.byoutline.pinafood.api.tumblr.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Blog {

    @Expose
    private String title;
    @Expose
    private String name;
    @Expose
    private Long posts;
    @Expose
    private String url;
    @Expose
    private Long updated;
    @Expose
    private String description;
    @SerializedName("is_nsfw")
    @Expose
    private Boolean isNsfw;
    @Expose
    private Boolean ask;
    @SerializedName("ask_page_title")
    @Expose
    private String askPageTitle;
    @SerializedName("ask_anon")
    @Expose
    private Boolean askAnon;
    @SerializedName("submission_page_title")
    @Expose
    private String submissionPageTitle;
    @SerializedName("share_likes")
    @Expose
    private Boolean shareLikes;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPosts() {
        return posts;
    }

    public void setPosts(Long posts) {
        this.posts = posts;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsNsfw() {
        return isNsfw;
    }

    public void setIsNsfw(Boolean isNsfw) {
        this.isNsfw = isNsfw;
    }

    public Boolean getAsk() {
        return ask;
    }

    public void setAsk(Boolean ask) {
        this.ask = ask;
    }

    public String getAskPageTitle() {
        return askPageTitle;
    }

    public void setAskPageTitle(String askPageTitle) {
        this.askPageTitle = askPageTitle;
    }

    public Boolean getAskAnon() {
        return askAnon;
    }

    public void setAskAnon(Boolean askAnon) {
        this.askAnon = askAnon;
    }

    public String getSubmissionPageTitle() {
        return submissionPageTitle;
    }

    public void setSubmissionPageTitle(String submissionPageTitle) {
        this.submissionPageTitle = submissionPageTitle;
    }

    public Boolean getShareLikes() {
        return shareLikes;
    }

    public void setShareLikes(Boolean shareLikes) {
        this.shareLikes = shareLikes;
    }

}
