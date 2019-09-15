package com.razytech.razynet.gui.treepage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.baseClasses.BaseFragment;
import com.razytech.razynet.databinding.ActivityTreeFragmentBinding;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
        Url="http://81.29.101.110:5201/tree/collapsable/index.html";
        binding.webview.getSettings().setDomStorageEnabled(true);
        binding.webview.getSettings().setJavaScriptEnabled(true);
        binding.webview.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        binding.webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
      //  binding.webview.setWebViewClient(new AppWebViewClients());
        binding.webview.getSettings().setSupportZoom(true);
        binding.webview.getSettings().setBuiltInZoomControls(true);
        binding.webview.setVerticalScrollBarEnabled(true);
        binding.webview.setHorizontalScrollBarEnabled(true);
        binding.webview.addJavascriptInterface(new JavaScriptInterface(getActivity()), "Android");
        binding.webview.loadUrl(Url);

}
class JavaScriptInterface {
    Context mContext;
    JavaScriptInterface(Context c) {
        mContext = c;
    }
    @JavascriptInterface
    public String getFromAndroid() {
     return  AppConstant.userResponse.getToken();
    }
}




    public class AppWebViewClients extends WebViewClient {
        public AppWebViewClients() {
            showloadingviewBase(getActivity());
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

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
//   http://81.29.101.110:5201/trees/tree?id= showloadingviewBase(getActivity());  http://www.imakaseb.com/tree/    + "?token="+ AppConstant.userResponse.getToken()
//        StringBuffer buffer=new StringBuffer("http://81.29.101.110:5201/tree/collapsable/index.html");
//        buffer.append("?token="+URLEncoder.encode(AppConstant.userResponse.getToken()));
//buffer.append("&id="+URLEncoder.encode("xxxxxxxx"));
//buffer.append("act="+URLEncoder.encode("readFileAndPrint"));
// webView.loadUrl(buffer.toString());
//  Log.e("TreeUrl",buffer.toString());
//        binding.webview.getSettings().setLoadsImagesAutomatically(true);
//    binding.webview.getSettings().setJavaScriptEnabled(true);
//       binding.webview.getSettings().setSupportZoom(true);
//        WebSettings settings = binding.webview.getSettings();
//        settings.setJavaScriptEnabled(true);
//        settings.setDomStorageEnabled(true);