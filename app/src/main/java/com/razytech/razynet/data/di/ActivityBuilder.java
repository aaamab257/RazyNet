package com.razytech.razynet.data.di;

import com.razytech.razynet.gui.mainpage.MainpageActivity;
import com.razytech.razynet.gui.mainpage.MainpageActivityModule;
import com.razytech.razynet.gui.register.RegisterActivity;
import com.razytech.razynet.gui.register.RegisterActivityModule;
import com.razytech.razynet.gui.remainingpage.RemainingActivity;
import com.razytech.razynet.gui.remainingpage.RemainingActivityModule;
import com.razytech.razynet.gui.splash.SplashActivity;
import com.razytech.razynet.gui.splash.SplashActivityModule;
import com.razytech.razynet.gui.verificationcode.VerifyCodeActivity;
import com.razytech.razynet.gui.verificationcode.VerifyCodeActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by A.Noby on 4/7/2019.
 */

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector(modules = VerifyCodeActivityModule.class)
    abstract VerifyCodeActivity bindVerifyCodeActivity();

    @ContributesAndroidInjector(modules = RegisterActivityModule.class)
    abstract RegisterActivity bindRegisterActivity();

    @ContributesAndroidInjector(modules = MainpageActivityModule.class)
    abstract MainpageActivity bindMainpageActivity();

    @ContributesAndroidInjector(modules = RemainingActivityModule.class)
    abstract RemainingActivity bindRemainingActivity();
}