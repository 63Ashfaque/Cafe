package com.ashfaque.cafe;

import static com.ashfaque.cafe.Utils.dbClose;
import static com.ashfaque.cafe.Utils.logD;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.ashfaque.cafe.adapter.MenuTableAdapter;
import com.ashfaque.cafe.adapter.OrderTableAdapter;
import com.ashfaque.cafe.adapter.SettingTableAdapter;
import com.ashfaque.cafe.sqlite.DatabaseHelper;

public class MenuSettingActivity extends AppCompatActivity {


	RecyclerView recyclerViewSetting;
	private DatabaseHelper dbHelper;
	MenuTableAdapter tableAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_setting);

		dbHelper = new DatabaseHelper(this);
		recyclerViewSetting=findViewById(R.id.recyclerViewSetting);
		tableAdapter = new MenuTableAdapter(dbHelper.getAllMenu());
		recyclerViewSetting.setAdapter(tableAdapter);

		findViewById(R.id.fabAddMenu).setOnClickListener(view ->{
			insertMenu();
			tableAdapter.updateList(dbHelper.getAllMenu());
			recyclerViewSetting.smoothScrollToPosition(tableAdapter.getItemCount()-1);
		});
	}


	private void insertMenu() {
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.COLUMN_MENU_TITLE, "Menu ");
		values.put(DatabaseHelper.COLUMN_MENU_DESC, "Desc ");
		values.put(DatabaseHelper.COLUMN_MENU_RATE, "10");

		// Insert the new row, returning the primary key value of the new row
		long newRowId = db.insert(DatabaseHelper.TABLE_MENU, null, values);

		// Log the result or perform further actions based on the insertion
		logD("MainActivity"+ "New customer inserted with ID: " + newRowId);

		// Don't forget to close the database
		dbClose(db);
	}
}