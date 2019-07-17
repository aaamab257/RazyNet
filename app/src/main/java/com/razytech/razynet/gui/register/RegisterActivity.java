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
import com.razytech.razynet.Utils.ToastUtil;
import com.razytech.razynet.Utils.dialogutil.DialogUtil;
import com.razytech.razynet.Utils.dialogutil.DialogUtilResponse;
import com.razytech.razynet.Utils.takeimage.TakeImageReceiveView;
import com.razytech.razynet.Utils.takeimage.TakeImageUtiles;
import com.razytech.razynet.baseClasses.BaseActivity;
import com.razytech.razynet.data.beans.AreaResponse;
import com.razytech.razynet.data.beans.CityResponse;
import com.razytech.razynet.data.beans.UserResponse;
import com.razytech.razynet.data.prefs.PrefUtils;
import com.razytech.razynet.databinding.ActivityRegisterBinding;
import com.razytech.razynet.gui.mainpage.MainpageActivity;
import com.razytech.razynet.gui.splash.SplashActivity;
import com.razytech.razynet.gui.verificationcode.VerifyCodeActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.razytech.razynet.Utils.AppConstant.MALE;
import static com.razytech.razynet.Utils.AppConstant.REQUEST_PICK_IMAGE;
import static com.razytech.razynet.Utils.StaticMethods.checkCameraPermission;
import static com.razytech.razynet.Utils.takeimage.TakeImageUtiles.CreateFile;
import static com.razytech.razynet.Utils.takeimage.TakeImageUtiles.getImageUri;
import static com.razytech.razynet.Utils.takeimage.TakeImageUtiles.getRealPathFromURI;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding,  RegisterModelView>
        implements RegisterView, TakeImageReceiveView , DialogUtilResponse {

    ActivityRegisterBinding binding ;
    @Inject RegisterModelView modelView  ;
    MyClickHandlers handlers  ;
    Uri imageUri;
    boolean isFILE = false ,isPhoto = false;
    TakeImageUtiles imageUtiles;
    File filePath =null;
    byte[] array =null  ;
    int genderID =  MALE ,cityId  =  -1   ,  areaId = -1;
    DialogUtil dialogUtil ;
    List<String>  CitiesNames  ,  AreaNames  ;
    String   Citytxt =  "city" ,  Areatxt =  "area" ,image = "" ,  phone = "" ,  token =  "";

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
        dialogUtil =new DialogUtil(this);
        modelView.loadCitiesData(RegisterActivity.this ,  binding.coorregister, token);
        if (getIntent().hasExtra(AppConstant.TokenKey)) {
            token = getIntent().getExtras().getString(AppConstant.TokenKey);
            phone = getIntent().getExtras().getString(AppConstant.phoneKey);
        }
        binding.createAccPhoneET.setText(phone);
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
    public void LoadCitydata(List<CityResponse> cityResponses) {
        AppConstant.cityResponses =  cityResponses ;
        CitiesNames =  new ArrayList<>() ;
        for (CityResponse response : cityResponses ){
            CitiesNames.add(response.getCityName());
        }
    }

    @Override
    public void LoadAreadata(List<AreaResponse> areaResponses) {
        AppConstant.areaResponses =  areaResponses ;
        AreaNames =  new ArrayList<>() ;
        for (AreaResponse response : areaResponses ){
            AreaNames.add(response.getAreaName());
        }
    }

    @Override
    public void SavingData(UserResponse userResponse) {
        userResponse.setToken(token);
        userResponse.setPhone(phone);
        AppConstant.userResponse =  userResponse  ;
        PrefUtils.saveUserinformation(RegisterActivity.this,userResponse,PrefUtils.User_Singin);
        OpenMainPage();
    }

    @Override
    public void AftergettingImage(Bitmap bitmap, byte[] array, String fileName, File FilePath) {
        if(isFILE) {
            String completePath = Environment.getExternalStorageDirectory() + "/" + fileName;
            File file = new File(completePath);
            Uri imageUri = Uri.fromFile(file);
            Glide.with(this)
                    .load(imageUri)
                    .into(binding.createAccImg);
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
                binding.createAccImg.setImageBitmap(photo);
                Uri tempUri = getImageUri(getApplicationContext(), photo);
                File finalFile = new File(getRealPathFromURI(RegisterActivity.this,tempUri));
               // requestPhoto =photo ;
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
               // requestPhoto =photo ;
                filePath =finalFile;
                imageUtiles.onCaptureImageResult(data,this,RegisterActivity.this);
            }
        }
    }

    @Override
    public void selectedValueSingleChoice(int position) {

    }

    @Override
    public void selectedValueSingleChoice(int position, String arrayType) {
        if (arrayType  == Citytxt){
            if (position !=  -1  ) {
                // langposition = position;
                cityId =  AppConstant.cityResponses.get(position).getId()  ;
                binding.txtcity.setText(AppConstant.cityResponses.get(position).getCityName());
                modelView.loadAreasData(RegisterActivity.this ,  binding.coorregister , cityId+""  , token );
                binding.txtarea.setText(getString(R.string.area));
                /// PrefUtils.saveLanguage(WelcomeLangActivity.this, AppConstant.languageResponses, AppConstant.languageResponses.get(position).Key);
            }
        }
        else  if (arrayType  == Areatxt){
            if (position !=  -1  ) {
                areaId =  AppConstant.areaResponses.get(position).getId()  ;
                binding.txtarea.setText(AppConstant.areaResponses.get(position).getAreaName());
            }
        }
    }


    public class MyClickHandlers {
        Context context;
        public MyClickHandlers(Context context) {
            this.context = context;
        }

        public void btn_city(View view) {
            if (AppConstant.cityResponses != null)
                dialogUtil.showDialogSingleChooice(RegisterActivity.this, R.string.selectcity, R.string.ok,CitiesNames, Citytxt);
            else
                modelView.loadCitiesData(RegisterActivity.this ,  binding.coorregister, token);
        }
        public void btn_area(View view) {

            if (cityId != -1) {
                if (AppConstant.areaResponses != null)
                    dialogUtil.showDialogSingleChooice(RegisterActivity.this, R.string.selectarea, R.string.ok,AreaNames, Areatxt);
                else
                    modelView.loadAreasData(RegisterActivity.this ,  binding.coorregister , cityId+"", token );
            }else
                ToastUtil.showErrorToast(RegisterActivity.this ,  R.string.cityfirst);
        }


        public void btnEnterCode(View view) {
          modelView.vaildatedata(RegisterActivity.this , binding.coorregister ,
                  binding.createAccUsernameET.getText().toString()    , binding.createAccNidET.getText().toString()  ,
                  binding.createAccPasswordET.getText().toString()
                  , binding.createAccConfpasswordET.getText().toString()  ,cityId ,  areaId ,filePath ,  token);

           //  OpenMainPage();
        }
        public void ImgNidImage(View view) {
            pickImage();
        }
    }
    public void pickImage() {
        if (checkCameraPermission(RegisterActivity.this ,RegisterActivity.this))
            startActivityForResult(new Intent(this, ImagePickerActivity.class), REQUEST_PICK_IMAGE);
    }
}
