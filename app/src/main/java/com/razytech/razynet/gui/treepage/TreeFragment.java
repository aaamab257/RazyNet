package com.razytech.razynet.gui.treepage;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.razytech.razynet.R;
import com.razytech.razynet.baseClasses.BaseFragment;
import com.razytech.razynet.databinding.ActivityTreeFragmentBinding;

public class TreeFragment extends BaseFragment  implements TreeView {



    View view ;
    ActivityTreeFragmentBinding binding ;
    TreeModelView modelView   ;
    String Url =  "" ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.activity_tree_fragment, container, false);
        //  setUserVisibleHint(false);
        view = binding.getRoot();
        inilizeVariables();
        return  view;
    }

    private void inilizeVariables() {
        modelView =  new TreeModelView();
        modelView.attachView(this);
        LoadingWebView();
    }

    private void LoadingWebView() {
      //  showloadingviewBase(getActivity());
        Url="http://www.imakaseb.com/tree/";
        binding.webview.getSettings().setLoadsImagesAutomatically(true);
        binding.webview.getSettings().setJavaScriptEnabled(true);
        binding.webview.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        binding.webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        binding.webview.setWebViewClient(new AppWebViewClients());
        binding.webview.setVerticalScrollBarEnabled(true);
        binding.webview.setHorizontalScrollBarEnabled(true);
        binding.webview.loadUrl(Url);


       // browser.loadData(Message, "text/html; charset=UTF-8", null);
    }

    public class AppWebViewClients extends WebViewClient {
        public AppWebViewClients() {
            showloadingviewBase(getActivity());
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
          hideloadingviewBase();
        }
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
