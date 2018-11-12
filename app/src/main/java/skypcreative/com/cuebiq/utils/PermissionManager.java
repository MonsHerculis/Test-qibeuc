package skypcreative.com.cuebiq.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;

import skypcreative.com.cuebiq.R;


public class PermissionManager {
    Context context;
    View view;
    public PermissionManager(Context context, View view){
        this.context = context;
        this.view = view;

    }

    public void setPermission(final String permission){
        if (ContextCompat.checkSelfPermission(context, permission)!= PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,permission)) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                alertBuilder.setCancelable(true);
                alertBuilder.setTitle(context.getResources().getString(R.string.permessi_dialog_title));
                alertBuilder.setMessage(context.getResources().getString(R.string.permessi_dialog_message));
                alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,new String[]{permission},3);
                    }
                });

                AlertDialog alert = alertBuilder.create();
                alert.show();
            } else {

                ActivityCompat.requestPermissions((Activity) context, new String[]{permission},3);

            }
        }/* else {

        }*/
    }
}