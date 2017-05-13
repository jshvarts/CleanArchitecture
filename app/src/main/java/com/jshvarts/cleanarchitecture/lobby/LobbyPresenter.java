package com.jshvarts.cleanarchitecture.lobby;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.jshvarts.cleanarchitecture.mvp.BasePresenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Lobby Presenter that provides interaction between lobby view layer from business logic.
 */

class LobbyPresenter implements BasePresenter<LobbyView> {

    private final static int REPORT_DELAY_MILLIS = 500;

    enum RequestState {
        IDLE,
        LOADING,
        COMPLETE,
        ERROR
    }

    BehaviorRelay<RequestState> state = BehaviorRelay.createDefault(RequestState.IDLE);

    private final LobbyReportUseCase lobbyReportUseCase;

    private LobbyView view;

    private CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    LobbyPresenter(LobbyReportUseCase lobbyReportUseCase) {
        this.lobbyReportUseCase = lobbyReportUseCase;
        setupRequestStateObserver();
    }

    @Override
    public void setView(LobbyView view) {
        this.view = view;
    }

    @Override
    public void onViewDestroyed() {
        Timber.d("disposing disposables");
        disposables.dispose();
        view = null;
    }

    void generateReport() {
        disposables.add(lobbyReportUseCase.generateReport()
                .doOnSubscribe(s -> publishRequestState(RequestState.LOADING))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .delay(REPORT_DELAY_MILLIS, TimeUnit.MILLISECONDS,
                        AndroidSchedulers.mainThread()) // demonstrate loading indicator ui while loading
                .doOnSuccess(s -> publishRequestState(RequestState.COMPLETE))
                .doOnError(t -> publishRequestState(RequestState.ERROR))
                .subscribe(this::onReportDataAvailable, this::onReportDataError));
    }

    void onReportDataAvailable(String report) {
        Timber.d("report data: " + report);
        view.displayReportData(report);
    }

    void onReportDataError(Throwable throwable) {
        Timber.e("report error", throwable);
        view.displayReportError();
    }

    void publishRequestState(RequestState requestState) {
        disposables.add(Observable.just(requestState)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(state));
    }

    private void setupRequestStateObserver() {
        state.subscribe(requestState -> {
            switch (requestState) {
                case IDLE:
                    break;
                case LOADING:
                    view.hideReportData();
                    view.displayLoadingIndicator();
                    break;
                case COMPLETE:
                    view.hideLoadingIndicator();
                    break;
                case ERROR:
                    view.hideLoadingIndicator();
                    break;
            }
        }, Timber::e);
    }
}
