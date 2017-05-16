package com.jshvarts.cleanarchitecture.mvp;

import io.reactivex.disposables.Disposable;

/**
 * Defines common Presenter actions.
 */

interface Presenter<T> {
    void setView(T view);
    void onViewDestroyed();

    /**
     * Allows handling RxSubscriptions to avoid memory leaks
     */
    void addDisposable(Disposable disposable);
}
