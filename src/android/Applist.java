package org.jmrezayi2.Applist;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import android.os.Environment;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.BitmapDrawable;  
import android.content.Context;
import android.graphics.PixelFormat;
import java.util.List;

public class Applist extends CordovaPlugin {
    public static final String ACTION_ADD_CALENDAR_ENTRY = "addCalendarEntry";


    //保存图片png
    public static void drawableTofile(Drawable drawable,String path)
    {

            File file = new File(path);
            Bitmap bitmap=((BitmapDrawable)drawable).getBitmap();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();


            //write the bytes in file
            FileOutputStream fos;
            try {
                fos = new FileOutputStream(file);
                fos.write(bitmapdata);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

    }


    //路径设置 
    public String getSDPath()
     {
            File SDdir=null;
            boolean sdCardExist= Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
            if(sdCardExist){
                    SDdir=Environment.getExternalStorageDirectory();
            }
            if(SDdir!=null){

                    return SDdir.toString();
            }
            else{
                    return null;
            }
    }


    //获取SD卡路径
    public static void makeRootDirectory(String filePath) {  
        File file = null;  
        try {  
            file = new File(filePath);  
            if (!file.exists()) {
                file.mkdirs();  //make Directory
            }  
        } catch (Exception e) {  
             e.printStackTrace();
        }  
    }


    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException 
    {
        if (ACTION_ADD_CALENDAR_ENTRY.equals(action))
        { 
            cordova.getThreadPool().execute(new Runnable() 
            {
                public void run()
                {
                    try
                    {
                        //get a list of installed apps.
                        PackageManager pm = cordova.getActivity().getPackageManager();
                        List<ApplicationInfo> packages = pm.getInstalledApplications(0);
                        
                        JSONArray  app_list = new JSONArray();
                        int cnt =0;
                        String path=getSDPath();
                        makeRootDirectory(path+"/com.ionicframework.xxx/");
                        makeRootDirectory(path+"/com.ionicframework.xxx/Cache/");
                        for (ApplicationInfo packageInfo : packages) 
                        {
                            if ((packageInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1)
                            {
                                continue;
                            }
                            else
                            {
                                    JSONObject info = new JSONObject();  
                                    info.put("name",packageInfo.loadLabel(pm));//这里获取的是应用名
                                   String img_name =  "/com.ionicframework.xxx/Cache/"+ packageInfo.packageName +".png";//图片保存的是包名
                                   info.put("img",path+img_name);
                                    //cheak exist  or not
                                    File  cheakfile  = new File( path + img_name );
                                    if(  !cheakfile.exists()  )
                                    {
                                       //获取图像
                                        Drawable icon = pm.getApplicationIcon(packageInfo);
                                        if(icon!=null)
                                        {
                                            drawableTofile(icon,  path+img_name);
                                        }
                                    }
                                   app_list.put(cnt++,info);
                            }
                        }
                        callbackContext.success( app_list );
                     } 
                     catch(Exception e) 
                     {
                        System.err.println("Exception: " + e.getMessage());
                        callbackContext.error(e.getMessage());
                    }
                }// end of Run Runnable()
            });// end of run getThreadPool()
            return true;
        }
        //
        callbackContext.error("Invalid action");
        return false;
    } 
}
