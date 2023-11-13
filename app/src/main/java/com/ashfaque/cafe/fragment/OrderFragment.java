package com.ashfaque.cafe.fragment;

import static com.ashfaque.cafe.Utils.logD;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashfaque.cafe.adapter.OrderTableAdapter;
import com.ashfaque.cafe.sqlite.DatabaseHelper;
import com.ashfaque.cafe.R;
import com.google.gson.Gson;

public class OrderFragment extends Fragment {

	public OrderFragment() {
		// Required empty public constructor
	}

	private DatabaseHelper dbHelper;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dbHelper = new DatabaseHelper(getContext());
		logD("dfgkjndgd "+new Gson().toJson(	dbHelper.getAllTableNumbers()));

	}

	RecyclerView recyclerViewOrder;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view= inflater.inflate(R.layout.fragment_order, container, false);
		recyclerViewOrder=view.findViewById(R.id.recyclerViewOrder);
		OrderTableAdapter tableAdapter = new OrderTableAdapter(dbHelper.getAllTableNumbers());
		recyclerViewOrder.setAdapter(tableAdapter);
		return view;
	}
}