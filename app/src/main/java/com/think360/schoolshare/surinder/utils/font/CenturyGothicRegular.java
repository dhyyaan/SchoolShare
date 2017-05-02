package com.think360.schoolshare.surinder.utils.font;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by user on 12/9/2015.
 */
public class CenturyGothicRegular extends TextView {

    public CenturyGothicRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CenturyGothicRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CenturyGothicRegular(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/century_gothic.ttf");
            setTypeface(tf);
        }
    }
}
