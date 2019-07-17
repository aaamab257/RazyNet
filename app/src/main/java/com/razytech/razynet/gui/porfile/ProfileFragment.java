package com.razytech.razynet.gui.porfile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.razytech.razynet.CustomViews.crop.ImagePickerActivity;
import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.Utils.takeimage.TakeImageReceiveView;
import com.razytech.razynet.Utils.takeimage.TakeImageUtiles;
import com.razytech.razynet.baseClasses.BaseFragment;
import com.razytech.razynet.databinding.ActivityProfileFragmentBinding;
import com.razytech.razynet.gui.mainpage.MainpageActivity;
import com.razytech.razynet.gui.maintransaction.redeem.RedeemListFragment;
import com.razytech.razynet.gui.register.RegisterActivity;

import java.io.File;

import static com.razytech.razynet.Utils.AppConstant.REQUEST_PICK_IMAGE;
import static com.razytech.razynet.Utils.StaticMethods.checkCameraPermission;
import static com.razytech.razynet.Utils.takeimage.TakeImageUtiles.CreateFile;
import static com.razytech.razynet.Utils.takeimage.TakeImageUtiles.getImageUri;
import static com.razytech.razynet.Utils.takeimage.TakeImageUtiles.getRealPathFromURI;

public class ProfileFragment extends BaseFragment implements   ProfileView , TakeImageReceiveView {

    View view ;
    ActivityProfileFragmentBinding binding ;
    ProfileModelView modelView   ;
    MyClickHandlers handlers  ;
    boolean isFILE = false ,isPhoto = false;
    TakeImageUtiles imageUtiles;
    String image = "";
    File filePath =null;
    byte[] array =null  ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.activity_profile_fragment, container, false);
        view = binding.getRoot();
        handlers =  new MyClickHandlers(getActivity());
        binding.setHandlers(handlers);

        inilizeVariables();
        return  view;
    }

    private void inilizeVariables() {
        ((MainpageActivity)getActivity()).setViewHandling(""  ,""  , true , false );
        modelView =  new ProfileModelView();
        modelView.attachView(this);
        imageUtiles =  new TakeImageUtiles(this) ;
        filldata();
    }

    private void filldata() {

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
                Uri tempUri = getImageUri(getActivity(), photo);
                File finalFile = new File(getRealPathFromURI(getActivity(),tempUri));
                // requestPhoto =photo ;
                filePath =finalFile;
                imageUtiles.onCaptureImageResult(photo,getActivity(),getActivity());
            }else  if (requestCode == AppConstant.SELECT_FILE) {
                isFILE = true;
                imageUtiles.onCaptureImageResult(data,getActivity(),getActivity());
                filePath =    CreateFile(getActivity(),data);
            } else if (requestCode == AppConstant.REQUEST_CAMERA) {
                isFILE = false;
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                binding.createAccImg.setImageBitmap(photo);
                Uri tempUri = getImageUri(getActivity(), photo);
                File finalFile = new File(getRealPathFromURI(getActivity(),tempUri));
                // requestPhoto =photo ;
                filePath =finalFile;
                imageUtiles.onCaptureImageResult(data,getActivity(),getActivity());
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
        public void btn_UpdateProfile(View view) {
//          modelView.vaildatedata(RegisterActivity.this , binding.coorregister , binding.createAccUsernameET.getText().toString()  , binding.createAccPhoneET.getText().toString()  , binding.createAccNidET.getText().toString()  ,
//                  binding.createAccPasswordET.getText().toString()  , binding.createAccConfpasswordET.getText().toString()  ,genderID+"",filePath,array);

        }

        public void btn_logout(View view) {
        }

    }
    public void pickImage() {
        if (checkCameraPermission(getActivity(),getActivity()))
            startActivityForResult(new Intent(getActivity(), ImagePickerActivity.class), REQUEST_PICK_IMAGE);
    }
}
