package com.razytech.razynet.customviews.views;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by A.Noby on 10/17/2019.
 */
public class StarWalletVisibility  extends BaseObservable {



        private boolean isToggleOn;

     public StarWalletVisibility() {
            isToggleOn = false;
        }

        public void toggleVisibility(boolean toggleOn) {
            isToggleOn = toggleOn;
            notifyPropertyChanged(BR.toggleOn);
        }

        @Bindable
        public boolean getToggleOn() {
            return isToggleOn;
        }
}
