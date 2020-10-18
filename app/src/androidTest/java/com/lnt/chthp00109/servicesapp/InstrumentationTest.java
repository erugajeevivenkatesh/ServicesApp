package com.lnt.chthp00109.servicesapp;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;



import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;
import java.util.Collections;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
@LargeTest
public class InstrumentationTest   {

    @Rule
    public ActivityTestRule<MainActivity> mainactivity=new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void checkchangedText()
    {
        onView(withId(R.id.Batterystate)).check(matches(withText("CHARGING")));
    }
    @Test
    public void checksatusofdischarging()
    {
        onView(withId(R.id.Batterystate)).check(matches(withText("DISCHARGING")));
    }
    @Test
    public void statusofchargingfull()
    {
        onView(withId(R.id.Batterystate)).check(matches(withText("BATTERY FULL")));
    }


    @Test
    public void searchnearbuttonclikable()
    {
        onView(withId(R.id.WifiOnofff)).check(matches(isClickable()));
       }
    @Test
    public void searchforWifibutton()
    {
        onView(withId(R.id.searchnearwifi)).check(matches(isClickable()));
    }
    @Test
    public void onOffButtonclickble()
    {
        onView(withId(R.id.Onofbluetooth)).check(matches(isClickable()));
    }

    @Test
    public void SearchAvailableButetooth()
    {
        onView(withId(R.id.Searchavailable)).check(matches(isClickable()));
    }

    @Test
    public void ScedulerTaskbutton()
    {onView(withId(R.id.ScedulerTask)).check(matches(isClickable()));
    }


    @Test
    public void navigate()
    {   getActivityInstance();
        onView(withId(R.id.ScedulerTask)).perform(click());
        Activity activity=getActivityInstance();
        boolean b=(activity instanceof Sceduletask);
        assertTrue(b);
    }
    public Activity getActivityInstance()
    {
        final  Activity[] activities=new Activity[1];
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                Activity currentActivity=null;
                Collection resumedActivity=ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                if(resumedActivity.iterator().hasNext())
                {
                    currentActivity=(Activity)resumedActivity.iterator().next();
                    activities[0]=currentActivity;
                }
            }
        });
        return activities[0];
    }
    @Before
    // getPermission() will use to ask permission from user for access storage, location.
    public void grantPermissions()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            getInstrumentation().getUiAutomation().executeShellCommand("pm grant"+getTargetContext().getPackageName()
                    +"android.permission.ACCESS_FINE_LOCATION");
        }
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            getInstrumentation().getUiAutomation().executeShellCommand("pm grant"+getTargetContext().getPackageName()
                    +"android.permission.READ_EXTERNAL_STORAGE");

            getInstrumentation().getUiAutomation().executeShellCommand("pm grant"+getTargetContext().getPackageName()
                    +"android.permission.WRITE_EXTERNAL_STORAGE");
        }
    }









}
