package com.razytech.razynet.gui.notifications;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.razytech.razynet.R;
import com.razytech.razynet.baseClasses.BaseFragment;
import com.razytech.razynet.databinding.ActivityNotificationFragmentBinding;
import com.razytech.razynet.databinding.ActivityTreeFragmentBinding;
import com.razytech.razynet.gui.maintransaction.MainTransactionFragment;

public class NotificationsFragment extends BaseFragment implements NotificationsView {


    View view ;
    ActivityNotificationFragmentBinding binding ;
    MyClickHandlers handlers  ;
    NotificationsModelView  modelView  ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.activity_notification_fragment, container, false);
        //  setUserVisibleHint(false);
        view = binding.getRoot();
        handlers =  new MyClickHandlers(getActivity());
        binding.setHandlers(handlers);
        inilizeVariables();
        return  view;
    }

    private void inilizeVariables() {
        modelView =  new NotificationsModelView();
        modelView.attachView(this);
    }

    public class MyClickHandlers {
        Context context;
        public MyClickHandlers(Context context) {
            this.context = context;
        }
        public void Radiomale(View view) {
        }
    }
}
