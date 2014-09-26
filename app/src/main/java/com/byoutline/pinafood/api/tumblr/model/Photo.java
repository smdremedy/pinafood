
package com.byoutline.pinafood.api.tumblr.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Photo {

    @Expose
    private String caption;
    @SerializedName("alt_sizes")
    @Expose
    private List<AltSize> altSizes = new ArrayList<AltSize>();
    @SerializedName("original_size")
    @Expose
    private OriginalSize originalSize;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public List<AltSize> getAltSizes() {
        return altSizes;
    }

    public void setAltSizes(List<AltSize> altSizes) {
        this.altSizes = altSizes;
    }

    public OriginalSize getOriginalSize() {
        return originalSize;
    }

    public void setOriginalSize(OriginalSize originalSize) {
        this.originalSize = originalSize;
    }

}
