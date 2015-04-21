package com.mobileapp.jolono.remora.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.robotium.solo.Solo;

/**
 * Created by Logan on 4/20/15.
 */
public class DeleteGroup extends ActivityInstrumentationTestCase2{


    private Solo solo;

    private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.mobileapp.jolono.remora.activity.LoginActivity";

    private static Class<?> launcherActivityClass;

    static {
        try {
            launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public DeleteGroup() {
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
        solo.sleep(200);

        solo.clickOnView(solo.getView("activity_get_account_events_button"));
        solo.waitForActivity("GetEventsFromAccount", 2000);
        solo.searchText("robo's Test Event 2:33 5:36");
        solo.clickOnText("robo's Test Event 2:33 5:36");

        solo.waitForActivity("GetEventActivity", 2000);

        solo.clickOnView(solo.getView("fragment_event_header_add_group_button"));
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
        
        solo.clickOnText("robots group");

        
        solo.clickOnView(solo.getView("activity_get_group_delete"));
        
        assertFalse(solo.searchText("edited"));



    }

}
