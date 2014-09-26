package com.byoutline.pinafood.api.tumblr;

import com.byoutline.pinafood.api.tumblr.model.TumblrResponse;

import retrofit.Callback;
import retrofit.http.GET;

/**
* Created by madejs on 26.09.14.
*/
public interface TumblrApi {
    @GET("/v2/blog/wehavethemunchies.tumblr.com/posts?api_key=fD0HOvNDa2z10uyozPZNnjeb4fEFGVGm58zttH6cXSe4K0qC64&limit=20&offset=0")
    void getMunchiesPosts(Callback<TumblrResponse> callback);
}
