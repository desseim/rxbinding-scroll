package com.jakewharton.rxbinding.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * A {@link ScrollView scroll view} which events are listenable so that it can
 * be used with {@link RxScrollListenable} to get its events as {@link rx.Observable observables}.
 */
public class ScrollListenableScrollView extends ScrollView implements ScrollListenable {

    private OnScrollChangedListener onScrollChangedListener;

    public ScrollListenableScrollView(final Context context) {
        super(context);
    }

    public ScrollListenableScrollView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollListenableScrollView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ScrollListenableScrollView(final Context context, final AttributeSet attrs, final int defStyleAttr, final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public synchronized void setOnScrollChangedListener(final OnScrollChangedListener listener) {
        onScrollChangedListener = listener;
    }

    @Override
    protected synchronized void onScrollChanged(final int l, final int t, final int oldl, final int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (onScrollChangedListener != null) {
            onScrollChangedListener.onScrollChanged(l, t, oldl, oldt);
        }
    }

}
