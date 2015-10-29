package com.jakewharton.rxbinding.widget;

import android.support.annotation.NonNull;
import android.view.View;

import com.jakewharton.rxbinding.view.ViewEvent;

public class ScrollListenableScrollChangeEvent extends ViewEvent<View> {
    private final int horizontalScrollPrevious;
    private final int horizontalScrollNew;
    private final int verticalScrollPrevious;
    private final int verticalScrollNew;

    public int horizontalScrollPrevious() { return this.horizontalScrollPrevious; }
    public int horizontalScrollNew() { return this.horizontalScrollNew; }
    public int verticalScrollPrevious() { return this.verticalScrollPrevious; }
    public int verticalScrollNew() { return this.verticalScrollNew; }

    public static <ScrollListenableViewType extends View & ScrollListenable> ScrollListenableScrollChangeEvent create(
            @NonNull final ScrollListenableViewType view,
            final int horizontalScrollPrevious,
            final int horizontalScrollNew,
            final int verticalScrollPrevious,
            final int verticalScrollNew) {
        return new ScrollListenableScrollChangeEvent(view, horizontalScrollPrevious, horizontalScrollNew, verticalScrollPrevious, verticalScrollNew);
    }

    private <ScrollListenableViewType extends View & ScrollListenable> ScrollListenableScrollChangeEvent(
            @NonNull final ScrollListenableViewType view,
            final int horizontalScrollPrevious,
            final int horizontalScrollNew,
            final int verticalScrollPrevious,
            final int verticalScrollNew) {
        super(view);

        this.horizontalScrollPrevious = horizontalScrollPrevious;
        this.horizontalScrollNew = horizontalScrollNew;
        this.verticalScrollPrevious = verticalScrollPrevious;
        this.verticalScrollNew = verticalScrollNew;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof ScrollListenableScrollChangeEvent)) return false;

        final ScrollListenableScrollChangeEvent other = (ScrollListenableScrollChangeEvent) o;

        return view() == other.view() &&
                horizontalScrollPrevious == other.horizontalScrollPrevious &&
                horizontalScrollNew == other.horizontalScrollNew &&
                verticalScrollPrevious == other.verticalScrollPrevious &&
                verticalScrollNew == other.verticalScrollNew;
    }

    @Override
    public int hashCode() {
        int result = horizontalScrollPrevious;
        result = 31 * result + horizontalScrollNew;
        result = 31 * result + verticalScrollPrevious;
        result = 31 * result + verticalScrollNew;
        result = 31 * result + view().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ViewScrollChangeEvent{" +
                "view=" + view() +
                "horizontalScrollPrevious=" + horizontalScrollPrevious +
                ", horizontalScrollNew=" + horizontalScrollNew +
                ", verticalScrollPrevious=" + verticalScrollPrevious +
                ", verticalScrollNew=" + verticalScrollNew +
                '}';
    }
}
