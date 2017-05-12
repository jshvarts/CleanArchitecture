package com.jshvarts.cleanarchitecture.lobby;

/**
 * Defines possible view interactions with lobby
 */
interface LobbyView {
    void onGenerateReportButtonClicked();
    void onDisplayReportData(CharSequence report);
    void onDisplayReportError();
}
