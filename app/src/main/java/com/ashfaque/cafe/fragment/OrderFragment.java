package com.ashfaque.cafe.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.ashfaque.cafe.R;
import com.ashfaque.cafe.TableSettingActivity;
import com.ashfaque.cafe.adapter.OrderTableAdapter;
import com.ashfaque.cafe.sqlite.DatabaseHelper;

public class OrderFragment extends Fragment {

	public OrderFragment() {
		// Required empty public constructor
	}

	private DatabaseHelper dbHelper;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dbHelper = new DatabaseHelper(getContext());

	}

	RecyclerView recyclerViewOrder;
	TextView tvAddTable;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_order, container, false);
		recyclerViewOrder = view.findViewById(R.id.recyclerViewOrder);
		OrderTableAdapter tableAdapter = new OrderTableAdapter(dbHelper.getAllTableNumbers());
		recyclerViewOrder.setAdapter(tableAdapter);
		if (tableAdapter.getItemCount() == 0) {
			tvAddTable = view.findViewById(R.id.tvAddTable);
			tvAddTable.setVisibility(View.VISIBLE);
			tvAddTable.setOnClickListener(view1 ->
			{
				requireActivity().startActivity(new Intent(requireActivity(), TableSettingActivity.class));
			});
		}
		return view;
	}
}