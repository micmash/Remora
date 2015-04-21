package com.mobileapp.jolono.remora.test;

import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


@SuppressWarnings("rawtypes")
public class RegisterNewUser extends ActivityInstrumentationTestCase2 {
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
    public RegisterNewUser() throws ClassNotFoundException {
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
        //Click on Empty Text View
		solo.clickOnView(solo.getView("email"));
        //Click on Register
		solo.clickOnView(solo.getView("activity_login_register_button"));
        //Wait for activity: 'com.mobileapp.jolono.remora.activity.CreateAccountActivity'
		assertTrue("CreateAccountActivity is not found!", solo.waitForActivity("CreateAccountActivity"));
        //Click on Empty Text View
		solo.clickOnView(solo.getView("activity_create_account_email"));
        //Set default small timeout to 10775 milliseconds
		Timeout.setSmallTimeout(10775);
        //Enter the text: 'myfirsttest@test.com'
		solo.clearEditText((android.widget.EditText) solo.getView("activity_create_account_email"));
		solo.enterText((android.widget.EditText) solo.getView("activity_create_account_email"), "myfirsttest@test.com");
        //Click on Empty Text View
		solo.clickOnView(solo.getView("activity_create_account_password"));
        //Enter the text: 'tqywr1'
		solo.clearEditText((android.widget.EditText) solo.getView("activity_create_account_password"));
		solo.enterText((android.widget.EditText) solo.getView("activity_create_account_password"), "tqywr1");

		solo.clickOnView(solo.getView("activity_create_account_name"));
        solo.clearEditText((android.widget.EditText) solo.getView("activity_create_account_name"));
        solo.enterText((android.widget.EditText) solo.getView("activity_create_account_name"), "MICKBAGZZS");
        //Enter the text: 'test master flex'
		solo.clearEditText((android.widget.EditText) solo.getView("activity_create_account_name"));
		solo.enterText((android.widget.EditText) solo.getView("activity_create_account_name"), "test master flex");
        //Click on Empty Text View
		solo.clickOnView(solo.getView("activity_create_account_birthdate"));
        //Enter the text: '5/6/1987'
		solo.clearEditText((android.widget.EditText) solo.getView("activity_create_account_birthdate"));
		solo.enterText((android.widget.EditText) solo.getView("activity_create_account_birthdate"), "561987");
        //Click on Empty Text View
		solo.clickOnView(solo.getView("activity_create_account_gender"));
        //Enter the text: 'm'
		solo.clearEditText((android.widget.EditText) solo.getView("activity_create_account_gender"));
		solo.enterText((android.widget.EditText) solo.getView("activity_create_account_gender"), "m");
        //Click on create account
		solo.clickOnView(solo.getView("activity_create_account_accept_button"));
        //Wait for activity: 'com.mobileapp.jolono.remora.activity.GetAccountActivity'
		assertTrue("GetAccountActivity is not found!", solo.waitForActivity("GetAccountActivity"));
        //Set default small timeout to 16800 milliseconds
		Timeout.setSmallTimeout(16800);
        //Click on map
		solo.clickOnView(solo.getView("activity_get_account_map_button"));
        //Wait for activity: 'com.mobileapp.jolono.remora.activity.MapsActivity'
		assertTrue("MapsActivity is not found!", solo.waitForActivity("MapsActivity"));
	}
}
