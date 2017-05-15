package com.jshvarts.cleanarchitecture.lobby;

import com.jshvarts.cleanarchitecture.rx.SchedulersFacade;
import com.jshvarts.cleanarchitecture.rx.SchedulersFacadeUtils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.reflect.Whitebox;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static com.jshvarts.cleanarchitecture.lobby.LobbyPresenter.REPORT_DELAY_MILLIS;

/**
 * Unit tests for {@link LobbyPresenter}.
 */
public class LobbyPresenterTest {

    static final String DUMMY_REPORT_DATA = "dummy report data";

    @InjectMocks
    LobbyPresenter testSubject;

    @Mock
    LobbyReportUseCase lobbyReportUseCase;

    @Mock
    SchedulersFacade schedulersFacade;

    @Mock
    LobbyView view;

    TestScheduler testScheduler;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Whitebox.setInternalState(testSubject, "view", view);
        testScheduler = SchedulersFacadeUtils.setupSchedulersFacade(schedulersFacade);
    }

    @Test
    public void setView_setsView() throws Exception {
        // WHEN
        Whitebox.setInternalState(testSubject, "view", (LobbyView) null);
        testSubject.setView(view);

        // THEN
        LobbyView result = Whitebox.getInternalState(testSubject, "view");
        assertThat(result, is(view));
    }

    @Test
    public void onViewDestroyed_setsViewToNull() throws Exception {
        // WHEN
        testSubject.onViewDestroyed();

        // THEN
        LobbyView result = Whitebox.getInternalState(testSubject, "view");
        assertThat(result, is(nullValue()));
    }

    @Test
    public void getLocalReport_returnsLocalReport() throws Exception {
        // GIVEN
        when(lobbyReportUseCase.getLocalReport()).thenReturn(DUMMY_REPORT_DATA);

        // WHEN
        String result = testSubject.getLocalReport();

        // THEN
        assertThat(result, is(DUMMY_REPORT_DATA));
    }

    @Test
    public void generateReport_whenReportDataAvailable_displaysReportDataInView() throws Exception {
        // GIVEN
        when(lobbyReportUseCase.generateReport()).thenReturn(Single.just(DUMMY_REPORT_DATA));
        testSubject.generateReport();
        testScheduler.advanceTimeBy(REPORT_DELAY_MILLIS, TimeUnit.MILLISECONDS);

        // WHEN
        testScheduler.triggerActions();

        // THEN
        verify(view).hideReportData();
        verify(view).displayLoadingIndicator();
        verify(view).hideLoadingIndicator();
        verify(view).displayReportData(DUMMY_REPORT_DATA);
        verifyNoMoreInteractions(view);
    }

    @Test
    public void generateReport_whenReportDataError_displaysReportErrorInView() throws Exception {
        // GIVEN
        Throwable throwable = mock(Throwable.class);
        when(lobbyReportUseCase.generateReport()).thenReturn(Single.error(throwable));
        testSubject.generateReport();
        testScheduler.advanceTimeBy(REPORT_DELAY_MILLIS, TimeUnit.MILLISECONDS);

        // WHEN
        testScheduler.triggerActions();

        // THEN
        verify(view).hideReportData();
        verify(view).displayLoadingIndicator();
        verify(view).hideLoadingIndicator();
        verify(view).displayReportError();
        verifyNoMoreInteractions(view);
    }

    @Test
    public void onReportDataAvailable_displaysReportDataInView() throws Exception {
        // WHEN
        testSubject.onReportDataAvailable(DUMMY_REPORT_DATA);

        // THEN
        verify(view).displayReportData(DUMMY_REPORT_DATA);
    }

    @Test
    public void onReportDataError_displaysReportErrorInView() throws Exception {
        // WHEN
        testSubject.onReportDataError(null);

        // THEN
        verify(view).displayReportError();
    }
}