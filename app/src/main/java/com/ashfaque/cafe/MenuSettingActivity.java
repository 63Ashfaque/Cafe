package com.ashfaque.cafe;

import static com.ashfaque.cafe.Utils.dbClose;
import static com.ashfaque.cafe.Utils.logD;
import static com.ashfaque.cafe.sqlite.DatabaseHelper.COLUMN_MENU_DESC;
import static com.ashfaque.cafe.sqlite.DatabaseHelper.COLUMN_MENU_RATE;
import static com.ashfaque.cafe.sqlite.DatabaseHelper.COLUMN_MENU_TITLE;
import static com.ashfaque.cafe.sqlite.DatabaseHelper.TABLE_MENU;

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
			//insert dummy data
			ContentValues values = new ContentValues();
			values.put(COLUMN_MENU_TITLE, "Menu ");
			values.put(COLUMN_MENU_DESC, "Desc ");
			values.put(COLUMN_MENU_RATE, "10");
			dbHelper.insertInfo(TABLE_MENU,values);

			tableAdapter.updateList(dbHelper.getAllMenu());
			recyclerViewSetting.smoothScrollToPosition(tableAdapter.getItemCount()-1);
		});
	}
}