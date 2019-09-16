package com.demo.csc214.socialmediaapp.view;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.demo.csc214.socialmediaapp.R;

public class SocialActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    int user_id;

    public final String USERID_KEY = "USER_ID_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        user_id = intent.getIntExtra(USERID_KEY, 0);

        Log.i("OnCreate ID", Integer.toString(user_id));

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View view) {
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
           // }
        //});

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.social, menu);
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

        return super.onOptionsItemSelected(item);
    }

    //Source: https://www.youtube.com/watch?v=ju837bQOBfg
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();

        Bundle bundle = new Bundle();
        bundle.putInt(USERID_KEY, user_id);

        Log.i("Current ID", Integer.toString(user_id));

        if (id == R.id.nav_news_feed) {
            NewsFeedFragment newsFragment = new NewsFeedFragment();
            newsFragment.setArguments(bundle);

            fragmentManager.beginTransaction().replace(R.id.content_frame, newsFragment).commit();
        } else if (id == R.id.nav_list_users) {
            ListUsersFragment usersFragment = new ListUsersFragment();
            usersFragment.setArguments(bundle);

            fragmentManager.beginTransaction().replace(R.id.content_frame, usersFragment).commit();
        } else if (id == R.id.nav_edit_profile) {
            EditProfileFragment profileFragment = new EditProfileFragment();
            profileFragment.setArguments(bundle);

            fragmentManager.beginTransaction().replace(R.id.content_frame, profileFragment).commit();
        } else if (id == R.id.nav_create_post) {
            CreatePostFragment postFragment = new CreatePostFragment();
            postFragment.setArguments(bundle);

            fragmentManager.beginTransaction().replace(R.id.content_frame, postFragment).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
