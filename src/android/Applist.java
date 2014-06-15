package org.jmrezayi2.Applist;
 
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.R.drawable;
import android.view.View;
import android.os.Bundle;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;



import java.util.ArrayList;
import java.util.List;

public class Applist extends CordovaPlugin {
    public static final String ACTION_ADD_CALENDAR_ENTRY = "addCalendarEntry";
    
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        try {
            if (ACTION_ADD_CALENDAR_ENTRY.equals(action)) { 
                JSONObject arg_object = args.getJSONObject(0);
                //get a list of installed apps.
                PackageManager pm = this.cordova.getActivity().getPackageManager();
                List<ApplicationInfo> packages = pm.getInstalledApplications(0);
                String label = "tt"; 
                		//(String)pm.getApplicationLabel(apps.get(0));
                for (ApplicationInfo packageInfo : packages) {
                    //Log.d(TAG, "Installed package :" + packageInfo.packageName);
                	if ((packageInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1){
                		
                	}else{
                		label=label.concat(packageInfo.packageName);
                		label=label.concat(";");
                		//icon = pm.getApplicationIcon(packageInfo);
                		
                	}
                	
                    //Log.d(TAG, "Source dir : " + packageInfo.sourceDir);
                    //Log.d(TAG, "Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName)); 
                }
                
                 /* Intent balIntent = new Intent(Intent.ACTION_EDIT)
                    .setType("vnd.android.cursor.item/event")
                    .putExtra("beginTime", arg_object.getLong("startTimeMillis"))
                    .putExtra("endTime", arg_object.getLong("endTimeMillis"))
                    .putExtra("title", arg_object.getString("title"))
                    .putExtra("description", arg_object.getString("description"))
                    .putExtra("eventLocation", arg_object.getString("eventLocation"));
             
               this.cordova.getActivity().startActivity(balIntent);
             */
               callbackContext.success(label);
               return true;
            }
            callbackContext.error("Invalid action");
            return false;
        } catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
            callbackContext.error(e.getMessage());
            return false;
        } 
    }
}