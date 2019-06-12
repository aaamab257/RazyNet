package com.razytech.razynet.data.di;

import android.app.Application;

import com.razytech.razynet.app.RazyNetApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by A.Noby on 4/7/2019.
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class, ActivityBuilder.class})
public interface AppComponent {

    void inject(RazyNetApp app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}

