package com.anu.gp24s1.ui.login;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import com.anu.gp24s1.R;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityScenarioRule = new ActivityScenarioRule<>(LoginActivity.class);
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void testUserLogin() {
        // Inputting text into EditText
        onView(withId(R.id.userEmail)).perform(typeText("test@example.com"));
        onView(withId(R.id.password)).perform(typeText("password"));

        // Clicking the login button
        onView(withId(R.id.LogInButton)).perform(click());

        // Verifying that the ProgressBar is displayed
        onView(withId(R.id.loading)).check(matches(isDisplayed()));
    }
}
