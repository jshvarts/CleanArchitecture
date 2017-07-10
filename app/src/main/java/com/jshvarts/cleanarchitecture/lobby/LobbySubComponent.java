package com.jshvarts.cleanarchitecture.lobby;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Lobby-specific Dagger sub-component.
 * https://android.jlelse.eu/android-and-dagger-2-10-androidinjector-5e9c523679a3
 */
@Subcomponent
public interface LobbySubComponent extends AndroidInjector<LobbyActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<LobbyActivity> {
    }
}
