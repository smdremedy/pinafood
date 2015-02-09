package com.byoutline.pinafood;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by madejs on 04.12.14.
 */
public class CustomFontEditText extends com.byoutline.secretsauce.views.CustomFontEditText {
    public CustomFontEditText(Context context) {
        super(context);
    }

    public CustomFontEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomFontEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setCustomFont(String font) {
        setCustomFont(getContext(), font);
    }
}
