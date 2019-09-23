package com.razytech.razynet.Utils;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by A.Noby on 4/7/2019.
 */
public class Validator {
    public static boolean isEditTextEmpty(EditText editText) {
        return isTextEmpty(editText.getText().toString().trim());
    }

    public static boolean isTextEmpty(String text) {
        return text.equals("");
    }
    public static boolean validEmail(String email) {
//        Pattern pattern;
//        Matcher matcher;
//        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//        pattern = Pattern.compile(EMAIL_PATTERN);
//        matcher = pattern.matcher(email);
//        return matcher.matches();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
    public static boolean validPasswordLength(String password) {
        return !(password.length() < 6 || password.length() > 50);
    }
    public static boolean isMatch(String word1, String word2) {
        return word1.equals(word2);
    }

    public static boolean validMobileNumber(String number) {
        return number.length() == 11  && number.matches("^[0-9]+$");
    }
    public static boolean validNIDNumber(String number) {
        return number.length() == 14  && number.matches("^[0-9]+$");
    }
    public static boolean validAmountPaid(String totalamount, String paindamount) {
        double total = Double.parseDouble(totalamount);
        double paid = Double.parseDouble(paindamount);
        if (total >=paid)
        {
            return true;
        }else {
            return  false ;
        }
    }

    public static String[]  PaternString(String qrCode) {
        Pattern pattern = Pattern.compile(".,.");
        Matcher matcher = pattern.matcher(qrCode);
        String[] parts = qrCode.split(",");
        if (matcher.find())
            return parts ;
        else
            return  null ;

    }



}
