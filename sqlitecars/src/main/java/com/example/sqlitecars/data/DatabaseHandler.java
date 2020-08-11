package com.example.sqlitecars.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.autofill.AutofillId;

import androidx.annotation.Nullable;

import com.example.sqlitecars.Car;
import com.example.sqlitecars.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler  extends SQLiteOpenHelper {
    public DatabaseHandler(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CARS_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY,"
                + Util.KEY_NAME + " TEXT,"
                + Util.KEY_PRICE + " TEXT" + ")";

        db.execSQL(CREATE_CARS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);
        onCreate(db);
    }

    public List<Car> getAllCars(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Car> carsList = new ArrayList<>();

        String selectAllCars = "SELECT * FROM " +Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAllCars, null);  // здесь идет добавление всех существующих машин

        if (cursor.moveToFirst()){
            do {
                Car car =new Car();
                car.setId(Integer.parseInt(cursor.getString(0)));
                car.setName(cursor.getString(1));
                car.setPrice(cursor.getString(2));

                carsList.add(car);

            }
            while (cursor.moveToNext());
        }
        return carsList;
    }

    public int updateCar(Car car){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_NAME, car.getName());
        contentValues.put(Util.KEY_PRICE, car.getPrice());

        return db.update(Util.TABLE_NAME, contentValues, Util.KEY_ID + "=?",
                new String[]{String.valueOf(car.getId())});
    }

    public void deleteCar(Car car){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TABLE_NAME,  Util.KEY_ID+ "=?", new String[]{String.valueOf(car.getId())});
        db.close();
    }
}
