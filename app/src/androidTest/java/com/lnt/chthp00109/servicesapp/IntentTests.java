package com.lnt.chthp00109.servicesapp;

import android.os.Build;
import android.provider.Settings;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.InstrumentationRegistry.getTargetContext;
import static  android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static  android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class IntentTests {
    UiDevice device;
    @Rule
    public IntentsTestRule<MainActivity> mactivity = new IntentsTestRule<MainActivity>(MainActivity.class);

    @Test
    public void bluetoothIntentTest() throws Exception{
        onView(withId(R.id.Onofbluetooth)).perform(click());
        assertviewwithTextvisible(device,"ALLOW");
        //onView(withText("ALLOW")).perform(click());
        onView(withId(R.id.Searchavailable)).perform(click());
       intended(hasAction(Settings.ACTION_BLUETOOTH_SETTINGS));
    }




    public static  void assertviewwithTextvisible(UiDevice device,String text)
    {
        UiObject allowbutton=device.findObject(new UiSelector().text(text));
        if(!allowbutton.exists())
        {
            throw new AssertionError("view with text<"+text+"> not found");
        }
    }
    public  static  void denycurrentpermission(UiDevice device) throws UiObjectNotFoundException
    {
        UiObject denybutton=device.findObject(new UiSelector().text("DENY"));
        denybutton.click();
    }
    @Before
    public void grantPermissions() {
        device=UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getInstrumentation().getUiAutomation().executeShellCommand("pm grant" + getTargetContext().getPackageName()
                    + "android.permission.ACCESS_FINE_LOCATION");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getInstrumentation().getUiAutomation().executeShellCommand("pm grant" + getTargetContext().getPackageName()
                    + "android.permission.BLUETOOTH_ADMIN");

            getInstrumentation().getUiAutomation().executeShellCommand("pm grant" + getTargetContext().getPackageName()
                    + "android.permission.BLUETOOTH");
        }
    }

}
