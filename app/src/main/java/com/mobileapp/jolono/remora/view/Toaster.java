package com.mobileapp.jolono.remora.view;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Noah on 4/20/2015.
 */
public class Toaster {
    public static void popShortSimpleToast(Context context, CharSequence text) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
    }
}
