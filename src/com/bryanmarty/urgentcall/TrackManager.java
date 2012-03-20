package com.bryanmarty.urgentcall;


import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class TrackManager {
	
	private static boolean initialized = false;
	private static DatabaseManager dbManager;
	private static Context context_;
	private static ThreadPoolExecutor threadPool_;
	private static BlockingQueue<Runnable> queue_;
	
	public synchronized static boolean initialize(Context context) {
		if(initialized) {
			return initialized;
		}
		context_ = context;
		try {
			queue_ = new LinkedBlockingQueue<Runnable>();
			threadPool_ = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, queue_);
			dbManager = new DatabaseManager(context_);
			dbManager.createDataBase();
			dbManager.openDataBase();
			initialized = true;
		} catch (Exception e) {
			e.printStackTrace();
			initialized = false;
		}
		return initialized;
	}
	
	public synchronized static void shutdown() {
		if(!initialized) {
			return;
		}
		threadPool_.shutdown();
		try {
			threadPool_.awaitTermination(10L, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		dbManager.close();
		initialized = false;
	}
	
	public static Future<Long> addRule(final UrgentEntry rule) {
		TrackRequest<Long> request = new TrackRequest<Long>() {
			
			@Override
			public Long call() throws Exception {
				SQLiteDatabase db = getDatabase();
				if(db == null) {
					throw new SQLiteException("Database was null");
				}
				ContentValues values = new ContentValues();
				values.put("nickname",rule.getNickname());
				values.put("phone", rule.getPhoneNumber());
				return db.insert("rules", null, values);
			}
		};
		request.setDatabase(dbManager.getDatabase());
		return threadPool_.submit(request);
	}
	
	public static Future<Boolean> updateRule(final UrgentEntry rule) {
		TrackRequest<Boolean> request = new TrackRequest<Boolean>() {

			@Override
			public Boolean call() throws Exception {
				SQLiteDatabase db = getDatabase();
				ContentValues values = new ContentValues();
				values.put("nickname",rule.getNickname());
				values.put("phone", rule.getPhoneNumber());
				String whereClause = "rowid = ?";
				String[] whereArgs = { rule.getId().toString() };
				int result = db.update("rules", values, whereClause, whereArgs);
				return (result == 1 ? true : false);
			}
			
		};
		
		request.setDatabase(dbManager.getDatabase());
		return threadPool_.submit(request);
	}
	
	public static Future<LinkedList<UrgentEntry>> getRuleList() {
		TrackRequest<LinkedList<UrgentEntry>> request = new TrackRequest<LinkedList<UrgentEntry>>() {

			@Override
			public LinkedList<UrgentEntry> call() throws Exception {
				LinkedList<UrgentEntry> list = new LinkedList<UrgentEntry>();
				SQLiteDatabase db = getDatabase();
				String[] columns = { "rowid", "nickname","phone" };
				Cursor c = db.query("rules", columns, null, null, null, null, "nickname ASC");
				while (c.moveToNext()) {
					UrgentEntry en = new UrgentEntry();
					en.setId(c.getLong(c.getColumnIndex("rowid")));
					en.setNickname(c.getString(c.getColumnIndex("nickname")));
					en.setPhoneNumber(c.getString(c.getColumnIndex("phone")));
					list.add(en);
				}
				return list;
			}
		};
		
		request.setDatabase(dbManager.getDatabase());
		return threadPool_.submit(request);
	}
	
	
	
	

}
