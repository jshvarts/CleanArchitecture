package com.jshvarts.cleanarchitecture.lobby;

/**
 * Defines possible view interactions with lobby
 */
interface LobbyView {
    void onGenerateReportButtonClicked();
    void displayReportData(CharSequence report);
    void hideReportData();
    void displayReportError();
    void displayLoadingIndicator();
    void hideLoadingIndicator();
}
