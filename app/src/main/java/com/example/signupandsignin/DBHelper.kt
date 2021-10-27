package com.example.signupandsignin

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(
    context: Context?,
    name: String?="Data",
    factory: SQLiteDatabase.CursorFactory?=null,
    version: Int=1,
    val tableName: String= "users"
) : SQLiteOpenHelper(context, name, factory, version) {

    private val sqLiteDatabase: SQLiteDatabase= writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $tableName (PK INTEGER PRIMARY KEY AUTOINCREMENT, UserName TEXT, PhoneNumber INTEGER, Location TEXT, Password TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $tableName")
        onCreate(db)
    }

    fun addUser(data: Data): Long{
        val contentValue= ContentValues()
        contentValue.put("UserName",data.name)
        contentValue.put("PhoneNumber",data.phoneNumber)
        contentValue.put("Location",data.location)
        contentValue.put("Password",data.password)
        return sqLiteDatabase.insert(tableName, null, contentValue)
    }
    fun checkLogIn(name: String, password: String): Int{
        try{
            val cursor: Cursor= sqLiteDatabase.query(tableName,null,"UserName=? AND Password=?", arrayOf(name,password), null, null, null)
            Log.d("MyData","$cursor")
            cursor.moveToFirst()
            if (cursor.count>0){
                return cursor.getInt(cursor.getColumnIndexOrThrow("PK"))
            }
            return -1
        }
        catch (e:Exception){
            Log.d("MyData",e.toString())
            return -1
        }
    }
    fun getDetails(pk: Int): Data{
        val selectQuery = "SELECT * FROM $tableName WHERE PK= $pk "
        var cursor: Cursor? = null
        try {
            cursor = sqLiteDatabase.rawQuery(selectQuery, null)
            cursor.moveToFirst()
            val name= cursor.getString(cursor.getColumnIndexOrThrow("UserName"))
            val phone= cursor.getInt(cursor.getColumnIndexOrThrow("PhoneNumber"))
            val location= cursor.getString(cursor.getColumnIndexOrThrow("Location"))
            return Data(name, phone, location)
        } catch (e: SQLiteException){
            return Data("",0,"")
        }
    }
}