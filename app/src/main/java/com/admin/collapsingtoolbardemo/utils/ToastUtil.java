package com.admin.collapsingtoolbardemo.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by admin on 2018/1/8.
 */

public class ToastUtil {

    public static void showMessage(Context context, String text){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }
}
