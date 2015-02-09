package com.byoutline.pinafood.test;

import com.byoutline.pinafood.activities.PinnedFoodActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class Login extends ActivityInstrumentationTestCase2<PinnedFoodActivity> {
  	private Solo solo;
  	
  	public Login() {
		super(PinnedFoodActivity.class);
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
        //Wait for activity: 'com.byoutline.pinafood.activities.PinnedFoodActivity'
		solo.waitForActivity(com.byoutline.pinafood.activities.PinnedFoodActivity.class, 2000);
        //Set default small timeout to 125269 milliseconds
		Timeout.setSmallTimeout(125269);
        //Enter the text: 'test'
		solo.clearEditText((android.widget.EditText) solo.getView(com.byoutline.pinafood.R.id.username_et));
		solo.enterText((android.widget.EditText) solo.getView(com.byoutline.pinafood.R.id.username_et), "test");
        //Click on Login
		solo.clickOnView(solo.getView(com.byoutline.pinafood.R.id.sign_in_btn));
        //Take screenshot
        solo.takeScreenshot();
	}
}
