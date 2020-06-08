package com.example.ad340_team_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private ProfileFragment profileFragment;
    private HostsFragment hostsFragment;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_layout);

        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tablayout);

        Bundle data = getIntent().getExtras();

        profileFragment = new ProfileFragment();
        hostsFragment = new HostsFragment();
        profileFragment.setArguments(data);

        Adapter adapter = new Adapter(getSupportFragmentManager(), getLifecycle());
        adapter.addFragment(profileFragment, getString(R.string.fragTitlePROFILE));
        adapter.addFragment(new HostsFragment(), getString(R.string.fragTitleHOSTS));
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position == 0) {
                tab.setText(R.string.fragTitlePROFILE);
            } else if (position == 1) {
                tab.setText(R.string.fragTitleHOSTS);
            }
        }).attach();
    }

    public String[] getExtraData() {
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        String[] data = new String[5];

        if (b != null) {
            if (b.containsKey(Constants.KEY_NAME)) {
                data[0] = b.getString(Constants.KEY_NAME);
            }
            if (b.containsKey(Constants.KEY_AGE)) {
                data[1] = b.getString(Constants.KEY_AGE);
            }
            if (b.containsKey(Constants.KEY_SIZE)) {
                data[2] = b.getString(Constants.KEY_SIZE);
            }
            if (b.containsKey(Constants.KEY_DESCRIPTION)) {
                data[3] = b.getString(Constants.KEY_DESCRIPTION);
            }
            if (b.containsKey(Constants.KEY_BREED)) {
                data[4] = b.getString(Constants.KEY_BREED);
            }
        }

        return data;
    }

    public class Adapter extends FragmentStateAdapter {

        private List<Fragment> fragments = new ArrayList<>();

        Adapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
        }

        @Override
        public int getItemCount() {
            return fragments.size();
        }

    }
}
