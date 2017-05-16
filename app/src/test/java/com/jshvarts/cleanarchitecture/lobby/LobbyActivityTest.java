package com.jshvarts.cleanarchitecture.lobby;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jshvarts.cleanarchitecture.BuildConfig;
import com.jshvarts.cleanarchitecture.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;

/**
 * Unit tests for {@link LobbyActivity}.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class LobbyActivityTest {
    static final CharSequence DUMMY_REPORT_DATA = "dummy report data";

    @InjectMocks
    LobbyActivity testSubject;

    @Mock
    LobbyPresenter presenter;

    @Mock
    TextView displayReportTextView;

    @Mock
    ProgressBar loadingIndicator;

    @Before
    public void setUp() throws Exception {
        testSubject = Robolectric.setupActivity(LobbyActivity.class);
        displayReportTextView = (TextView) testSubject.findViewById(R.id.display_report);
        loadingIndicator = (ProgressBar) testSubject.findViewById(R.id.loading_indicator);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void onDestroy_callsOnViewDestroyedOnPresenter() throws Exception {
        // WHEN
        testSubject.onDestroy();

        // THEN
        verify(presenter).onViewDestroyed();
    }

    @Test
    public void onGenerateReportButtonClicked_callsGenerateReportOnPresenter() throws Exception {
        // WHEN
        testSubject.onGenerateReportButtonClicked();

        // THEN
        verify(presenter).generateReport();
   }

    @Test
    public void displayReportData_setsReportTextViewVisibilityToVisible_andSetsText() throws Exception {
        // WHEN
        testSubject.displayReportData(DUMMY_REPORT_DATA);

        // THEN
        verify(displayReportTextView).setVisibility(View.VISIBLE);
        verify(displayReportTextView).setText(DUMMY_REPORT_DATA);
    }

    @Test
    public void displayReportError_displaysToastWithCorrectErrorMessage() throws Exception {
        // GIVEN
        String expected = RuntimeEnvironment.application.getString(R.string.lobby_report_error_text);

        // WHEN
        testSubject.displayReportError();

        // THEN
        assertThat(ShadowToast.getTextOfLatestToast(), is(expected));
    }

    @Test
    public void hideReportData_setsReportTextViewVisibilityToGone() throws Exception {
        // WHEN
        testSubject.hideReportData();

        // THEN
        verify(displayReportTextView).setVisibility(View.GONE);
    }

    @Test
    public void displayLoadingIndicator_setsLoadingIndicatorVisibilityToVisible() throws Exception {
        // WHEN
        testSubject.displayLoadingIndicator();

        // THEN
        verify(loadingIndicator).setVisibility(View.VISIBLE);
    }

    @Test
    public void hideLoadingIndicator_setsLoadingIndicatorVisibilityToGone() throws Exception {
        // WHEN
        testSubject.hideLoadingIndicator();

        // THEN
        verify(loadingIndicator).setVisibility(View.GONE);
    }
}