package com.jshvarts.cleanarchitecture.lobby;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * Unit tests for {@link LobbyReportUseCase}.
 */
public class LobbyReportUseCaseTest {

    @InjectMocks
    LobbyReportUseCase testSubject;

    @Mock
    LobbyRepository lobbyRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void generateReport_getsReportAsyncFromRepository() throws Exception {
        // WHEN
        testSubject.generateReport();

        // THEN
        verify(lobbyRepository).getReportAsync();
    }

    @Test
    public void getLocalReport_getsReportFromRepository() throws Exception {
        // WHEN
        testSubject.getLocalReport();

        // THEN
        verify(lobbyRepository).getReport();
    }
}