package demo.bookssample

import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun jumpToStartFragment() {
        activityTestRule.activity.apply {
            runOnUiThread {
                findNavController(R.id.nav_fragment).navigate(R.id.navigation_new_released)
            }
        }
    }

    @Test
    fun clickOnNewReleasedBooks_opensNewReleasedView() {
//        onView(withId(R.id.navigation_new_released)).perform(click())
    }

    @Test
    fun clickSearchBooks_opensSearchBooksView() {
//        onView(withId(R.id.navigation_books)).perform(click())
    }

    @Test
    fun clickSettings_opensSettingsView() {
        onView(withId(R.id.navigation_settings)).perform(click())
        onView(withText("Settings title")).check(matches(isDisplayed()))
    }
}