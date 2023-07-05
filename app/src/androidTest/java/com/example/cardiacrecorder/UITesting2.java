package com.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class UITesting2 {
    @Before
    public void setUp() throws Exception {
    }
    private String name="Meem", sys="80", dias="110", rate="150";
    private String email="meem.cse.2k19@gmail.com";
    private String password="1234@5678";
    @Rule
    public ActivityTestRule<HomeActivity> homeActivityActivityTestRule = new ActivityTestRule<HomeActivity>(HomeActivity.class);
    @Test
    public void addrecording()
    {

        onView(withId(R.id.addemail2)).perform(typeText(email));
        onView(withId(R.id.addRecord)).perform(click());
        onView(withId(R.id.addactivity)).check(matches(isDisplayed()));
        onView(withId(R.id.name)).perform(typeText(name));
        Espresso.pressBack();
        onView(withId(R.id.systolic)).perform(typeText(sys));
        Espresso.pressBack();
        onView(withId(R.id.diastolic)).perform(typeText(dias));
        Espresso.pressBack();
        onView(withId(R.id.HeartRate)).perform(typeText(rate));
        Espresso.pressBack();
        onView(withId(R.id.give_comment)).perform(typeText("High Heartbeat!"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.submit)).perform(click());
        Espresso.pressBack();
        Espresso.pressBack();
    }

    @Test
    public void viewrecord()
    {
        onView(withId(R.id.hac)).check(matches(isDisplayed()));
        onView(withId(R.id.uname)).perform(typeText("Meem"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.viewRecord)).perform(click());
        onView(withId(R.id.viewrecording)).check(matches(isDisplayed()));
    }
    @Test
    public void logout()
    {
        onView(withId(R.id.uname)).perform(typeText("Meem"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.hac)).check(matches(isDisplayed()));
        onView(withId(R.id.blo)).perform(click());
        onView(withId(R.id.mainactivity)).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
    }
}