package com.example.farmandroidapp

import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update

//Database class using Field entities
@Database(entities = [Field::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun FieldDao(): FieldDao
}

//Dao interface for interacting with database
@Dao
interface FieldDao {
    //Define SQL functions to use by DAO object
    @Query("SELECT * FROM Field ORDER BY title")
    suspend fun getAll(): MutableList<Field>
    @Query("SELECT * FROM Field WHERE id = :id")
    suspend fun getFieldByID(id : Int): Field?
    @Insert
    suspend fun insertAll(vararg fields: Field)
    @Update(entity = Field::class)
    suspend fun updateField(field : Field)
    @Delete
    suspend fun delete(field: Field)
}

//Table definition for Field Entities
@Entity
data class Field(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") var title: String,
//    @ColumnInfo(name = "category") var category: String,
//    @ColumnInfo(name = "duration") var duration: Int,
//    @ColumnInfo(name = "startTime") var startTime: Int
)