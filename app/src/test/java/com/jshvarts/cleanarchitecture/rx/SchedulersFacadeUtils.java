package com.jshvarts.cleanarchitecture.rx;

import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.when;

public class SchedulersFacadeUtils {
    private SchedulersFacadeUtils() {
    }

    public static TestScheduler setupSchedulersFacade(SchedulersFacade schedulersFacade) {
        TestScheduler testScheduler = new TestScheduler();
        when(schedulersFacade.computation()).thenReturn(testScheduler);
        when(schedulersFacade.ui()).thenReturn(testScheduler);
        when(schedulersFacade.io()).thenReturn(testScheduler);
        return testScheduler;
    }
}