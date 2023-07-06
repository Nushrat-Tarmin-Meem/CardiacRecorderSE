package com.example.cardiacrecorder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.rule.ActivityTestRule;
import org.junit.Rule;

public class UITesting {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule= new ActivityTestRule<MainActivity>(MainActivity.class);

    private String email="meem.cse.2k19@gmail.com";
    private String password="1234@5678";
    @Before
    public void setUp() throws Exception {
    }

    @Test
    /**
     * This method is for registration and login portion of ui/intent testing
     */
    public void registerandlogin()
    {
        onView(withId(R.id.mainactivity)).check(matches(isDisplayed()));
        onView(withId(R.id.r)).perform(click());
        onView(withId(R.id.registeractivity)).check(matches(isDisplayed()));
        onView(withId(R.id.regi)).perform(click());
        onView(withId(R.id.loginactivity)).check(matches(isDisplayed()));
        onView(withId(R.id.lmail)).perform(typeText(email));
        onView(withId(R.id.lpassword)).perform(typeText(password));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.lbutton)).perform(click());
    }
    @After
    public void tearDown() throws Exception {
    }
}