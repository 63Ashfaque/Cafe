package com.ashfaque.cafe;

import static com.ashfaque.cafe.sqlite.DatabaseHelper.COLUMN_T_STATUS;
import static com.ashfaque.cafe.sqlite.DatabaseHelper.COLUMN_T_TITLE;
import static com.ashfaque.cafe.sqlite.DatabaseHelper.TABLE_T_NUMBER;

import android.content.ContentValues;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ashfaque.cafe.adapter.SettingTableAdapter;
import com.ashfaque.cafe.sqlite.DatabaseHelper;

public class TableSettingActivity extends AppCompatActivity {


	RecyclerView recyclerViewSetting;
	private DatabaseHelper dbHelper;
	SettingTableAdapter tableAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_table_settting);
		dbHelper = new DatabaseHelper(this);
		recyclerViewSetting = findViewById(R.id.recyclerViewSetting);
		tableAdapter = new SettingTableAdapter(dbHelper.getAllTableNumbers());
		recyclerViewSetting.setAdapter(tableAdapter);

		findViewById(R.id.fabAddTable).setOnClickListener(view -> {
			//insert dummy data
			ContentValues values = new ContentValues();
			values.put(COLUMN_T_TITLE, "Table ");
			values.put(COLUMN_T_STATUS, 0);
			dbHelper.insertInfo(TABLE_T_NUMBER, values);

			tableAdapter.updateList(dbHelper.getAllTableNumbers());
			recyclerViewSetting.smoothScrollToPosition(tableAdapter.getItemCount() - 1);
		});
	}

}