package com.example.ad340_team_project;

import android.os.RemoteException;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.uiautomator.UiDevice;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void hasCorrectEmail() {
        onView(withId(R.id.email)).perform(typeText(Constants.TEST_EMAIL),
                closeSoftKeyboard());
        onView(withId(R.id.email)).check(matches(withText(Constants.TEST_EMAIL)));
    }

    @Test
    public void hasCorrectName() {
        onView(withId(R.id.dogName)).perform(typeText(Constants.TEST_NAME),
                closeSoftKeyboard());
        onView(withId(R.id.dogName)).check(matches(withText(Constants.TEST_NAME)));
    }

    @Test
    public void hasCorrectBreed() {
        onView(withId(R.id.dogBreed)).perform(typeText(Constants.TEST_BREED),
                closeSoftKeyboard());
        onView(withId(R.id.dogBreed)).check(matches(withText(Constants.TEST_BREED)));
    }

    @Test
    public void hasCorrectAge() {
        onView(withId(R.id.dogAge)).perform(typeText(Constants.TEST_AGE),
                closeSoftKeyboard());
        onView(withId(R.id.dogAge)).check(matches(withText(Constants.TEST_AGE)));
    }

    @Test
    public void hasCorrectSize() {
        onView(withId(R.id.dogSize)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.dogSize)).check(matches(withSpinnerText(containsString(Constants.TEST_SIZE))));
    }

    @Test
    public void hasCorrectDescription() {
        onView(withId(R.id.description))
                .perform(typeText(Constants.TEST_DESC), closeSoftKeyboard());
        onView(withId(R.id.description)).check(matches(withText(Constants.TEST_DESC)));
    }

//    @Test
//    public void hasLoginButton() {
//        onView(withId(R.id.login)).perform(click());
//        onView(withId(R.id.login)).check(matches(withText(Constants.TEST_BTN_LOGIN)));
//    }

    @Test
    public void checkOrientationView() throws RemoteException {
        UiDevice device = UiDevice.getInstance(getInstrumentation());
        device.setOrientationLeft();
        onView(withId(R.id.dogName)).check(matches(withText(Constants.TEST_EMPTY)));
        device.setOrientationNatural();
        onView(withId(R.id.dogName)).check(matches(withText(Constants.TEST_EMPTY)));
    }

    @Test
    public void hasDeveloperNames() {
        onView(withId(R.id.projectName)).check(matches(withText(Constants.TEST_DEV_NAMES)));
    }

}
