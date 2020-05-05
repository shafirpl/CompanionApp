package com.example.companionandroid;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.companionandroid.fragments.CustomFragment;
import com.example.companionandroid.fragments.DashboardFragment;
import com.example.companionandroid.fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public static int something;


    /*
     * https://developer.android.com/reference/android/support/design/widget/BottomNavigationView#setOnNavigationItemSelectedListener(android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener)
     * Basically this function runs whenever we select an item on the botton navigation bar
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fragment_layout);

        // initializing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // At first we wanna start with home fragment and then replace it with
        // whatever fragment is selected
        Fragment fragment = new HomeFragment();
        fragmentTransaction.add(R.id.fragment_layout, fragment);
        fragmentTransaction.commit();

        // start listening to bottom navigation bar
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        // we need to connect it to a function so that when we select the item, that thing runs
        /*
         * basically this is a listenr listening for item selection, and when we select an item, it run some
         * code, the code runs is the object navlistener refer to
         */
        bottomNav.setOnNavigationItemSelectedListener(navlistener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navlistener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            /*
             * this part is kinda self explanatory, we get the menu item id to figure out which
             * item was selected, and then just initialize thisFragment with appropriate fragment
             * and fill the frame layout (id:fragment_layout) wuth thisFragment
             */
            Fragment thisFragment = null;
            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    thisFragment = new HomeFragment();
                    Log.i("fragment","Home");
                    break;
                case R.id.navigation_dashboard:
                    thisFragment = new DashboardFragment();
                    Log.i("fragment","dashboard");
                    break;

                case R.id.navigation_custom:
                    thisFragment = new CustomFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, thisFragment).commit();

            return true;
        }
    };



}
