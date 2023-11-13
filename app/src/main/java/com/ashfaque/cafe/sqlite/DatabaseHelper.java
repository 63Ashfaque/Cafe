package com.ashfaque.cafe.sqlite;


import static com.ashfaque.cafe.Utils.dbClose;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ashfaque.cafe.model.TnTableModelClass;

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
	public static final String COLUMN_ORDER_T_TITLE = "t_title";
	public static final String COLUMN_ORDER_MENU_TITLE = "m_title";
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
				+ COLUMN_CUSTOMER_PAID_AMOUNT + " REAL)";
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
				+ COLUMN_ORDER_T_TITLE + " TEXT, "
				+ COLUMN_ORDER_MENU_TITLE + " TEXT, "
				+ COLUMN_ORDER_QUANTITY + " INTEGER, "
				+ COLUMN_ORDER_STATUS + " INTEGER)";
		db.execSQL(createOrderTable);

	}


	// Method to get all table numbers
	@SuppressLint("Range")
	public List<TnTableModelClass> getAllTableNumbers() {
		List<TnTableModelClass> tableNumbers = new ArrayList<>();

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_T_NUMBER, new String[]{COLUMN_T_TITLE,COLUMN_T_STATUS}, null, null, null, null, null);

		if (cursor != null && cursor.moveToFirst()) {
			do {
				TnTableModelClass modelClass=new TnTableModelClass();
				 modelClass.setTableTitle(cursor.getString(cursor.getColumnIndex(COLUMN_T_TITLE)));
				 modelClass.setTableStatus(cursor.getInt(cursor.getColumnIndex(COLUMN_T_STATUS)));

				tableNumbers.add(modelClass);
			} while (cursor.moveToNext());

			// Close the cursor to avoid memory leaks
			cursor.close();
		}

		// Close the database
		dbClose(db);

		return tableNumbers;
	}
}
