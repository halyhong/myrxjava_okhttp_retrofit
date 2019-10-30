package com.example.commonlib.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

public class SystemPermission {

    //申请权限
    private static void requestAlertWindowPermission(Context ctx) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        intent.setData(Uri.parse("package:" + ctx.getPackageName()));

        if (!(ctx instanceof Activity)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        ((Activity)ctx).startActivityForResult(intent, 1);
    }
}
