package com.ashfaque.cafe.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ashfaque.cafe.R;
import com.ashfaque.cafe.model.TnTableModelClass;

import java.util.List;

// TableAdapter.java
public class OrderTableAdapter extends RecyclerView.Adapter<OrderTableAdapter.ViewHolder> {

	private List<TnTableModelClass> tableList;

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

		if(tableItem.getTableStatus()>0)
		{
			holder.constraintLayout.setBackgroundResource(R.color.red);
		}else
		{	holder.constraintLayout.setBackgroundResource(R.color.white);

		}

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
