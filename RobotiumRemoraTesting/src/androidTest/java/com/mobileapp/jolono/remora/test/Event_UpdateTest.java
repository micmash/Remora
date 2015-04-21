package com.mobileapp.jolono.remora.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.robotium.solo.Solo;


@SuppressWarnings("rawtypes")
public class Event_UpdateTest extends ActivityInstrumentationTestCase2 {
  	private Solo solo;

  	private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.mobileapp.jolono.remora.activity.LoginActivity";

    private static Class<?> launcherActivityClass;
    static{
        try {
            launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
        } catch (ClassNotFoundException e) {
           throw new RuntimeException(e);
        }
    }

  	@SuppressWarnings("unchecked")
    public Event_UpdateTest() throws ClassNotFoundException {
        super(launcherActivityClass);
    }

  	public void setUp() throws Exception {
        super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();
  	}
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testRun() {
        //Wait for activity: 'com.mobileapp.jolono.remora.activity.LoginActivity'
		solo.waitForActivity("LoginActivity", 2000);
        //Click on Empty Text View
		solo.clickOnView(solo.getView("email"));
        //Enter the text: 'robo@test.com'
		solo.clearEditText((android.widget.EditText) solo.getView("email"));
		solo.enterText((android.widget.EditText) solo.getView("email"), "robo@test.com");
        //Click on Empty Text View
		solo.clickOnView(solo.getView("password"));
        //Enter the text: '1'
		solo.clearEditText((android.widget.EditText) solo.getView("password"));
		solo.enterText((android.widget.EditText) solo.getView("password"), "1");
        //Click on Sign in
		solo.clickOnView(solo.getView("email_sign_in_button"));
        //Wait for activity: 'com.mobileapp.jolono.remora.activity.GetAccountActivity'
		assertTrue("GetAccountActivity is not found!", solo.waitForActivity("GetAccountActivity"));

        //Click on Events
        solo.clickOnView(solo.getView("activity_get_account_events_button"));
        //Wait for activity:
        assertTrue("GetEventsFromAccount is not found!", solo.waitForActivity("GetEventsFromAccount"));
        solo.sleep(200);

        solo.searchText("robo's Test Event 2:33 5:36");
        solo.clickOnText("robo's Test Event 2:33 5:36");
        assertTrue("GetEventActivity is not found!", solo.waitForActivity("GetEventActivity"));

        solo.clickOnView(solo.getView("fragment_event_header_name"));
        solo.clearEditText((EditText) solo.getView("fragment_event_header_name"));
        solo.enterText((EditText) solo.getView("fragment_event_header_name"), "edited");

        solo.clickOnView(solo.getView("fragment_event_header_description"));
        solo.clearEditText((EditText) solo.getView("fragment_event_header_description"));
        solo.enterText((EditText) solo.getView("fragment_event_header_description"), "edited");

        solo.clickOnView(solo.getView("fragment_event_header_save_button"));
	}
}
