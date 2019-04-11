package my.repository.marvelapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import my.repository.marvelapp.characterList.CharacterListActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class HomeInstrumentedTest {

    @Rule @JvmField
    var activityRule: ActivityTestRule<CharacterListActivity> =
        ActivityTestRule(CharacterListActivity::class.java, false, true)

    @Test
    fun whenActivityLaunched_shouldClick(){
        onView(withId(R.id.recycler_view_characters)).perform(click())
    }

    @Test
    fun whenActivityLaunched_shouldCheckIfItemsDisplayed(){
        onView(withId(R.id.recycler_view_characters)).check(matches(isDisplayed()))
    }

    @Test
    fun whenActivityLaunched_shouldGoToCharDetail(){
        onView(withId(R.id.recycler_view_characters)).perform(click())
    }

}
