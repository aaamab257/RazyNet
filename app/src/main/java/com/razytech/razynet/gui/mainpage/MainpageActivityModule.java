package com.razytech.razynet.gui.mainpage;

import dagger.Module;
import dagger.Provides;

/**
 * Created by A.Noby on 6/9/2019.
 */
@Module
public class MainpageActivityModule {
    @Provides
    MainpageModelView provideMainpageModelView() {
        return new MainpageModelView();
    }
}
