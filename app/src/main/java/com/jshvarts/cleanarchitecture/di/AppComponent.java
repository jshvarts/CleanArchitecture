package com.jshvarts.cleanarchitecture.di;

import com.jshvarts.cleanarchitecture.CleanArchitectureApplication;
import com.jshvarts.cleanarchitecture.lobby.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dagger App Component.
 */
@Singleton
@Component(modules={AppModule.class})
public interface AppComponent {
    void inject(MainActivity activity);

    final class Initializer {
        private Initializer() {
        }

        public static AppComponent init(CleanArchitectureApplication app) {
            return DaggerAppComponent.builder()
                    .appModule(new AppModule(app))
                    .build();
        }
    }
}
