package com.anu.gp24s1.ui;

import android.content.Context;
import android.util.AttributeSet;

public class PostMultiAutoCompleteTextView extends androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView {

    public PostMultiAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PostMultiAutoCompleteTextView(Context context) {
        this(context, null);
    }

    // @Override
//    protected void performFiltering(CharSequence text, int keyCode) {
//        System.out.println("text: " + text);
//        System.out.println("keyCode: " + keyCode);
//
//    }


}
