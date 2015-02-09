package com.byoutline.pinafood;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.byoutline.secretsauce.utils.FontCache;

/**
 * Created by madejs on 04.12.14.
 */
public class CustomFontTextView extends com.byoutline.secretsauce.views.CustomFontTextView {
    public CustomFontTextView(Context context) {
        super(context);
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setFont(String font) {
        setCustomFont(getContext(), font);
    }

    public void setCustomFont(Context ctx, String font) {
//        if (isInEditMode()) {
//            return;
//        }

        if (font == null || TextUtils.isEmpty(font)) {
            return;
        }
        Typeface tf = FontCache.get(font, ctx);
        if (tf != null) {
            setTypeface(tf);
        }
    }
}
