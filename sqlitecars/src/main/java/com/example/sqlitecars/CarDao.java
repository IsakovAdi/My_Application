package com.example.sqlitecars;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface CarDao {

    // Будет содержать методы для манипуляции над объектами
    // Create - Read - Upgrade - Delete

    @Insert
    public long addCar(Car car);

    @Update
    public void UpdateCar(Car car);

    @Delete
    public void deleteCar(Car car);

    // Read - Car - {id}
    // Read - List<Car>

    @Query("select * from cars")
    public List<Car> getAllCars();

    @Query("select * from cars where car_id ==:carId")
    public Car getCar (long carId);


}
