package com.ashfaque.cafe.adapter;

import static com.ashfaque.cafe.sqlite.DatabaseHelper.COLUMN_MENU_DESC;
import static com.ashfaque.cafe.sqlite.DatabaseHelper.COLUMN_MENU_ID;
import static com.ashfaque.cafe.sqlite.DatabaseHelper.COLUMN_MENU_RATE;
import static com.ashfaque.cafe.sqlite.DatabaseHelper.COLUMN_MENU_TITLE;
import static com.ashfaque.cafe.sqlite.DatabaseHelper.TABLE_MENU;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ashfaque.cafe.R;
import com.ashfaque.cafe.model.OrderModelClass;
import com.ashfaque.cafe.sqlite.DatabaseHelper;

import java.util.List;

public class ItemOrderTableAdapter extends RecyclerView.Adapter<ItemOrderTableAdapter.ViewHolder> {

	private List<OrderModelClass> tableList;
	private DatabaseHelper dbHelper;
	String tName = "Table --";

	public ItemOrderTableAdapter(List<OrderModelClass> tableList) {
		OrderModelClass tableItem = new OrderModelClass();
		this.tableList = tableList;
		this.tableList.add(tableItem);
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.item_list_order_table, parent, false);
		return new ViewHolder(view);
	}

	@SuppressLint("SetTextI18n")
	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
	//	OrderModelClass tableItem = tableList.get(position);
	//	holder.checkbox.setText("" + tableItem.getOrderId());
//		holder.editMenuDesc.setText("" + tableItem.getMenuDesc());
//		holder.editMenuRate.setText("" + tableItem.getMenuRate());
//		holder.tvMenuId.setText("" + tableItem.getMenuId());
//
//		holder.btnSet.setOnClickListener(view ->
//		{
//			dbHelper = new DatabaseHelper(view.getContext());
//
//			ContentValues values = new ContentValues();
//			values.put(COLUMN_MENU_TITLE, holder.editMenuName.getText().toString());
//			values.put(COLUMN_MENU_DESC, holder.editMenuDesc.getText().toString());
//			values.put(COLUMN_MENU_RATE, holder.editMenuRate.getText().toString());
//
//			dbHelper.updateInfo(TABLE_MENU, values, COLUMN_MENU_ID, holder.tvMenuId.getText().toString());
//		});
//
//		holder.btnDelete.setOnClickListener(view ->
//		{
//			dbHelper = new DatabaseHelper(view.getContext());
//			dbHelper.deleteRow(TABLE_MENU, COLUMN_MENU_ID, holder.tvMenuId.getText().toString());
//			tableList.remove(tableItem);
//			notifyDataSetChanged();
//		});


	}

	@Override
	public int getItemCount() {
		return tableList.size();
	}

	public void updateList(List<OrderModelClass> allTableNumbers) {
		this.tableList = allTableNumbers;
		notifyDataSetChanged();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		public ViewHolder(@NonNull View itemView) {
			super(itemView);

		}
	}

}
