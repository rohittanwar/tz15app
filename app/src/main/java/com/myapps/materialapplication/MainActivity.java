package com.myapps.materialapplication;

import android.app.Fragment;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
        // populate the navigation drawer
        mNavigationDrawerFragment.setUserData("Rohit", "TZ10287566", BitmapFactory.decodeResource(getResources(), R.drawable.tzlogo));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Fragment fragment;
        switch (position) {
            case 3: //home

                    fragment = new HomeFragment();
                getFragmentManager().beginTransaction().replace(R.id.container, fragment, HomeFragment.TAG).commit();
                break;
            case 1: //profile
                Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_LONG).show();
                    fragment = new ProfileFragment();
                getFragmentManager().beginTransaction().replace(R.id.container, fragment, HomeFragment.TAG).commit();
                break;
            case 2: //registration
                    fragment = new RegistrationFragment();
                getFragmentManager().beginTransaction().replace(R.id.container, fragment, HomeFragment.TAG).commit();
                break;
            case 0: //events
                    fragment = new EventsFragment();
                getFragmentManager().beginTransaction().replace(R.id.container, fragment, HomeFragment.TAG).commit();
                break;
            case 4: //workshops
                    fragment = new WorkshopsFragment();
                getFragmentManager().beginTransaction().replace(R.id.container, fragment, HomeFragment.TAG).commit();
                break;
            case 5: //Map //TODO
//                fragment = getFragmentManager().findFragmentByTag(EventsFragment.TAG);
//                if (fragment == null) {
//                    fragment = new EventsFragment();
//                }
//                getFragmentManager().beginTransaction().replace(R.id.container, fragment, EventsFragment.TAG).commit();
                break;
            case 6: //T-Shirts
                    fragment = new TShirtsFragment();
                getFragmentManager().beginTransaction().replace(R.id.container, fragment, HomeFragment.TAG).commit();
                break;
            case 7: //FAQ
                    fragment = new FAQFragment();
                getFragmentManager().beginTransaction().replace(R.id.container, fragment, HomeFragment.TAG).commit();
                break;
            case 8: //logout //TODO
//                fragment = getFragmentManager().findFragmentByTag(EventsFragment.TAG);
//                if (fragment == null) {
//                    fragment = new EventsFragment();
//                }
//                getFragmentManager().beginTransaction().replace(R.id.container, fragment, EventsFragment.TAG).commit();
                break;
        }
    }


    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}