package com.compassites.materialdesign;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.compassites.materialdesign.fragment.CardViewListFragment;
import com.compassites.materialdesign.fragment.NavigationDrawerFragment;


public class MainActivity extends ActionBarActivity {

    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app_bar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);

        NavigationDrawerFragment navigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.nav_drawer);
        navigationDrawerFragment.setUp(R.id.nav_drawer,(DrawerLayout) findViewById(R.id.fragment_drawer_layout),toolbar);


        CardViewListFragment cardViewListFragment = new CardViewListFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, cardViewListFragment)
                    .addToBackStack(null)
                    .commit();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.search_voice_btn){
            startActivity(new Intent(getApplicationContext(),SubActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setToolbarVisibility(boolean visible, boolean animate){
        if(!animate) {
            toolbar.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
            return;
        }
        if(visible) {
            toolbar.animate().translationY(0)
                    .setDuration(500)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            toolbar.setVisibility(View.VISIBLE);
                            getSupportActionBar().show();
                        }
                    })
                    .start();
        }
        else {
            toolbar.animate().translationY(-toolbar.getBottom())
                    .setDuration(500)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            toolbar.setVisibility(View.INVISIBLE);
                            getSupportActionBar().hide();
                        }
                    }).start();
        }
    }


    /**
     * Hide the action bar and the bottom bar.
     */
    public void hideActionAndBottomBar() {
        if (getSupportActionBar() != null && getSupportActionBar().isShowing()) {
            setToolbarVisibility(false,true);
        }
    }

    /**
     * Show the action bar and the bottom bar.
     */
    public void showActionAndBottomBar() {
        if (getSupportActionBar() != null && !getSupportActionBar().isShowing()) {
            setToolbarVisibility(true,true);
        }
    }
}
