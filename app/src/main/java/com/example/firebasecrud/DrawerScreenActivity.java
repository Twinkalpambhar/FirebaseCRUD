package com.example.firebasecrud;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.firebasecrud.databinding.ActivityDrawerScreenBinding;
import com.google.android.material.navigation.NavigationView;

public class DrawerScreenActivity extends AppCompatActivity
{
    private ActivityDrawerScreenBinding binding;
    private AppBarConfiguration mAppBarConfiguration;
    NavigationView navigationView;
    Toolbar toolbar;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDrawerScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);
        drawerLayout=findViewById(R.id.drawer_layout);
//        getSupportActionBar().show();
        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(DrawerScreenActivity.this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_home) {
                    addFragment(new Add_Product());
                }
                if (item.getItemId() == R.id.nav_gallery) {
                    addFragment(new View_Product());
                }
                if (item.getItemId() == R.id.nav_slideshow) {
                    addFragment(new Show_all_product_Fragment());
                }
                if (item.getItemId() == R.id.Tools) {
                    Intent intent = new Intent(DrawerScreenActivity.this, MainActivity.class);

//                    editor.putBoolean("Login",false);
//                    editor.commit();
                    startActivity(intent);
                    finish();
                }
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });
    }

    private void addFragment(Fragment fragment) {
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.nav_host_fragment_content_drawer_screen,fragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer_screen, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_drawer_screen);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}