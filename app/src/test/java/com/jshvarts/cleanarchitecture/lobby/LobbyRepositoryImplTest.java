package com.jshvarts.cleanarchitecture.lobby;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link LobbyRepositoryImpl}.
 */
public class LobbyRepositoryImplTest {

    LobbyRepositoryImpl testSubject;

    @Before
    public void setUp() throws Exception {
        testSubject = spy(new LobbyRepositoryImpl());
    }

    @Test
    public void getReportAsync_onSuccess_returnsReportData() throws Exception {
        // GIVEN
        when(testSubject.getReportAsync())
                .thenReturn(Single.just(LobbyRepositoryImpl.FAKE_REPORT_DATA));

        // WHEN
        TestObserver<String> testObserver = testSubject.getReportAsync().test();

        // THEN
        testObserver.assertResult(LobbyRepositoryImpl.FAKE_REPORT_DATA);
        testObserver.assertComplete();
    }

    @Test
    public void getReportAsync_onError_returnsError() throws Exception {
        // GIVEN
        when(testSubject.getReportAsync())
                .thenReturn(Single.error(new RuntimeException()));

        // WHEN
        TestObserver<String> testObserver = testSubject.getReportAsync().test();

        // THEN
        testObserver.assertError(RuntimeException.class);
    }

    @Test
    public void getReport_returnsReportData() throws Exception {
        // WHEN
        String result = testSubject.getReport();

        // THEN
        assertThat(result, is(LobbyRepositoryImpl.FAKE_REPORT_DATA));
    }
}