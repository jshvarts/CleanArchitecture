package com.jshvarts.cleanarchitecture.di;

import android.app.Activity;

import com.jshvarts.cleanarchitecture.lobby.LobbyActivity;
import com.jshvarts.cleanarchitecture.lobby.LobbySubComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Binds all sub-components within the app.
 * https://android.jlelse.eu/android-and-dagger-2-10-androidinjector-5e9c523679a3
 */
@Module
public abstract class BuildersModule {
    @Binds
    @IntoMap
    @ActivityKey(LobbyActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindFeatureActivityInjectorFactory(LobbySubComponent.Builder builder);

    // Add more bindings here for other sub components
}
