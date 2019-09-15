package com.razytech.razynet.gui.forgetpassword;

import dagger.Module;
import dagger.Provides;

/**
 * Created by A.Noby on 9/9/2019.
 */
@Module
public class ForgetPasswordActivityModule {
    @Provides
    ForgetPasswordModelView provideForgetPasswordModelView() {
        return new ForgetPasswordModelView();
    }
}
