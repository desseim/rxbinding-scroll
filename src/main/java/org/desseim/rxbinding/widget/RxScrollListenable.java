package com.jakewharton.rxbinding.widget;

import android.support.annotation.NonNull;
import android.view.View;

import rx.Observable;

/**
 * Static factory methods for creating {@linkplain Observable observables} for {@link View views}
 * implementing the {@link ScrollListenable} interface.
 *
 * Unfortunately since the Android framework doesn't provide such views at the moment, one will
 * have to be implemented manually to use with this factory.
 * @see ScrollListenableScrollView an example of such an implementation for {@link android.widget.ScrollView}
 */
public final class RxScrollListenable {

    /**
     * Create an {@link Observable} to the scroll events of a {@link ScrollListenable} view.
     * The events are normalized so that each consecutive event is unique (it filters out events
     * strictly equal to their previous event -- the Android framework often sends scrolling events
     * twice in a row).
     *
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
     * to free this reference.
     *
     * @param view the view to get the scroll events of
     * @param <ScrollListenableView> the concrete type of the view
     * @return an observable to the scroll events of the parameter view
     * @see #scrollChangeRawEvents(View) same but notifies of <b>all</b> events without any normalizing
     */
    public static <ScrollListenableView extends View & ScrollListenable>
    Observable<ScrollListenableScrollChangeEvent> scrollChangeEvents(final @NonNull ScrollListenableView view) {
        return scrollChangeRawEvents(view)
                .distinctUntilChanged();
    }

    /**
     * Create an {@link Observable} to the scroll events of a {@link ScrollListenable} view.
     * All scroll events sent to the view by the Android framework will be notified to the resulting
     * {@link Observable}.
     *
     * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
     * to free this reference.
     *
     * @param view the view to get the scroll events of
     * @param <ScrollListenableView> the concrete type of the view
     * @return an observable to the scroll events of the parameter view
     */
    public static <ScrollListenableView extends View & ScrollListenable>
    Observable<ScrollListenableScrollChangeEvent> scrollChangeRawEvents(final @NonNull ScrollListenableView view) {
        return Observable.create(new ScrollListenableScrollChangeEventOnSubscribe<>(view));
    }

}
