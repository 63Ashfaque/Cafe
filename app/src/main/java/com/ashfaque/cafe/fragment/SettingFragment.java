package com.ashfaque.cafe.fragment;

import static com.ashfaque.cafe.Utils.dbClose;
import static com.ashfaque.cafe.Utils.logD;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ashfaque.cafe.MenuSettingActivity;
import com.ashfaque.cafe.R;
import com.ashfaque.cafe.TableSettingActivity;
import com.ashfaque.cafe.sqlite.DatabaseHelper;

public class SettingFragment extends Fragment {



	public SettingFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	private DatabaseHelper dbHelper;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_setting, container, false);
		dbHelper = new DatabaseHelper(getContext());

		view.findViewById(R.id.tvTableInfo).setOnClickListener(view1 -> requireActivity().startActivity(new Intent(requireActivity(), TableSettingActivity.class)));
		view.findViewById(R.id.tvMenuInfo).setOnClickListener(view1 ->
				requireActivity().startActivity(new Intent(requireActivity(), MenuSettingActivity.class)));
		return view;
	}

}