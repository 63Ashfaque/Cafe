package com.ashfaque.cafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.ashfaque.cafe.adapter.ItemOrderTableAdapter;
import com.ashfaque.cafe.sqlite.DatabaseHelper;

public class WaiterActivity extends AppCompatActivity {

	private DatabaseHelper dbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_waiter);
		dbHelper = new DatabaseHelper(this);
		RecyclerView recyclerViewItemOrder = findViewById(R.id.recyclerViewItemOrder);
		ItemOrderTableAdapter tableAdapter = new ItemOrderTableAdapter(dbHelper.getAllOrderItem());
		recyclerViewItemOrder.setAdapter(tableAdapter);

	}
}