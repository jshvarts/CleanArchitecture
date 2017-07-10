package com.jshvarts.cleanarchitecture.di;

import android.content.Context;

import com.jshvarts.cleanarchitecture.App;
import com.jshvarts.cleanarchitecture.lobby.LobbyRepository;
import com.jshvarts.cleanarchitecture.lobby.LobbyRepositoryImpl;
import com.jshvarts.cleanarchitecture.lobby.LobbySubComponent;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger Module
 * https://android.jlelse.eu/android-and-dagger-2-10-androidinjector-5e9c523679a3
 */
@Module(subcomponents = { LobbySubComponent.class })
public class AppModule {
    @Provides
    Context provideContext(App application) {
        return application.getApplicationContext();
    }

    @Provides
    LobbyRepository provideLobbyRepository() {
        return new LobbyRepositoryImpl();
    }
}

