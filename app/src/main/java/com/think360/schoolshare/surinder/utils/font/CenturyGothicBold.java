package com.think360.schoolshare.surinder.utils.font;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by user on 12/9/2015.
 */
public class CenturyGothicBold extends android.support.v7.widget.AppCompatTextView {

    public CenturyGothicBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CenturyGothicBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CenturyGothicBold(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/gothicb.ttf");
            setTypeface(tf);
        }
    }
}
