package com.razytech.razynet.gui.remainingpage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.razytech.razynet.R;
import com.razytech.razynet.baseClasses.BaseActivity;
import com.razytech.razynet.databinding.ActivityRemainingBinding;
import com.razytech.razynet.gui.verificationcode.VerifyCodeActivity;

import javax.inject.Inject;

public class RemainingActivity extends BaseActivity<ActivityRemainingBinding  ,  RemainingModelView> implements  RemainingView {

    ActivityRemainingBinding binding  ;
   @Inject RemainingModelView modelView ;
    MyClickHandlers handlers ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remaining);
        binding = getViewDataBinding();
        handlers =  new MyClickHandlers(this);
        binding.setHandlers(handlers);
        inilizeVaribles();
    }
    private void inilizeVaribles() {
        modelView.attachView(this);
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_remaining;
    }
    @Override
    public RemainingModelView getViewModel() {
        return modelView;
    }
    @Override
    public int getBindingVariable() {
        return 0;
    }
    public class MyClickHandlers {
        Context context;
        public MyClickHandlers(Context context) {
            this.context = context;
        }
        public void btnEnter(View view) {
        }
        public void btnback(View view) {
        }
        public void uploadImage(View view) {
        }
        public void btnSkip(View view) {
        }

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

    }
}
