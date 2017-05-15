package com.jshvarts.cleanarchitecture.lobby;

import android.os.Build;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jshvarts.cleanarchitecture.BuildConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Unit tests for {@link LobbyActivity}.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class LobbyActivityTest {
    static final CharSequence DUMMY_REPORT_DATA = "dummy report data";

    LobbyActivity testSubject;

    LobbyPresenter presenter;

    TextView displayReportTextView;

    ProgressBar loadingIndicator;

    @Before
    public void setUp() throws Exception {
        testSubject = Robolectric.buildActivity(LobbyActivity.class).create().get();
    }

    @After
    public void tearDown() throws Exception {
        testSubject.finish();
    }

    @Test
    public void onDestroy_callsOnViewDestroyedOnPresenter() throws Exception {
        // GIVEN
        given_presenter();

        // WHEN
        testSubject.onDestroy();

        // THEN
        verify(presenter).onViewDestroyed();
    }

    @Test
    public void onGenerateReportButtonClicked_callsGenerateReportOnPresenter() throws Exception {
        // GIVEN
        given_presenter();

        // WHEN
        testSubject.onGenerateReportButtonClicked();

        // THEN
        verify(presenter).generateReport();
   }

    @Test
    public void displayReportData_setsReportTextViewVisibilityToVisible_andSetsText() throws Exception {
        // GIVEN
        given_displayReportTextView();

        // WHEN
        testSubject.displayReportData(DUMMY_REPORT_DATA);

        // THEN
        verify(displayReportTextView).setVisibility(View.VISIBLE);
        verify(displayReportTextView).setText(DUMMY_REPORT_DATA);
    }

    @Test
    public void hideReportData_setsReportTextViewVisibilityToGone() throws Exception {
        // GIVEN
        given_displayReportTextView();

        // WHEN
        testSubject.hideReportData();

        // THEN
        verify(displayReportTextView).setVisibility(View.GONE);
    }

    @Test
    public void displayLoadingIndicator_setsLoadingIndicatorVisibilityToVisible() throws Exception {
        // GIVEN
        given_loadingIndicator();

        // WHEN
        testSubject.displayLoadingIndicator();

        // THEN
        verify(loadingIndicator).setVisibility(View.VISIBLE);
    }

    @Test
    public void hideLoadingIndicator_setsLoadingIndicatorVisibilityToGone() throws Exception {
        // GIVEN
        given_loadingIndicator();

        // WHEN
        testSubject.hideLoadingIndicator();

        // THEN
        verify(loadingIndicator).setVisibility(View.GONE);
    }

    private void given_presenter() {
        presenter = mock(LobbyPresenter.class);
        testSubject.presenter = presenter;
    }

    private void given_displayReportTextView() {
        displayReportTextView = mock(TextView.class);
        testSubject.displayReportTextView = displayReportTextView;
    }

    private void given_loadingIndicator() {
        loadingIndicator = mock(ProgressBar.class);
        testSubject.loadingIndicator = loadingIndicator;
    }
}