package com.razytech.razynet.gui.remainingpage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.razytech.razynet.customviews.crop.ImagePickerActivity;
import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.Utils.IntentUtiles;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.Utils.takeimage.TakeImageReceiveView;
import com.razytech.razynet.Utils.takeimage.TakeImageUtiles;
import com.razytech.razynet.baseClasses.BaseActivity;
import com.razytech.razynet.data.beans.RemainingResponse;
import com.razytech.razynet.data.prefs.PrefUtils;
import com.razytech.razynet.databinding.ActivityRemainingBinding;
import com.razytech.razynet.gui.loginpage.LoginActivity;
import com.razytech.razynet.gui.mainpage.MainpageActivity;

import java.io.File;

import javax.inject.Inject;

import static com.razytech.razynet.Utils.AppConstant.REQUEST_PICK_IMAGE;
import static com.razytech.razynet.Utils.StaticMethods.checkCameraPermission;
import static com.razytech.razynet.Utils.takeimage.TakeImageUtiles.CreateFile;
import static com.razytech.razynet.Utils.takeimage.TakeImageUtiles.getImageUri;
import static com.razytech.razynet.Utils.takeimage.TakeImageUtiles.getRealPathFromURI;

public class RemainingActivity extends BaseActivity<ActivityRemainingBinding
        ,  RemainingModelView> implements  RemainingView  , TakeImageReceiveView {

    ActivityRemainingBinding binding  ;
   @Inject RemainingModelView modelView ;
    MyClickHandlers handlers ;
    boolean isFILE = false ,isPhoto = false;
    TakeImageUtiles imageUtiles;
    File filePath =null;
    byte[] array =null  ;
    String image  =  ""  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideKeyboard();

        binding = getViewDataBinding();
        handlers =  new MyClickHandlers(this);
        binding.setHandlers(handlers);
        inilizeVaribles();
    }
    private void inilizeVaribles() {
        modelView.attachView(this);
        modelView.loadingRemainingData(binding.coorremain,  RemainingActivity.this);
        show_errorView(false ,"" );
        imageUtiles =  new TakeImageUtiles(this) ;

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

    @Override
    public void SetRemainingData(RemainingResponse remainingResponse ,  String Message) {
        binding.setData(remainingResponse);
        show_errorView(false ,"" );
        binding.setIswait(remainingResponse.isWaiting());
        binding.setWaittxt(Message);
    }

    @Override
    public void show_errorView(boolean Isshow, String error) {
        binding.setIserror(Isshow);
        binding.errorLayoutView.setViewerror(Isshow);
        if (Isshow){
            binding.errorLayoutView.setErrortxt(error);
            binding.errorLayoutView.btnTryAgain.setOnClickListener((View)->{
                modelView.loadingRemainingData(binding.coorremain,  RemainingActivity.this);
            });
        }
    }

    @Override
    public void UpdateUserStatus(String messsage) {
        binding.setIswait(true);
        binding.setWaittxt(messsage);

    }

    @Override
    public void logout() {
        StaticMethods.ClearChash();
        PrefUtils.SignOut_User(RemainingActivity.this);
        AppConstant.userResponse = null ;
        IntentUtiles.openActivityInNewStack(RemainingActivity.this, LoginActivity.class);    }

    @Override
    public void AftergettingImage(Bitmap bitmap, byte[] array, String fileName, File FilePath) {
        if(isFILE) {
            String completePath = Environment.getExternalStorageDirectory() + "/" + fileName;
            File file = new File(completePath);
            Uri imageUri = Uri.fromFile(file);
            Glide.with(this)
                    .load(imageUri)
                    .into(binding.imgnid);
            //  requestPhoto = bitmap;
        }
        image = fileName;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == REQUEST_PICK_IMAGE) {
                String imagePath = data.getStringExtra("image_path");
                isFILE = false;
                Bitmap photo =  imageUtiles.getImageFromStorage(imagePath);
                binding.imgnid.setImageBitmap(photo);
                Uri tempUri = getImageUri(getApplicationContext(), photo);
                File finalFile = new File(getRealPathFromURI(RemainingActivity.this,tempUri));
                // requestPhoto =photo ;
                filePath =finalFile;
                imageUtiles.onCaptureImageResult(photo,this,RemainingActivity.this);
            }else  if (requestCode == AppConstant.SELECT_FILE) {
                isFILE = true;
                imageUtiles.onCaptureImageResult(data,RemainingActivity.this,RemainingActivity.this);
                filePath =    CreateFile(RemainingActivity.this,data);
            } else if (requestCode == AppConstant.REQUEST_CAMERA) {
                isFILE = false;
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                binding.imgnid.setImageBitmap(photo);
                Uri tempUri = getImageUri(getApplicationContext(), photo);
                File finalFile = new File(getRealPathFromURI(RemainingActivity.this,tempUri));
                // requestPhoto =photo ;
                filePath =finalFile;
                imageUtiles.onCaptureImageResult(data,this,RemainingActivity.this);
            }
        }
    }

    public class MyClickHandlers {
        Context context;
        public MyClickHandlers(Context context) {
            this.context = context;
        }
        public void btnUpload(View view) {
            modelView.ValidateSendData(RemainingActivity.this , binding.coorremain ,
                    binding.createNIDET.getText().toString()  ,filePath );
        }
        public void uploadImage(View view) {
            pickImage() ;
        }
        public void btnSkip(View view) {
            IntentUtiles.openActivityInNewStack(RemainingActivity.this, MainpageActivity.class);
        }
    }

    public void pickImage() {
        if (checkCameraPermission(RemainingActivity.this ,RemainingActivity.this))
            startActivityForResult(new Intent(this, ImagePickerActivity.class), REQUEST_PICK_IMAGE);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

    }
}
