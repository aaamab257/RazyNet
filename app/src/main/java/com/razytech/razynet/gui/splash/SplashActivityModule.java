package com.razytech.razynet.gui.splash;

import dagger.Module;
import dagger.Provides;

/**
 * Created by A.Noby on 4/7/2019.
 */
@Module
public class SplashActivityModule {
    @Provides
    SplashModelView provideSplashViewModel() {
        return new SplashModelView();
    }
}
