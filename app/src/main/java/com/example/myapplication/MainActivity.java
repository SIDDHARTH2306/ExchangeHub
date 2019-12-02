package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static FragmentManager fragmentManager;
    public void onClick(View view){
        /*if (view.getId() == R.id.frameContainer) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }*/
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout backgroundFrameLayout = (FrameLayout) findViewById(R.id.frameContainer);
        backgroundFrameLayout.setOnClickListener(this);
        fragmentManager = getSupportFragmentManager();
        CommonUtil.getInstance().setAppContext(getApplicationContext());
        CommonUtil.getInstance().setMainActionBar(getSupportActionBar());
        // If savedinstnacestate is null then replace login fragment
        if (savedInstanceState == null) {

            if( SaveSharedPreference.getLoggedStatus(getApplicationContext()) == false) {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.frameContainer, new Login_Fragment(),
                                CommonUtil.Login_Fragment).commit();
            }
            else
            {
                CommonUtil.getInstance().showHomePageFragment();
            }
        }

        // On close icon click finish activity
		/*findViewById(R.id.close_activity).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						finish();

					}
				});*/

    }

    // Replace Login Fragment with animation
    protected void replaceLoginFragment() {
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.frameContainer, new Login_Fragment(),
                        CommonUtil.Login_Fragment).commit();
    }

    @Override
    public void onBackPressed() {

        // Find the tag of signup and forgot password fragment
        Fragment SignUp_Fragment = fragmentManager
                .findFragmentByTag(CommonUtil.SignUp_Fragment);
        Fragment ForgotPassword_Fragment = fragmentManager
                .findFragmentByTag(CommonUtil.ForgotPassword_Fragment);

        // Check if both are null or not
        // If both are not null then replace login fragment else do backpressed
        // task

        if (SignUp_Fragment != null)
            replaceLoginFragment();
        else if (ForgotPassword_Fragment != null)
            replaceLoginFragment();
        else
            super.onBackPressed();
    }

    public static FragmentManager getMainFragmentManager()
    {
        return fragmentManager;
    }

    public Context getMainApplicationContext()
    {
        return getApplicationContext();
    }

    public ActionBar getMainActionBar() {
        return getSupportActionBar();
    }


}
