package com.mobileapp.jolono.remora.test;

import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


@SuppressWarnings("rawtypes")
public class RoboCreateEventAndCreateGroupViewGroupsAfter extends ActivityInstrumentationTestCase2 {
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
    public RoboCreateEventAndCreateGroupViewGroupsAfter() throws ClassNotFoundException {
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
        //Click on map
		//solo.clickOnView(solo.getView("activity_get_account_map_button"));
        //Wait for activity: 'com.mobileapp.jolono.remora.activity.MapsActivity'
		//assertTrue("MapsActivity is not found!", solo.waitForActivity("MapsActivity"));
        //Set default small timeout to 20471 milliseconds
		//Timeout.setSmallTimeout(20471);
        //Press menu back key
		//solo.goBack();
        //Click on Create Event
		solo.clickOnView(solo.getView("activity_get_account_create_event_button"));
        //Wait for activity: 'com.mobileapp.jolono.remora.activity.CreateEventActivity'
		assertTrue("CreateEventActivity is not found!", solo.waitForActivity("CreateEventActivity"));
        //Click on Empty Text View
		solo.clickOnView(solo.getView("activity_create_event_name"));
        //Enter the text: 'robo's Test Event'
		solo.clearEditText((android.widget.EditText) solo.getView("activity_create_event_name"));
		solo.enterText((android.widget.EditText) solo.getView("activity_create_event_name"), "robo's Test Event");
        //Assert that: 'location' is shown
		assertTrue("'location' is not shown!", solo.waitForText(java.util.regex.Pattern.quote("location"), 1, 20000, true, true));
        //Click on Empty Text View
		solo.clickOnView(solo.getView("activity_create_event_location"));
        //Enter the text: 'my house!'
		solo.clearEditText((android.widget.EditText) solo.getView("activity_create_event_location"));
		solo.enterText((android.widget.EditText) solo.getView("activity_create_event_location"), "my house!");
        //Click on Empty Text View
		solo.clickOnView(solo.getView("activity_create_event_start_time"));
        //Enter the text: '2:33'
		solo.clearEditText((android.widget.EditText) solo.getView("activity_create_event_start_time"));
		solo.enterText((android.widget.EditText) solo.getView("activity_create_event_start_time"), "2:33");
        //Click on Empty Text View
		solo.clickOnView(solo.getView("activity_create_event_end_time"));
        //Enter the text: '5:36'
		solo.clearEditText((android.widget.EditText) solo.getView("activity_create_event_end_time"));
		solo.enterText((android.widget.EditText) solo.getView("activity_create_event_end_time"), "5:36");
        //Click on Empty Text View
		solo.clickOnView(solo.getView("activity_create_event_description"));
        //Enter the text: 'we are all going to a party!'
		solo.clearEditText((android.widget.EditText) solo.getView("activity_create_event_description"));
		solo.enterText((android.widget.EditText) solo.getView("activity_create_event_description"), "we are all going to a party!");
        //Click on create
		solo.clickOnView(solo.getView("activity_create_event_create_button"));
        //Wait for activity: 'com.mobileapp.jolono.remora.activity.CreateGroupActivity'
		assertTrue("CreateGroupActivity is not found!", solo.waitForActivity("CreateGroupActivity"));
        //Click on Empty Text View
		solo.clickOnView(solo.getView("activity_create_group_name"));
        //Enter the text: 'robots group'
		solo.clearEditText((android.widget.EditText) solo.getView("activity_create_group_name"));
		solo.enterText((android.widget.EditText) solo.getView("activity_create_group_name"), "robots group");
        //Click on Empty Text View
		solo.clickOnView(solo.getView("activity_create_group_description"));
        //Enter the text: 'we are all robots'
		solo.clearEditText((android.widget.EditText) solo.getView("activity_create_group_description"));
		solo.enterText((android.widget.EditText) solo.getView("activity_create_group_description"), "we are all robots");
        //Click on Save Group
		solo.clickOnView(solo.getView("activity_create_group_save_group"));
        solo.goBackToActivity("GetAccountActivity");
        assertTrue("GetAccountActivity is not found!", solo.waitForActivity("GetAccountActivity"));
	}
}
