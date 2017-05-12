package com.jshvarts.cleanarchitecture.mvp;

public interface BasePresenter<T> {
    void setView(T view);
    void onViewDestroyed();
}
