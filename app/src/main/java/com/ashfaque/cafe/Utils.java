package com.ashfaque.cafe;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.intuit.sdp.BuildConfig;

public class Utils {

	public static void logD(String msg) {

			Log.d("Ashu", msg);

	}

	public static void dbClose(SQLiteDatabase db) {
		if (BuildConfig.DEBUG) {
			db.close();
		}

	}
}
