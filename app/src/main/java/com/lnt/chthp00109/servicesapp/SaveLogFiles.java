package com.lnt.chthp00109.servicesapp;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class SaveLogFiles  {
    private static final String TAG=SaveLogFiles.class.getSimpleName();
    private static int Count=0;
    private   String ChangeableFilename="Log"+Count;
   private String FileName=ChangeableFilename+".txt";

    public void Senddata(String Logdetails)
    {
        Log.i(TAG,Logdetails);
       // String FileName=ChangeableFilename+".txt";
        try {
            File fileroot = new File(
                    Environment.getExternalStorageDirectory().getAbsolutePath() + "/Venky");
            Log.d(TAG,fileroot.toString());
            //create the file if not exists
            if(!fileroot.exists())
            {
                if(fileroot.mkdirs())
                    Log.d(TAG,"fileroot is not existed at this time it is created");
            }

            File textfile=new File(fileroot+"/"+FileName);
            //creating textfile directory
            Log.d(TAG,textfile.toString());

            if(textfile.exists())
            {
                if(Writedatatofile(textfile,Logdetails))
                Log.d(TAG,"writing the data is success");
                Log.d(TAG,String.valueOf(textfile.length()));
            }
            else {
                if (textfile.createNewFile()) {
                    Log.d(TAG, "successfully created Textfile");
                    if(Writedatatofile(textfile,Logdetails))
                        Log.d(TAG,"writing the data is success");
                }
            }

        }catch (FileNotFoundException e)
        {
            Log.e(TAG,e.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }
    public boolean Writedatatofile(File textfile,String message)
    {   boolean data=false;
        try
        {
            if(textfile.length()/1024<200)
            {
                FileWriter fileWriter=new FileWriter(textfile,true);
                BufferedWriter bufferaWriter=new BufferedWriter(fileWriter);
                bufferaWriter.newLine();
                bufferaWriter.write(message);
                bufferaWriter.close();
                fileWriter.close();
                data=true;
            }
            else
            {
                Count=Count+1;
                ChangeableFilename="Log"+Count;
                FileName=ChangeableFilename+".txt";
                Log.d(TAG,"created file directory"+FileName.toString());
                Log.d(TAG,String.valueOf(Count));
            }
        }
        catch (IOException e)
        {  data=false;
            Log.e(TAG, String.valueOf(Log.e(TAG,e.toString())));
        }
        return data;
    }
    public String Logfile()
    {
        String s="";
        try {

            s= String.valueOf(Runtime.getRuntime().exec(" logcat -t"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

}
