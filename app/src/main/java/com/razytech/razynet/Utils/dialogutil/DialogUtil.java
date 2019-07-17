package com.razytech.razynet.Utils.dialogutil;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DialogUtil {

    // 0 in parameters means not needed , use default title.
    DialogUtilResponse response;
    DialougUtilsDate dateresponse  ;
    public  DialogUtil  (DialogUtilResponse response){
        this.response = response ;
    }
    public  DialogUtil  (DialougUtilsDate response){
        this.dateresponse = response ;
    }
    public  DialogUtil  (DialogUtilResponse response ,  DialougUtilsDate date ){
        this.response = response ;
        this.dateresponse =  date ;
    }

    public static int ImageInfoIcon = 1;
    public static int ImageErrorIcon = 2;
    public static int Imagenonr = 0  ;

    public  void showSingleActionDialog(Context context, @StringRes int title,
                                              @StringRes int text,boolean showImageDialoug,int ImageDialougType,
                                              AppDialog.OkButtonClickListener clickListener) {
        showDialog(context, title, text,0 ,ImageDialougType, clickListener);
    }

    public  void showSingleActionDialog(Context context, @StringRes int text,
                                              AppDialog.OkButtonClickListener clickListener) {
        showDialog(context, 0, text, 0,0, clickListener);
    }

    public  void showSingleActionDialog(Context context, @StringRes int title, @StringRes int text,int ImageDialougType) {
        showDialog(context, title, text, 0,ImageDialougType, null);
    }

    public  void showSingleActionDialog(Context context, @StringRes int title, @StringRes int text,int ImageDialougType, AppDialog.OkButtonClickListener clickListener) {
        showDialog(context, title, text, 0,ImageDialougType, clickListener);
    }

    public static void showTwoActionDialog(Context context, @StringRes int title,
                                           @StringRes int text, int ImageDialougType, AppDialog.RightButtonClickListener clickListener
            , AppDialog.LeftButtonClickListener leftButtonClickListener) {

        showDialog(context, title, text, 0, 0,ImageDialougType, leftButtonClickListener, clickListener);
    }


    public  void showTwoActionDialog(Context context,
                                           @StringRes int text, AppDialog.RightButtonClickListener clickListener
            , AppDialog.LeftButtonClickListener leftButtonClickListener) {

        showDialog(context, 0, text, 0, 0,0, leftButtonClickListener, clickListener);
    }

    public  void showTwoActionDialog(Context context, @StringRes int title, @StringRes int text) {
        showDialog(context, title, text, 0, 0,0, null, null);
    }

//    public static void showDialougSingleChooice(Context context, @StringRes int title, @StringRes int okButtonText, List<String> listarray,AppDialog.OkButtonClickListener okButtonClickListener) {
//         showDialogSingleChooice(context, title, okButtonText, listarray,okButtonClickListener );
//    }


    private static void showDialog(Context context, @StringRes int title, @StringRes int text,
                                   @StringRes int leftButtonText, @StringRes int rightButtonText
            , int ImageDialougType, AppDialog.LeftButtonClickListener leftButtonClickListener
            , AppDialog.RightButtonClickListener rightButtonClickListener) {

        AppDialog.SmallDialog smallDialog = new AppDialog.SmallDialog(context);
        smallDialog.dialogTitle(title).dialogText(text).showImageDialoug(
                false,ImageDialougType);
        if (leftButtonText != 0) {
            smallDialog.leftButtonText(leftButtonText);
        }
        if (rightButtonText != 0) {
            smallDialog.rightButtonText(rightButtonText);
        }
        if (leftButtonClickListener != null) {
            smallDialog.leftButtonClickListener(leftButtonClickListener);
        }
        if (rightButtonClickListener != null) {
            smallDialog.rightButtonClickListener(rightButtonClickListener);
        }
        smallDialog.show();
    }

    private  void showDialog(Context context, @StringRes int title, @StringRes int text,
                                   @StringRes int okButtonText,int ImageDialougType,AppDialog.OkButtonClickListener okButtonClickListener) {

        AppDialog.SmallDialog smallDialog = new AppDialog.SmallDialog(context);
        smallDialog.dialogTitle(title).dialogText(text).showImageDialoug(true,ImageDialougType);
        if (okButtonText != 0) {
            smallDialog.okButtonText(okButtonText);
        }
        if (okButtonClickListener != null) {
            smallDialog.okButtonClickListener(okButtonClickListener);
        }
        smallDialog.show();
    }
    public   int  indexofarray = -1;

    public    void   showDialogSingleChooice(final Context context, @StringRes int title, @StringRes int okButtonText, List<String> listarray){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getResources().getString(title));
        //idcity = idscity.get(0);
        builder.setSingleChoiceItems(listarray.toArray(new String[listarray.size()]),
                -1, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int poistion) {
                        indexofarray = poistion;
                        Log.e("position1",""+indexofarray);

                    }
                });
        builder.setPositiveButton(context.getResources().getString(okButtonText),new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {

              response.selectedValueSingleChoice(indexofarray);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


    public    void   showDialogSingleChooice(final Context context, @StringRes int title, @StringRes int okButtonText, List<String> listarray, final String arrayType){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getResources().getString(title));
        //idcity = idscity.get(0);
        indexofarray =-1;
        builder.setSingleChoiceItems(listarray.toArray(new String[listarray.size()]),
                -1, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int poistion) {
                        indexofarray = poistion;
                        Log.e("position1",""+indexofarray);

                    }
                });
        builder.setPositiveButton(context.getResources().getString(okButtonText),new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
               if (indexofarray != -1)
                   response.selectedValueSingleChoice(indexofarray,arrayType);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public    void   showDialogDatePicker(final Context context){
        DatePickerDialog dialog=  new DatePickerDialog(context, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));
        // set max date
        dialog.getDatePicker().setMaxDate(new Date().getTime());
        dialog.show();
    }

    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
          //  updateLabel();
           dateresponse.DateFormatResponse(myCalendar);
        }
    };
}
