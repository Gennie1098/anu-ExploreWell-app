package com.anu.gp24s1;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.intent.Intents;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;

import com.anu.gp24s1.ui.login.LoginActivity;

@RunWith(AndroidJUnit4.class)
public class StartScreenTest {

    @Rule
    public ActivityScenarioRule<StartScreen> activityRule = new ActivityScenarioRule<>(StartScreen.class);

    @Before
    public void setUp() {
        Intents.init();  // Initialize Intents before each test
    }

    @After
    public void tearDown() {
        Intents.release();  // Release Intents after each test
    }

    @Test
    public void testNavigateToLogin() {
        // Perform a click on the login button
        onView(withId(R.id.LogInButton)).perform(click());

        // Intended to check the expected intent which is LoginActivity
        intended(hasComponent(LoginActivity.class.getName()));
    }
}
