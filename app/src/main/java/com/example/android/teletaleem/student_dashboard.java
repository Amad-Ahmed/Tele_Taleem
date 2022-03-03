package com.example.android.teletaleem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

public class student_dashboard extends AppCompatActivity {
    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        bottomNavigation = findViewById(R.id.StBottomNav);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_progress));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_assignment));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_menu));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_quiz));
        bottomNavigation.add(new MeowBottomNavigation.Model(5, R.drawable.ic_chat));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                // Initialize fragments
                Fragment fragment = null;
                switch (item.getId()){
                    case 1:
                        fragment = new StProgressFragment();
                        break;
                    case 2:
                        fragment = new StAssignmentFragment();
                        break;
                    case 3:
                        fragment = new StHomeFragment();
                        break;
                    case 4:
                        fragment = new StQuizFragment();
                        break;
                    case 5:
                        fragment = new StChatFragment();
                        break;
                }
                loadFragment(fragment);
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.StBottomNav,fragment)
                .commit();
    }
}