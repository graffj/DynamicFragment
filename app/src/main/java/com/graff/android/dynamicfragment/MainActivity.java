package com.graff.android.dynamicfragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSwitch = (Button) findViewById(R.id.btn_switch);

        OnClickListener listener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction;
                Fragment1 frag1;
                Fragment2 frag2;

                /* Determine what fragment is visible, then replace with the other fragment */
                if (getFragmentManager().getBackStackEntryCount() == 0) {
                    Log.d("MainActivity", "No back stack available; switching to fragment 2");
                    /* No back stack; switch to Fragment2 */
                    fragmentTransaction = fragmentManager.beginTransaction();
                    frag2 = new Fragment2();
                    fragmentTransaction.replace(R.id.fragment_container, frag2, "Fragment2");
                    fragmentTransaction.addToBackStack("Fragment2");
                    fragmentTransaction.commit();
                }
                else {
                    String tag = getFragmentManager().getBackStackEntryAt(getFragmentManager().getBackStackEntryCount() - 1).getName();
                    Log.d("MainActivity", "Retrieved current fragment tag: " + tag);

                    if (tag.equals("Fragment1")) {
                        /* Switch to Fragment 2 */
                        fragmentTransaction = fragmentManager.beginTransaction();
                        frag2 = new Fragment2();
                        fragmentTransaction.replace(R.id.fragment_container, frag2, "Fragment2");
                        fragmentTransaction.addToBackStack("Fragment2");
                        fragmentTransaction.commit();
                    }
                    else if (tag.equals("Fragment2")) {
                        /* Switch to Fragment 1 */
                        fragmentTransaction = fragmentManager.beginTransaction();
                        frag1 = new Fragment1();
                        fragmentTransaction.replace(R.id.fragment_container, frag1, "Fragment1");
                        fragmentTransaction.addToBackStack("Fragment1");
                        fragmentTransaction.commit();
                    }
                    else
                        Log.d("MainActivity", "Unexpected fragment tag encountered: " + tag);

                }

            }
        };

        btnSwitch.setOnClickListener(listener);

        /* OnCreate default - add fragment 1 */
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment1 frag1 = new Fragment1();
        fragmentTransaction.add(R.id.fragment_container, frag1, "Fragment1");
        fragmentTransaction.commit();

    }
}
