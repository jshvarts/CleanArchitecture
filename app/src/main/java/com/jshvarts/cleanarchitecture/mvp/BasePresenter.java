package com.jshvarts.cleanarchitecture.mvp;

/**
 * Defines common Presenter actions.
 *
 * @param <T>
 */
public interface BasePresenter<T> {
    void setView(T view);
    void onViewDestroyed();
}
