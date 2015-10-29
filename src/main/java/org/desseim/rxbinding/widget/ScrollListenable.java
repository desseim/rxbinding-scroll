package com.jakewharton.rxbinding.widget;

public interface ScrollListenable {

    interface OnScrollChangedListener {
        void onScrollChanged(int l, int t, int oldl, int oldt);
    }

    void setOnScrollChangedListener(OnScrollChangedListener listener);

}
