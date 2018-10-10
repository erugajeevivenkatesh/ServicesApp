package com.lnt.chthp00109.servicesapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.robotium.solo.Solo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
@LargeTest
public class InstrumentationTest   {

    @Rule
    public ActivityTestRule<MainActivity> mainactivity=new ActivityTestRule(MainActivity.class);

    @Test
    public void checkchangedText()
    {
        onView(withId(R.id.Batterystate)).check(matches(withText("CHARGING")));
    }
    @Test
    public void bluetoothbuttonclikable()
    {
        onView(withId(R.id.Onofbluetooth)).check(matches(isClickable()));
    }
    @Test
    public void searchnearbuttonclikable()
    {
        onView(withId(R.id.Searchavailable)).check(matches(isClickable()));
    }
    @Test
    public void searchwifibuttonclickable()
    {
        onView(withId(R.id.WifiOnofff)).check(matches(isClickable()));
    }
    @Test
    public void searchnearwifibuttonclickable()
    {
        onView(withId(R.id.searchnearwifi)).check(matches(isClickable()));
    }
    @Test
    public void Scedulertaskbuttonclickable()
    {
        onView(withId(R.id.ScedulerTask)).check(matches(isClickable()));
    }









}
