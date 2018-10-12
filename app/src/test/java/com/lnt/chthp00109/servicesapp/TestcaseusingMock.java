package com.lnt.chthp00109.servicesapp;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;


import java.io.File;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestcaseusingMock {
    public static final String Google="WHO ARE YOU";
//    @Mock
//    MainActivity mockMainActivity;
//    @Rule
//    public MockitoRule mockitoRule=MockitoJUnit.rule();


    @Test
    public void checkLoffile()
    {
        SaveLogFiles saveLogFiles=mock(SaveLogFiles.class);
        when(saveLogFiles.Logfile()).thenReturn("succ");
        assertEquals(saveLogFiles.Logfile(),"succ");
    }
    @Test(expected =IllegalStateException.class)
    public void initializationadapters()
    {
        MainActivity sceduletask=mock(MainActivity.class);
        doThrow(IllegalStateException.class).when(sceduletask).initializationadapters();
        sceduletask.initializationadapters();
    }
    @Test(expected = IllegalStateException.class)
     public void mockvoidmethod()
    {
        MainActivity activity=mock(MainActivity.class);
        doThrow(IllegalStateException.class).when(activity).volumecontrol();
        activity.volumecontrol();
    }
    @Test
    public void TestArgumentmatcher()
    {
        SaveLogFiles saveLogFiles=mock(SaveLogFiles.class);
        File file=new File(Environment.getExternalStorageDirectory()+"/textfile.txt");
        when(saveLogFiles.Writedatatofile(file,"Hello")).thenReturn(true);
        assertTrue(saveLogFiles.Writedatatofile(file,"Hello"));
    }
    @Test
    public void methodcheck()
    {
            MainActivity mainActivity=mock(MainActivity.class);
        try {
            doThrow(new Exception()).when(mainActivity).SearchWifimethod();
            mainActivity.SearchWifimethod();
        } catch (Exception e) {
           Log.e("Testcase",e.toString());
        }
        //mainActivity.SearchWifimethod();
    }
    @Test(expected = IllegalStateException.class)
    public void Settaskmethod()
    {
        Sceduletask sceduletask=mock(Sceduletask.class);
        doThrow(IllegalStateException.class).when(sceduletask).setTask(anyLong(),anyString());
        sceduletask.setTask(1234,"Task time");
    }
    @Test(expected = IllegalStateException.class)
    public void methodsenddatatolog()
    {
        SaveLogFiles saveLogFiles=mock(SaveLogFiles.class);
        doThrow(IllegalStateException.class).when(saveLogFiles).Senddata(anyString());
        saveLogFiles.Senddata("dataforlog files");
    }
    @Test(expected = IllegalStateException.class)
    public void Disablealarmmethod()
    {
        Sceduletask sceduletask=mock(Sceduletask.class);
        doThrow(IllegalStateException.class).when(sceduletask).disablealarm();
        sceduletask.disablealarm();
    }
    @Test(expected = IllegalStateException.class)
    public void Notification()
    {
        MyAlarm myAlarm=mock(MyAlarm.class);
        doThrow(IllegalStateException.class).when(myAlarm).Notifications();
        myAlarm.Notifications();
    }
    @Test(expected = IllegalStateException.class)
    public void Bluetootonofffunction()
    {
        MainActivity mainActivity=mock(MainActivity.class);
        doThrow(IllegalStateException.class).when(mainActivity).BluetoothOnofffunction();
        mainActivity.BluetoothOnofffunction();
    }
    @Test(expected = IllegalStateException.class)
    public  void TestDevicestatus()
    {
        MainActivity mainActivity=mock(MainActivity.class);
        doThrow(IllegalStateException.class).when(mainActivity).Devicestause(anyInt(),anyInt());
        mainActivity.Devicestause(45,34);
    }

}
