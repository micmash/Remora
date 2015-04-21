package com.mobileapp.jolono.remora.test;

import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by Noah on 4/20/2015.
 */
public class RemoveUserFromGroup extends ActivityInstrumentationTestCase2 {
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
    public RemoveUserFromGroup() throws ClassNotFoundException {
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
        //Wait for activity: 'com.mobileapp.jolono.remora.activity.GetAccountActivity
        assertTrue("GetAccountActivity is not found!", solo.waitForActivity("GetAccountActivity"));
        //click on events button
        solo.clickOnView(solo.getView("activity_get_account_events_button"));
        assertTrue("GetEventsFromAccountActivity is not found!", solo.waitForActivity("GetEventsFromAccount"));
        //click on a list member
        solo.clickOnText("robo's Test Event 2:33 5:36");
        assertTrue("GetEventActivity is not found!", solo.waitForActivity("GetEventActivity"));
        //click on add group button
        solo.clickOnView(solo.getView("fragment_event_header_add_group_button"));
        assertTrue("CreateGroupActivity is not found!", solo.waitForActivity("CreateGroupActivity"));
        //enter group name
        solo.clearEditText((android.widget.EditText) solo.getView("activity_create_group_name"));
        solo.enterText((android.widget.EditText) solo.getView("activity_create_group_name"), "roboto group");
        //enter group description
        solo.clearEditText((android.widget.EditText) solo.getView("activity_create_group_description"));
        solo.enterText((android.widget.EditText) solo.getView("activity_create_group_description"), "roboto's group");
        //click save group button
        solo.clickOnView(solo.getView("activity_create_group_save_group"));
        assertTrue("GetEventActivity is not found!", solo.waitForActivity("GetEventActivity"));
        //click on group created.
        solo.clickOnText("roboto group");
        assertTrue("GetGroupActivity is not found!", solo.waitForActivity("GetGroupActivity"));
        //click on remove me button.
        solo.clickOnView(solo.getView("activity_get_group_removeProfile"));
        assertTrue("GetAccountActivity is not found!", solo.waitForActivity("GetAccountActivity"));
        //click on events button
        solo.clickOnView(solo.getView("activity_get_account_events_button"));
        assertTrue("GetEventsFromAccountActivity is not found!", solo.waitForActivity("GetEventsFromAccount"));
        //click on a list member
        solo.clickOnText("robo's Test Event 2:33 5:36");
        assertTrue("GetEventActivity is not found!", solo.waitForActivity("GetEventActivity"));
        //click on group created
        solo.clickOnText("roboto group");
        assertTrue("GetGroupActivity is not found!", solo.waitForActivity("GetGroupActivity"));
        //check if user is not listed under members.
        boolean result = solo.searchText("Mr Robot 1001-01-01 m");
        assertTrue("User still in group!", !solo.searchText("Mr Robot 1001-01-01 m"));
    }
}
