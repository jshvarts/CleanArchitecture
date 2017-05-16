package com.jshvarts.cleanarchitecture.mvp;

import android.support.annotation.CallSuper;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

/**
 * Implements common Presenter actions. Other Presenters should extend it.
 */

public abstract class BasePresenter<T> implements Presenter<T> {

    protected T view;
    private CompositeDisposable disposables = new CompositeDisposable();

    public final void setView(T view) {
        this.view = view;
    }

    @CallSuper
    public void onViewDestroyed() {
        Timber.d("disposing disposables");
        disposables.dispose();
        view = null;
    }

    public final void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }
}
