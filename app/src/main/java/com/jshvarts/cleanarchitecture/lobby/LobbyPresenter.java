package com.jshvarts.cleanarchitecture.lobby;

import com.jshvarts.cleanarchitecture.mvp.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Lobby Presenter that provides interaction between lobby view layer from business logic.
 */

class LobbyPresenter implements BasePresenter<LobbyView> {
    private final LobbyReportUseCase lobbyReportUseCase;

    private LobbyView view;

    private Disposable disposable;

    @Inject
    LobbyPresenter(LobbyReportUseCase lobbyReportUseCase) {
        this.lobbyReportUseCase = lobbyReportUseCase;
    }

    @Override
    public void setView(LobbyView view) {
        this.view = view;
    }

    @Override
    public void onViewDestroyed() {
        if (disposable != null) {
            Timber.d("disposing subscriptions");
            disposable.dispose();
        }
        view = null;
    }

    void generateReport() {
        disposable = lobbyReportUseCase.generateReport()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(items -> items.toString())
                .subscribe(this::onReportDataAvailable, this::onReportDataError);
    }

    void onReportDataAvailable(String report) {
        Timber.d("report data: " + report);
        view.onDisplayReportData(report);
    }

    void onReportDataError(Throwable throwable) {
        Timber.e("report error", throwable);
        view.onDisplayReportError();
    }
}
