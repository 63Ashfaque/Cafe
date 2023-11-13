package com.ashfaque.cafe.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ashfaque.cafe.fragment.HistoryFragment;
import com.ashfaque.cafe.fragment.OrderFragment;
import com.ashfaque.cafe.fragment.SettingFragment;

public class MyAdapter extends FragmentPagerAdapter {

	private Context myContext;
	int totalTabs;

	public MyAdapter(Context context, FragmentManager fm, int totalTabs) {
		super(fm);
		myContext = context;
		this.totalTabs = totalTabs;
	}

	// this is for fragment tabs
	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				OrderFragment orderFragment = new OrderFragment();
				return orderFragment;
			case 1:
				HistoryFragment historyFragment = new HistoryFragment();
				return historyFragment;
			case 2:
				SettingFragment settingFragment = new SettingFragment();
				return settingFragment;
			default:
				return null;
		}
	}
	// this counts total number of tabs
	@Override
	public int getCount() {
		return totalTabs;
	}
}
