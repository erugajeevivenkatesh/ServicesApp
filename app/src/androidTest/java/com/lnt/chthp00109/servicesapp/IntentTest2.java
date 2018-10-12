package com.lnt.chthp00109.servicesapp;

import android.provider.Settings;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class IntentTest2 {
    @Rule
    public IntentsTestRule<MainActivity> mactivity = new IntentsTestRule<MainActivity>(MainActivity.class);

        @Test
    public void IntentTestcaseforWifi() {
        onView(withId(R.id.WifiOnofff)).perform(click());
        onView(withId(R.id.searchnearwifi)).perform(click());
        //  intended(allOf(hasAction(Settings.ACTION_BLUETOOTH_SETTINGS)));
        intended(hasAction(Settings.ACTION_WIFI_SETTINGS));

    }
}
