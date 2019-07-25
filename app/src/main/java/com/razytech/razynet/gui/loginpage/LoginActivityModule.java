package com.razytech.razynet.gui.loginpage;



import dagger.Module;
import dagger.Provides;

/**
 * Created by A.Noby on 7/22/2019.
 */
@Module
public class LoginActivityModule {
    @Provides
    LoginModelView provideLoginModelView() {
        return new LoginModelView();
    }
}
