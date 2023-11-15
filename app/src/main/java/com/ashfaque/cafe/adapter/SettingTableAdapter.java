package com.ashfaque.cafe.adapter;

import static com.ashfaque.cafe.sqlite.DatabaseHelper.COLUMN_T_ID;
import static com.ashfaque.cafe.sqlite.DatabaseHelper.COLUMN_T_TITLE;
import static com.ashfaque.cafe.sqlite.DatabaseHelper.TABLE_T_NUMBER;

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
import com.ashfaque.cafe.model.TnTableModelClass;
import com.ashfaque.cafe.sqlite.DatabaseHelper;

import java.util.List;

public class SettingTableAdapter extends RecyclerView.Adapter<SettingTableAdapter.ViewHolder> {

	private List<TnTableModelClass> tableList;
	private DatabaseHelper dbHelper;
	String tName = "Table --";

	public SettingTableAdapter(List<TnTableModelClass> tableList) {
		this.tableList = tableList;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.item_setting_table, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		TnTableModelClass tableItem = tableList.get(position);
		holder.editTableName.setText(tableItem.getTableTitle());
		holder.tvTableId.setText("" + tableItem.getTableId());
		if (holder.editTableName.getText().length() > 0) {
			tName = holder.editTableName.getText().toString();
		}
		holder.btnSet.setOnClickListener(view ->
		{
			dbHelper = new DatabaseHelper(view.getContext());
			ContentValues values = new ContentValues();
			values.put(COLUMN_T_TITLE, holder.editTableName.getText().toString());
			dbHelper.updateInfo(TABLE_T_NUMBER, values, COLUMN_T_ID, holder.tvTableId.getText().toString());

		});

		holder.btnDelete.setOnClickListener(view ->
		{
			dbHelper = new DatabaseHelper(view.getContext());
			dbHelper.deleteRow(TABLE_T_NUMBER, COLUMN_T_ID, holder.tvTableId.getText().toString());
			tableList.remove(tableItem);
			notifyDataSetChanged();
		});


	}

	@Override
	public int getItemCount() {
		return tableList.size();
	}

	public void updateList(List<TnTableModelClass> allTableNumbers) {
		this.tableList = allTableNumbers;
		notifyDataSetChanged();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		EditText editTableName;
		TextView tvTableId;
		Button btnSet;
		ImageView btnDelete;
		ConstraintLayout constraintLayout;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			editTableName = itemView.findViewById(R.id.editTableName);
			tvTableId = itemView.findViewById(R.id.tvTableId);
			btnSet = itemView.findViewById(R.id.btnSet);
			btnDelete = itemView.findViewById(R.id.btnDelete);
			constraintLayout = itemView.findViewById(R.id.constraintLayout);
		}
	}

}
