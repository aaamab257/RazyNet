package com.razytech.razynet.gui.verificationcode;

import dagger.Module;
import dagger.Provides;

/**
 * Created by A.Noby on 4/7/2019.
 */
@Module
public class VerifyCodeActivityModule {
    @Provides
    VerifyCodeViewModel provideVerifyCodeViewModel() {
        return new VerifyCodeViewModel();
    }
}
