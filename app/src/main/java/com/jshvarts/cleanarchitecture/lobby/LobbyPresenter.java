package com.jshvarts.cleanarchitecture.lobby;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.jshvarts.cleanarchitecture.mvp.BasePresenter;
import com.jshvarts.cleanarchitecture.rx.SchedulersFacade;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import timber.log.Timber;

/**
 * Lobby Presenter that provides interaction between lobby view layer from business logic.
 */

class LobbyPresenter extends BasePresenter<LobbyView> {

    final static int REPORT_DELAY_MILLIS = 500;

    enum RequestState {
        IDLE,
        LOADING,
        COMPLETE,
        ERROR
    }

    private final LobbyReportUseCase lobbyReportUseCase;

    private final SchedulersFacade schedulersFacade;

    private BehaviorRelay<RequestState> state = BehaviorRelay.createDefault(RequestState.IDLE);

    @Inject
    LobbyPresenter(LobbyReportUseCase lobbyReportUseCase, SchedulersFacade schedulersFacade) {
        this.lobbyReportUseCase = lobbyReportUseCase;
        this.schedulersFacade = schedulersFacade;
        setupRequestStateObserver();
    }

    String getLocalReport() {
        String localReport = lobbyReportUseCase.getLocalReport();
        Timber.d("localReport: %s", localReport);
        return localReport;
    }

    void generateReport() {
        addDisposable(lobbyReportUseCase.generateReport()
                .doOnSubscribe(s -> publishRequestState(RequestState.LOADING))
                .subscribeOn(schedulersFacade.io())
                .observeOn(schedulersFacade.ui())
                .delay(REPORT_DELAY_MILLIS, TimeUnit.MILLISECONDS, schedulersFacade.ui()) // demonstrate loading indicator ui while loading
                .doOnSuccess(s -> publishRequestState(RequestState.COMPLETE))
                .doOnError(t -> publishRequestState(RequestState.ERROR))
                .subscribe(this::onReportDataAvailable, this::onReportDataError));
    }

    void onReportDataAvailable(String report) {
        Timber.d("report data: %s", report);
        view.displayReportData(report);
    }

    void onReportDataError(Throwable throwable) {
        Timber.e(throwable, "report error");
        view.displayReportError();
    }

    void publishRequestState(RequestState requestState) {
        addDisposable(Observable.just(requestState)
                .observeOn(schedulersFacade.ui())
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
