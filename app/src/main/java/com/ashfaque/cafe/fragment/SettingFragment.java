package com.ashfaque.cafe.fragment;

import static com.ashfaque.cafe.Utils.dbClose;
import static com.ashfaque.cafe.Utils.logD;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ashfaque.cafe.R;
import com.ashfaque.cafe.sqlite.DatabaseHelper;

public class SettingFragment extends Fragment {



	public SettingFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	Button btnAddTable;
	private DatabaseHelper dbHelper;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_setting, container, false);
		btnAddTable=view.findViewById(R.id.btnAddTable);

		dbHelper = new DatabaseHelper(getContext());

		btnAddTable.setOnClickListener(view1 -> {
			insertTnTable("Table -1 ", 1);
		});
		return view;
	}

	private void insertTnTable(String mTnTitle, int mStatus) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.COLUMN_T_TITLE, mTnTitle);
		values.put(DatabaseHelper.COLUMN_T_STATUS, mStatus);

		// Insert the new row, returning the primary key value of the new row
		long newRowId = db.insert(DatabaseHelper.TABLE_T_NUMBER, null, values);

		// Log the result or perform further actions based on the insertion
		logD("MainActivity"+ "New customer inserted with ID: " + newRowId);

		// Don't forget to close the database
		dbClose(db);
	}
}