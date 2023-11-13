package com.ashfaque.cafe;

import static com.ashfaque.cafe.Utils.logD;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.ashfaque.cafe.adapter.MyAdapter;
import com.ashfaque.cafe.sqlite.DatabaseHelper;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {


	private DatabaseHelper dbHelper;
	TabLayout tabLayout;
	ViewPager viewPager;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//dbHelper = new DatabaseHelper(this);

		tabLayout=(TabLayout)findViewById(R.id.tabLayout);
		viewPager=(ViewPager)findViewById(R.id.viewPager);

		tabLayout.addTab(tabLayout.newTab().setText("Order"));
		tabLayout.addTab(tabLayout.newTab().setText("History"));
		tabLayout.addTab(tabLayout.newTab().setText("Setting"));
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

		final MyAdapter adapter = new MyAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
		viewPager.setAdapter(adapter);

		viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

		tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				viewPager.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {

			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {

			}
		});

	}


}