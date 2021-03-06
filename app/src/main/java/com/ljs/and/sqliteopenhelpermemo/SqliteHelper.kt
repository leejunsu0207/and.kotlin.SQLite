package com.ljs.and.sqliteopenhelpermemo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SqliteHelper(context: Context, name: String, version: Int): SQLiteOpenHelper(context, name, null, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        Log.d("SQLiteHelper", "db")
        val create = "create table memo (" +
                "no integer primary key, " +
                "content text, " +
                "datetime integer" +
                ")"

        db?.execSQL(create)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun insertMemo(memo: Memo){
        val values = ContentValues()
        values.put("content", memo.content)
        values.put("datetime", memo.datetime)

        val wd = writableDatabase
        wd.insert("memo", null, values)
        wd.close()
    }

// 리젝티드 세마포 익스큐션
    fun selectMemo(): MutableList<Memo>{
        val list = mutableListOf<Memo>()

        val select = "select * from memo"
        val rd = readableDatabase
        val cursor = rd.rawQuery(select, null)

        while(cursor.moveToNext()){

            val noIdx = cursor.getColumnIndex("no")
            val no = cursor.getLong(noIdx)

            val contentIdx = cursor.getColumnIndex("content")
            val content = cursor.getString(contentIdx)

            val datetimeIdx = cursor.getColumnIndex("datetime")
            val datetime = cursor.getLong(datetimeIdx)

            Log.d("SQLiteHelper", no.toString())
            Log.d("SQLiteHelper", content.toString())
            Log.d("SQLiteHelper", datetime.toString())
            list.add(Memo(no, content, datetime))
        }
        cursor.close()
        rd.close()

        return list
    }

    fun updateMemo(memo: Memo){
        val values = ContentValues()
        values.put("content", memo.content)
        values.put("datetime", memo.datetime)

        val wd = writableDatabase
        wd.update("meme", values, "no = ${memo.no}", null)
        wd.close()
    }

    fun deleteMemo(memo: Memo){
        val delete = "delete from memo where no = ${memo.no}"
        val db = writableDatabase
        db.execSQL(delete)
        db.close()
    }



}

data class Memo(var no:Long?, var content: String, var datetime: Long)