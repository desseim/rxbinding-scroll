package com.jakewharton.rxbinding.widget;

import android.view.View;

import com.jakewharton.rxbinding.internal.MainThreadSubscription;
import com.jakewharton.rxbinding.internal.Preconditions;

import rx.Observable;
import rx.Subscriber;

/*package*/ class  ScrollListenableScrollChangeEventOnSubscribe<ScrollListenableView extends View & ScrollListenable> implements Observable.OnSubscribe<ScrollListenableScrollChangeEvent> {
    private final ScrollListenableView view;

    public ScrollListenableScrollChangeEventOnSubscribe(final ScrollListenableView view) {
        this.view = view;
    }

    @Override
    public void call(final Subscriber<? super ScrollListenableScrollChangeEvent> subscriber) {
        Preconditions.checkUiThread();

        final ScrollListenable.OnScrollChangedListener onScrollChangedListener = new ScrollListenable.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(final int l, final int t, final int oldl, final int oldt) {
                subscriber.onNext(ScrollListenableScrollChangeEvent.create(view, oldl, l, oldt, t));
            }
        };

        subscriber.add(new MainThreadSubscription() {
            @Override
            protected void onUnsubscribe() {
                view.setOnScrollChangedListener(null);
            }
        });

        view.setOnScrollChangedListener(onScrollChangedListener);
    }

}
