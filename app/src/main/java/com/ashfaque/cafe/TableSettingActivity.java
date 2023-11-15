package com.ashfaque.cafe;

import static com.ashfaque.cafe.Utils.dbClose;
import static com.ashfaque.cafe.Utils.logD;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.ashfaque.cafe.adapter.SettingTableAdapter;
import com.ashfaque.cafe.sqlite.DatabaseHelper;

public class TableSettingActivity extends AppCompatActivity{


	RecyclerView recyclerViewSetting;
	private DatabaseHelper dbHelper;
	SettingTableAdapter tableAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_table_settting);
		dbHelper = new DatabaseHelper(this);
		recyclerViewSetting=findViewById(R.id.recyclerViewSetting);
		tableAdapter = new SettingTableAdapter(dbHelper.getAllTableNumbers());
		recyclerViewSetting.setAdapter(tableAdapter);

		findViewById(R.id.fabAddTable).setOnClickListener(view ->{
			insertTnTable();
			tableAdapter.updateList(dbHelper.getAllTableNumbers());
			recyclerViewSetting.smoothScrollToPosition(tableAdapter.getItemCount()-1);
		});
	}

	private void insertTnTable() {
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.COLUMN_T_TITLE, "Table ");
		values.put(DatabaseHelper.COLUMN_T_STATUS, 1);

		// Insert the new row, returning the primary key value of the new row
		long newRowId = db.insert(DatabaseHelper.TABLE_T_NUMBER, null, values);

		// Log the result or perform further actions based on the insertion
		logD("MainActivity"+ "New customer inserted with ID: " + newRowId);

		// Don't forget to close the database
		dbClose(db);
	}

}