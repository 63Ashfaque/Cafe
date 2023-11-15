package com.ashfaque.cafe.adapter;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ashfaque.cafe.R;
import com.ashfaque.cafe.model.TnTableModelClass;
import com.ashfaque.cafe.sqlite.DatabaseHelper;

import java.util.List;

// TableAdapter.java
public class OrderTableAdapter extends RecyclerView.Adapter<OrderTableAdapter.ViewHolder> {

	private List<TnTableModelClass> tableList;
	private DatabaseHelper dbHelper;

	public OrderTableAdapter(List<TnTableModelClass> tableList) {
		this.tableList = tableList;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.item_order_table, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		TnTableModelClass tableItem = tableList.get(position);
		holder.tableName.setText(tableItem.getTableTitle());

		if (tableItem.getTableStatus() > 0) {
			holder.constraintLayout.setBackgroundResource(R.color.red);
		} else {
			holder.constraintLayout.setBackgroundResource(R.color.white);
		}

		holder.constraintLayout.setOnClickListener(view ->
		{
			showDialogBox(view);
		});

	}

	String[] spinnerCount = { "1","2","3","4","5","6","7","10",};

	private void showDialogBox(View view) {
		dbHelper = new DatabaseHelper(view.getContext());
		// custom dialog
		final Dialog dialog = new Dialog(view.getContext());
		dialog.setContentView(R.layout.dialog_order_item);
		dialog.setCancelable(false);
		Button dialogButton = dialog.findViewById(R.id.dialogButtonOK);
		RecyclerView recyclerViewItemOrder = dialog.findViewById(R.id.recyclerViewItemOrder);
		ItemOrderTableAdapter tableAdapter = new ItemOrderTableAdapter(dbHelper.getAllOrderItem());
		recyclerViewItemOrder.setAdapter(tableAdapter);
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(v -> {
			dialog.dismiss();
			Toast.makeText(view.getContext(), "Dismissed..!!",Toast.LENGTH_SHORT).show();
		});


		dialog.show();
	}

	@Override
	public int getItemCount() {
		return tableList.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		TextView tableName;
		ConstraintLayout constraintLayout;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			tableName = itemView.findViewById(R.id.tableName);
			constraintLayout = itemView.findViewById(R.id.constraintLayout);
		}
	}
}
