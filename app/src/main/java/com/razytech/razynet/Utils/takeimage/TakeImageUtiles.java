package com.razytech.razynet.Utils.takeimage;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;


import com.razytech.razynet.R;
import com.razytech.razynet.Utils.AppConstant;
import com.razytech.razynet.app.RazyNetApp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.razytech.razynet.Utils.StaticMethods.REQUEST_CAMERA;
import static com.razytech.razynet.Utils.StaticMethods.checkCameraPermission;


/**
 * Created by A.Noby on 6/9/2019.
 */

public class TakeImageUtiles   {


    static  String encodedImage = "",image = "";
    static  boolean isFILE = false;
    static Bitmap requestPhoto;

    public static boolean isPhoto = false;
   // static Context context;
   // static Fragment fragment;


    TakeImageReceiveView view;
    public  TakeImageUtiles(TakeImageReceiveView view){
        this.view=view;
    }

    public   void selectImage(final Context mcontext, final Activity activity) {
        final CharSequence[] items = {mcontext.getString(R.string.takephoto)
                , mcontext.getString(R.string.chooselib), mcontext.getString(R.string.cancel)};
        AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
        builder.setTitle( mcontext.getString(R.string.titledialoug));
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals(mcontext.getString(R.string.takephoto))) {

                    if(!checkCameraPermission(mcontext ,activity)) {
                        Log.d("imagecapture","-1");
                    }else {
                        Log.d("imagecapture","1");
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        activity.startActivityForResult(intent, REQUEST_CAMERA);
                    }

                } else if (items[item].equals(mcontext.getString(R.string.chooselib))) {
                    if(!checkCameraPermission(mcontext ,activity)) {
                        Log.d("imagecapture","-2");
                    }else {
                        Log.d("imagecapture","2");
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        activity.startActivityForResult(Intent.createChooser(intent, mcontext.getString(R.string.selectimage)), AppConstant.SELECT_FILE);
                    }
                } else if (items[item].equals(mcontext.getString(R.string.cancel))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


      public void selectImage(final Context mcontext, final Fragment fragment) {
       // context =mcontext;
       //  TakeImageUtiles.fragment =fragment;
        final CharSequence[] items = {mcontext.getString(R.string.takephoto)
                , mcontext.getString(R.string.chooselib), mcontext.getString(R.string.cancel)};
        AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
        builder.setTitle( mcontext.getString(R.string.titledialoug));
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals(mcontext.getString(R.string.takephoto))) {
                    gettingImageCamera(fragment);
                } else if (items[item].equals(mcontext.getString(R.string.chooselib))) {
                    // gettting Image From Gallery
                    gettingImageGallery(fragment,mcontext);
                } else if (items[item].equals(mcontext.getString(R.string.cancel))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    private  void gettingImageCamera(Fragment newfragment){
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(newfragment.getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    ) {
                ActivityCompat.requestPermissions(newfragment.getActivity(), new String[]
                                {Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        AppConstant.PICK_FROM_GALLERY);
            } else {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                newfragment.startActivityForResult(intent, REQUEST_CAMERA);
            }
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            newfragment.startActivityForResult(intent, REQUEST_CAMERA);
        }
    }
    public void gettingImageCamera(Activity newfragment){
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(newfragment,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(newfragment, new String[]
                                {Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        AppConstant.PICK_FROM_GALLERY);
            } else {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                newfragment.startActivityForResult(intent, REQUEST_CAMERA);
            }
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            newfragment.startActivityForResult(intent, REQUEST_CAMERA);
        }
    }
    private  void gettingImageGallery(Fragment newfragment , Context mcontext){
        if (Build.VERSION.SDK_INT >= 23){
            // Here, thisActivity is the current activity
            if (ActivityCompat.checkSelfPermission(newfragment.getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(newfragment.getActivity(), new String[]
                                {Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        AppConstant.PICK_FROM_GALLERY);
            } else{

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                newfragment.startActivityForResult(Intent.createChooser(intent, mcontext.getString(R.string.selectimage)), AppConstant.SELECT_FILE);

            }
        }else {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            newfragment.startActivityForResult(Intent.createChooser(intent, mcontext.getString(R.string.selectimage)), AppConstant.SELECT_FILE);
        }
    }

    public void gettingImageGallery(Activity newfragment ,Context mcontext){
        if (Build.VERSION.SDK_INT >= 23){
            // Here, thisActivity is the current activity
            if (ActivityCompat.checkSelfPermission(newfragment,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(newfragment, new String[]
                                {Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        AppConstant.PICK_FROM_GALLERY);
            } else{

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                newfragment.startActivityForResult(Intent.createChooser(intent, mcontext.getString(R.string.selectimage)), AppConstant.SELECT_FILE);

            }
        }else {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            newfragment.startActivityForResult(Intent.createChooser(intent, mcontext.getString(R.string.selectimage)), AppConstant.SELECT_FILE);
        }
    }
    public   void onCaptureImageResult(Intent data,  Context context,Activity activity) {

        RazyNetApp.setLocale();
        Bitmap thumbnail = null;
        ByteArrayOutputStream bytes;

        if (data.getData() == null) {
            thumbnail = (Bitmap) data.getExtras().get("data");
        } else {
            try {
                thumbnail = MediaStore.Images.Media.getBitmap(context.getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String fileName = System.currentTimeMillis() + ".jpg";
        File destination = new File(Environment.getExternalStorageDirectory(),
                fileName);
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int byt = thumbnail.getByteCount();
        image = fileName;
        requestPhoto = thumbnail;
        view.AftergettingImage(thumbnail,null,image,destination);
        Log.d("testimge", image+"    "+requestPhoto.toString());
        isPhoto = true;
    }

    public   void onCaptureImageResult(Bitmap thumbnail,  Context context,Activity activity) {

        RazyNetApp.setLocale();
        ByteArrayOutputStream bytes;

        bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String fileName = System.currentTimeMillis() + ".jpg";
        File destination = new File(Environment.getExternalStorageDirectory(),
                fileName);
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int byt = thumbnail.getByteCount();
        image = fileName;
        requestPhoto = thumbnail;
        view.AftergettingImage(thumbnail,null,image,destination);
       // Log.d("testimge", image+"    "+requestPhoto.toString());
        isPhoto = true;
    }

    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }



    public static Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public static String getRealPathFromURI(Context inContext,Uri uri) {
        Cursor cursor = inContext.getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    public static File CreateFile(Context context,Intent data) {
        Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        if (cursor == null)
            return null;
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String filePathstr = cursor.getString(columnIndex);
        cursor.close();
        File file = new File(filePathstr);
        //filePath =file ;
        return  file ;
    }


    public Bitmap getImageFromStorage(String path) {
        try {
            File f = new File(path);
            // First decode with inJustDecodeBounds=true to check dimensions
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, 512, 512);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f), null, options);
            return b;

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        return null;
    }
    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }


}
