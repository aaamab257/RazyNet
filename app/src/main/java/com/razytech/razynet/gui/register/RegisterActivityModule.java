package com.razytech.razynet.gui.register;


import dagger.Module;
import dagger.Provides;

/**
 * Created by A.Noby on 6/9/2019.
 */

@Module
public class RegisterActivityModule {

    @Provides
    RegisterModelView provideRegisterModelView() {
        return new RegisterModelView();
    }
}
