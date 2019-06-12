package com.razytech.razynet.gui.register;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.razytech.razynet.CustomViews.crop.ImagePickerActivity;
import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.Utils.IntentUtiles;
import com.razytech.razynet.Utils.takeimage.TakeImageReceiveView;
import com.razytech.razynet.Utils.takeimage.TakeImageUtiles;
import com.razytech.razynet.baseClasses.BaseActivity;
import com.razytech.razynet.databinding.ActivityRegisterBinding;
import com.razytech.razynet.gui.mainpage.MainpageActivity;
import com.razytech.razynet.gui.splash.SplashActivity;
import com.razytech.razynet.gui.verificationcode.VerifyCodeActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.inject.Inject;

import static com.razytech.razynet.Utils.AppConstant.MALE;
import static com.razytech.razynet.Utils.StaticMethods.checkCameraPermission;
import static com.razytech.razynet.Utils.takeimage.TakeImageUtiles.CreateFile;
import static com.razytech.razynet.Utils.takeimage.TakeImageUtiles.getImageUri;
import static com.razytech.razynet.Utils.takeimage.TakeImageUtiles.getRealPathFromURI;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding,  RegisterModelView>
        implements RegisterView, TakeImageReceiveView {

    ActivityRegisterBinding binding ;
 @Inject
 RegisterModelView modelView  ;
    MyClickHandlers handlers  ;
    Uri imageUri;
    boolean isFILE = false ,isPhoto = false;
    Bitmap requestPhoto;
    TakeImageUtiles imageUtiles;
    String image = "";
    File filePath =null;
    byte[] array =null  ;
    private static final int REQUEST_PICK_IMAGE = 1002;
    int genderID =  MALE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideKeyboard();
        binding   = getViewDataBinding();
        handlers =  new MyClickHandlers(this);
        binding.setHandlers(handlers);
        inilizeVaribles();
    }

    private void inilizeVaribles() {
        modelView.attachView(this);
        imageUtiles =  new TakeImageUtiles(this) ;
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }
    @Override
    public RegisterModelView getViewModel() {
        return modelView;
    }
    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public void OpenMainPage() {
        IntentUtiles.openActivityInNewStack(RegisterActivity.this, MainpageActivity.class);
    }

    @Override
    public void AftergettingImage(Bitmap bitmap, byte[] array, String fileName, File FilePath) {
        Log.e("fileImage",FilePath.getName() +"  "+fileName);
        if(isFILE) {
            String completePath = Environment.getExternalStorageDirectory() + "/" + fileName;
            File file = new File(completePath);
            Uri imageUri = Uri.fromFile(file);
            Glide.with(this)
                    .load(imageUri)
                    .into(binding.createAccImg);
            requestPhoto = bitmap;
        }
        image = fileName;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == REQUEST_PICK_IMAGE) {
                //   String imagePath = data.getStringExtra("image_path");
                // setImage(imagePath);
                String imagePath = data.getStringExtra("image_path");
                isFILE = false;
                Bitmap photo =  imageUtiles.getImageFromStorage(imagePath);
                binding.createAccImg.setImageBitmap(photo);
                Uri tempUri = getImageUri(getApplicationContext(), photo);
                File finalFile = new File(getRealPathFromURI(RegisterActivity.this,tempUri));
                requestPhoto =photo ;
                filePath =finalFile;
                imageUtiles.onCaptureImageResult(photo,this,RegisterActivity.this);
            }else  if (requestCode == AppConstant.SELECT_FILE) {
                isFILE = true;
                imageUtiles.onCaptureImageResult(data,RegisterActivity.this,RegisterActivity.this);
                filePath =    CreateFile(RegisterActivity.this,data);
            } else if (requestCode == AppConstant.REQUEST_CAMERA) {
                isFILE = false;
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                binding.createAccImg.setImageBitmap(photo);
                Uri tempUri = getImageUri(getApplicationContext(), photo);
                File finalFile = new File(getRealPathFromURI(RegisterActivity.this,tempUri));
                requestPhoto =photo ;
                filePath =finalFile;
                imageUtiles.onCaptureImageResult(data,this,RegisterActivity.this);
            }
        }
    }




    public class MyClickHandlers {
        Context context;
        public MyClickHandlers(Context context) {
            this.context = context;
        }

        public void btn_city(View view) {
        }
        public void btn_area(View view) {
        }
        public void btnEnterCode(View view) {
//          modelView.vaildatedata(RegisterActivity.this , binding.coorregister , binding.createAccUsernameET.getText().toString()  , binding.createAccPhoneET.getText().toString()  , binding.createAccNidET.getText().toString()  ,
//                  binding.createAccPasswordET.getText().toString()  , binding.createAccConfpasswordET.getText().toString()  ,genderID+"",filePath,array);
             OpenMainPage();
        }
        public void ImgNidImage(View view) {
            //  imageUtiles.selectImage(UpdateProfileActivity.this,UpdateProfileActivity.this);
            pickImage();
        }
    }
    public void pickImage() {
        if (checkCameraPermission(RegisterActivity.this ,RegisterActivity.this))
            startActivityForResult(new Intent(this, ImagePickerActivity.class), REQUEST_PICK_IMAGE);
    }

}
