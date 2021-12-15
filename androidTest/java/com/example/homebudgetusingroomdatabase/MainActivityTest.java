package com.example.homebudgetusingroomdatabase;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction textView = onView(
                allOf(withText("AVAILABLE BALANCE"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView.check(matches(withText("AVAILABLE BALANCE")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.remaining_balance), withText("Resultant Balance"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView2.check(matches(withText("Resultant Balance")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.ti1), withText("TOTAL INCOME"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView3.check(matches(withText("TOTAL INCOME")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.ti2), withText("TOTAL EXPENSE"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView4.check(matches(withText("TOTAL EXPENSE")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.total_expense), withText("0"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView5.check(matches(withText("0")));

        ViewInteraction editText = onView(
                allOf(withId(R.id.desc), withText("ADD DESCRIPTION"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        editText.check(matches(withText("ADD DESCRIPTION")));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.val), withText("VALUE"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        editText2.check(matches(withText("VALUE")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.textView), withText("DATA VALUES"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView6.check(matches(withText("DATA VALUES")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.textView), withText("DATA VALUES"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView7.check(matches(withText("DATA VALUES")));
    }
}
