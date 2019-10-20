package com.razytech.razynet.gui.updateprofile;

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
import com.razytech.razynet.customviews.crop.ImagePickerActivity;
import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.Utils.StaticMethods;
import com.razytech.razynet.Utils.ToastUtil;
import com.razytech.razynet.Utils.dialogutil.DialogUtil;
import com.razytech.razynet.Utils.dialogutil.DialogUtilResponse;
import com.razytech.razynet.Utils.takeimage.TakeImageReceiveView;
import com.razytech.razynet.Utils.takeimage.TakeImageUtiles;
import com.razytech.razynet.baseClasses.BaseFragment;
import com.razytech.razynet.data.beans.AreaResponse;
import com.razytech.razynet.data.beans.CityResponse;
import com.razytech.razynet.data.beans.UserResponse;
import com.razytech.razynet.data.prefs.PrefUtils;
import com.razytech.razynet.databinding.ActivityUpdateProfileFragmentBinding;
import com.razytech.razynet.gui.mainpage.MainpageActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.razytech.razynet.Utils.AppConstant.MALE;
import static com.razytech.razynet.Utils.AppConstant.PROFILE_page;
import static com.razytech.razynet.Utils.AppConstant.REQUEST_PICK_IMAGE;
import static com.razytech.razynet.Utils.StaticMethods.checkCameraPermission;
import static com.razytech.razynet.Utils.takeimage.TakeImageUtiles.CreateFile;
import static com.razytech.razynet.Utils.takeimage.TakeImageUtiles.getImageUri;
import static com.razytech.razynet.Utils.takeimage.TakeImageUtiles.getRealPathFromURI;

public class UpdateProfileFragment extends BaseFragment implements
        UpdateProfileView , TakeImageReceiveView , DialogUtilResponse {


    View view ;
    ActivityUpdateProfileFragmentBinding binding ;
    UpdateProfileModelView  modelView   ;
    MyClickHandlers handlers  ;
    DialogUtil dialogUtil ;
    boolean isFILE = false ,isPhoto = false;
    TakeImageUtiles imageUtiles;
    String image = "";
    File filePath =null;
    byte[] array =null  ;
    List<String>  CitiesNames  ,  AreaNames  ;
    String   Citytxt =  "city" ,  Areatxt =  "area";
    int genderID =  MALE ,cityId  =  -1   ,  areaId = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.activity_update_profile_fragment, container, false);
        //  setUserVisibleHint(false);
        view = binding.getRoot();
        handlers =  new MyClickHandlers(getActivity());
        binding.setHandlers(handlers);

        inilizeVariables();
        return  view;
    }

    private void inilizeVariables() {
        modelView =  new UpdateProfileModelView();
        modelView.attachView(this);
        imageUtiles =  new TakeImageUtiles(this) ;
        dialogUtil =new DialogUtil(this);
        filldata();

    }
    private void filldata() {
        binding.setUser(AppConstant.userResponse);
        StaticMethods.LoadImage(getActivity(), binding.createAccImg,AppConstant.userResponse.getIdImageUrl(),null);

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
        AppConstant.areaResponses = areaResponses;
        AreaNames = new ArrayList<>();
        for (AreaResponse response : areaResponses) {
            AreaNames.add(response.getAreaName());
        }
    }
    @Override
    public void SavingData(UserResponse userResponse) {
        userResponse.setToken(AppConstant.userResponse.getToken());
        AppConstant.userResponse =  userResponse  ;
        PrefUtils.saveUserinformation(getActivity(),userResponse,PrefUtils.User_Singin);
        ((MainpageActivity)getActivity()).displayView(PROFILE_page);
    }

    @Override
    public void selectedValueSingleChoice(int position) {

    }

    @Override
    public void selectedValueSingleChoice(int position, String arrayType) {
        if (arrayType  == Citytxt){
            if (position !=  -1  ) {
                // langposition = position;
                areaId =  -1 ;
                cityId =  AppConstant.cityResponses.get(position).getId()  ;
                binding.txtcity.setText(AppConstant.cityResponses.get(position).getCityName());
                modelView.loadAreasData(getActivity() ,  binding.coorupdateprofile , cityId+""  , AppConstant.userResponse.getToken() );
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
        public void UpdateImage(View view) {
            pickImage();
        }
        public void btn_city(View view) {
            if (CitiesNames != null) {
                 if (CitiesNames.size() != 0)
                dialogUtil.showDialogSingleChooice(getActivity(), R.string.selectcity, R.string.ok, CitiesNames, Citytxt);
            }else
                modelView.loadCitiesData(getActivity() ,  binding.coorupdateprofile, AppConstant.userResponse.getToken());
        }
        public void btn_area(View view) {

            if (cityId != -1) {
                if (AreaNames != null) {
                    if (AreaNames.size() != 0)
                    dialogUtil.showDialogSingleChooice(getActivity(), R.string.selectarea, R.string.ok, AreaNames, Areatxt);
                } else
                    modelView.loadAreasData(getActivity() ,  binding.coorupdateprofile , cityId+"", AppConstant.userResponse.getToken() );
            }else
                ToastUtil.showErrorToast(getActivity() ,  R.string.cityfirst);
        }
        public void btn_UpdateProfile(View view) {
          modelView.vaildatedata(getActivity() , binding.coorupdateprofile ,
                  binding.createAccUsernameET.getText().toString()  , cityId  , areaId  ,filePath);

        }
    }


    public void pickImage() {
        if (checkCameraPermission(getActivity(),getActivity()))
            startActivityForResult(new Intent(getActivity(), ImagePickerActivity.class), REQUEST_PICK_IMAGE);
    }
}
