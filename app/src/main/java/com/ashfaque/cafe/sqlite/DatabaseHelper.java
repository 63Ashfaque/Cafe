package com.ashfaque.cafe.sqlite;


import static com.ashfaque.cafe.Utils.dbClose;
import static com.ashfaque.cafe.Utils.logD;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ashfaque.cafe.Utils;
import com.ashfaque.cafe.model.MenuModelClass;
import com.ashfaque.cafe.model.OrderModelClass;
import com.ashfaque.cafe.model.TnTableModelClass;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Upgrade database if needed
	}

	private static final String DATABASE_NAME = "cafe.db";
	private static final int DATABASE_VERSION = 1;



	//////////// Customer table
	public static final String TABLE_CUSTOMER = "customer_tabel";
	public static final String COLUMN_CUSTOMER_ID = "id";
	public static final String COLUMN_CUSTOMER_NAME = "name";
	public static final String COLUMN_CUSTOMER_MOBILE = "mobile";
	public static final String COLUMN_CUSTOMER_PAID_AMOUNT = "paid_amount";
	private static final String COLUMN_CUSTOMER_DATE = "date";



	/////////////// Table number table/////////////
	public static final String TABLE_T_NUMBER = "t_number_table";
	public static final String COLUMN_T_ID = "id";
	public static final String COLUMN_T_TITLE = "t_title";
	public static final String COLUMN_T_STATUS = "t_status";




	// Menu table
	public static final String TABLE_MENU = "menu_table";
	public static final String COLUMN_MENU_ID = "id";
	public static final String COLUMN_MENU_TITLE = "m_title";
	public static final String COLUMN_MENU_RATE = "m_rate";
	public static final String COLUMN_MENU_DESC = "m_desc";



	////////////// Order table//////////////
	public static final String TABLE_ORDER = "order_table";
	public static final String COLUMN_ORDER_ID = "id";
	public static final String COLUMN_ORDER_T_ID = "t_id";
	public static final String COLUMN_ORDER_MENU_ID = "m_id";
	public static final String COLUMN_ORDER_QUANTITY = "o_quantity";
	public static final String COLUMN_ORDER_STATUS = "o_status";



	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Create tables

		// Create Customer table
		String createCustomerTable = "CREATE TABLE " + TABLE_CUSTOMER + " ("
				+ COLUMN_CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ COLUMN_CUSTOMER_NAME + " TEXT, "
				+ COLUMN_CUSTOMER_MOBILE + " TEXT, "
				+ COLUMN_CUSTOMER_PAID_AMOUNT + " REAL,"
				+ COLUMN_CUSTOMER_DATE + " TEXT)";
		db.execSQL(createCustomerTable);

		// Create TABLE_T_NUMBER table
		String createTnTable = "CREATE TABLE " + TABLE_T_NUMBER + " ("
				+ COLUMN_T_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ COLUMN_T_TITLE + " TEXT, "
				+ COLUMN_T_STATUS + " INTEGER)";
		db.execSQL(createTnTable);

		// Create TABLE_MENU table
		String createMenuTable = "CREATE TABLE " + TABLE_MENU + " ("
				+ COLUMN_MENU_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ COLUMN_MENU_TITLE + " TEXT, "
				+ COLUMN_MENU_DESC + " TEXT, "
				+ COLUMN_MENU_RATE + " REAL)";
		db.execSQL(createMenuTable);

		// Create TABLE_ORDER table
		String createOrderTable = "CREATE TABLE " + TABLE_ORDER + " ("
				+ COLUMN_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ COLUMN_ORDER_T_ID+ " INTEGER, "
				+ COLUMN_ORDER_MENU_ID + " INTEGER, "
				+ COLUMN_ORDER_QUANTITY + " INTEGER, "
				+ COLUMN_ORDER_STATUS + " INTEGER)";
		db.execSQL(createOrderTable);

	}


	// Method to get all table numbers
	@SuppressLint("Range")
	public List<TnTableModelClass> getAllTableNumbers() {
		List<TnTableModelClass> tableNumbers = new ArrayList<>();

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_T_NUMBER, null, null, null, null, null, null);

		if (cursor != null && cursor.moveToFirst()) {
			do {
				TnTableModelClass modelClass=new TnTableModelClass();
				 modelClass.setTableId(cursor.getInt(cursor.getColumnIndex(COLUMN_T_ID)));
				 modelClass.setTableTitle(cursor.getString(cursor.getColumnIndex(COLUMN_T_TITLE)));
				 modelClass.setTableStatus(cursor.getInt(cursor.getColumnIndex(COLUMN_T_STATUS)));
				tableNumbers.add(modelClass);
			} while (cursor.moveToNext());

			// Close the cursor to avoid memory leaks
			cursor.close();
		}

		// Close the database
		dbClose(db);
		Utils.logD("getAllTableNumbers "+new Gson().toJson(tableNumbers));
		return tableNumbers;
	}



	@SuppressLint("Range")
	public List<MenuModelClass> getAllMenu() {
		List<MenuModelClass> tableNumbers = new ArrayList<>();

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_MENU, null, null, null, null, null, null);

		if (cursor != null && cursor.moveToFirst()) {
			do {
				MenuModelClass modelClass=new MenuModelClass();
				modelClass.setMenuId(cursor.getInt(cursor.getColumnIndex(COLUMN_MENU_ID)));
				modelClass.setMenuTitle(cursor.getString(cursor.getColumnIndex(COLUMN_MENU_TITLE)));
				modelClass.setMenuDesc(cursor.getString(cursor.getColumnIndex(COLUMN_MENU_DESC)));
				modelClass.setMenuRate(cursor.getFloat(cursor.getColumnIndex(COLUMN_MENU_RATE)));
				tableNumbers.add(modelClass);
			} while (cursor.moveToNext());

			// Close the cursor to avoid memory leaks
			cursor.close();
		}

		// Close the database
		dbClose(db);
		Utils.logD("getAllMenu "+new Gson().toJson(tableNumbers));
		return tableNumbers;
	}

	@SuppressLint("Range")
	public List<OrderModelClass> getAllOrderItem() {
		List<OrderModelClass> tableNumbers = new ArrayList<>();

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_ORDER, null, null, null, null, null, null);

		if (cursor != null && cursor.moveToFirst()) {
			do {
				OrderModelClass modelClass=new OrderModelClass();
				modelClass.setOrderId(cursor.getInt(cursor.getColumnIndex(COLUMN_ORDER_ID)));
				modelClass.setOrderTableId(cursor.getInt(cursor.getColumnIndex(COLUMN_ORDER_T_ID)));
				modelClass.setOrderMenuId(cursor.getInt(cursor.getColumnIndex(COLUMN_ORDER_MENU_ID)));
				modelClass.setOrderQuantity(cursor.getInt(cursor.getColumnIndex(COLUMN_ORDER_QUANTITY)));
				modelClass.setOrderStatus(cursor.getInt(cursor.getColumnIndex(COLUMN_ORDER_STATUS)));
				tableNumbers.add(modelClass);
			} while (cursor.moveToNext());

			// Close the cursor to avoid memory leaks
			cursor.close();
		}

		// Close the database
		dbClose(db);
		Utils.logD("getAllOrderItem "+new Gson().toJson(tableNumbers));
		return tableNumbers;
	}

	//insert All Table information
	public void insertInfo(String tableName,ContentValues values ) {
		SQLiteDatabase db = this.getWritableDatabase();

		// Insert the new row, returning the primary key value of the new row
		long newRowId = db.insert(tableName, null, values);

		// Log the result or perform further actions based on the insertion
		logD("MainActivity"+ "New customer inserted with ID: " + newRowId);

		// Don't forget to close the database
		dbClose(db);
	}



	//Update All Table information
	public void updateInfo(String tableName,ContentValues values,String tableColumnName,String tableId ) {
		SQLiteDatabase db = this.getWritableDatabase();

		// Define the WHERE clause to identify the specific table number
		String whereClause = tableColumnName + "=?";
		String[] whereArgs = {tableId};

		// Update the table status
		db.update(tableName, values, whereClause, whereArgs);

		Utils.logD("updateInfo "+db.getPath());
		// Close the database
		dbClose(db);
	}


	public void deleteRow(String tableName, String tableColumnName,String tableId) {
		SQLiteDatabase db = this.getWritableDatabase();

		// Define the WHERE clause to identify the specific table number
		String whereClause = tableColumnName + "=?";
		String[] whereArgs = {tableId};

		// Delete the row
		db.delete(tableName, whereClause, whereArgs);

		// Close the database
		dbClose(db);
	}
}
