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
    private static  String ChangeableFilename="Log"+Count;


    public void Senddata(String Logdetails)
    {
        Log.i(TAG,Logdetails);
        String FileName=ChangeableFilename+".txt";
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
                Writedatatofile(textfile,Logdetails);
                Log.d(TAG,"writing the data is success");
                Log.d(TAG,String.valueOf(textfile.length()));
            }
            else {
                if (textfile.createNewFile()) {
                    Log.d(TAG, "successfully created Textfile");
                    Writedatatofile(textfile,Logdetails);
                }
            }

        }catch (FileNotFoundException e)
        {
            Log.e(TAG,e.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    } public void Writedatatofile(File textfile,String message)
    {
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
            }
            else
            {
                Count=Count+1;
            }
        }
        catch (IOException e)
        {
            Log.e(TAG, String.valueOf(Log.e(TAG,e.toString())));
        }
    }

}
