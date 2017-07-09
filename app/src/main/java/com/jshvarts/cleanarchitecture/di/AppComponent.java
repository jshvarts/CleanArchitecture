package com.jshvarts.cleanarchitecture.di;

import com.jshvarts.cleanarchitecture.App;
import com.jshvarts.cleanarchitecture.lobby.LobbyComponent;
import com.jshvarts.cleanarchitecture.lobby.LobbyModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Dagger App Component.
 */
@Singleton
@Component(modules={AppModule.class})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance Builder application(App application);
        AppComponent build();
    }
    LobbyComponent plus(LobbyModule module);
}
