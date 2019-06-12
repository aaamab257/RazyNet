package com.razytech.razynet.baseClasses;

import android.arch.lifecycle.ViewModel;

/**
 * Created by A.Noby on 4/7/2019.
 */
public abstract class BaseViewModel<V extends BaseView>  extends ViewModel {

    public V view;

    public void attachView(V view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
    }

    public boolean isViewDetached() {
        return view == null;
    }

    public boolean isViewAttached() {
        return view != null;
    }

}