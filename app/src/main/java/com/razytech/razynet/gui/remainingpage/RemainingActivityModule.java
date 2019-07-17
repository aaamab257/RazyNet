package com.razytech.razynet.gui.remainingpage;





import dagger.Module;
import dagger.Provides;

/**
 * Created by A.Noby on 6/19/2019.
 */
@Module
public class RemainingActivityModule {
    @Provides
    RemainingModelView  provideRemainingModelView() {
        return new RemainingModelView();
    }
}
