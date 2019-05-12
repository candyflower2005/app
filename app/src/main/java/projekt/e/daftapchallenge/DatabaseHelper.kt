package projekt.e.daftapchallenge

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, "db", null, 6) {

    override fun onCreate(db: SQLiteDatabase) {
        val sql = "CREATE TABLE results(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "score INTEGER NOT NULL," +
                "date TEXT NOT NULL);"
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS results")
        onCreate(db)
    }

    fun insertScore(score: Int, date: String) {
        val values = ContentValues()
        values.put("score", score)
        values.put("date", date)
        this.writableDatabase.insert("results", null, values)
    }

    fun topResults(): ArrayList<ResultModel> {
        val sql = "SELECT * FROM results ORDER BY score DESC LIMIT 5"
        val list = ArrayList<ResultModel>()
        this.writableDatabase.rawQuery(sql, null).use {
            while(it.moveToNext()) {
                val result = ResultModel()
                result.score = it.getInt(it.getColumnIndex("score"))
                result.date = it.getString(it.getColumnIndex("date"))
                list.add(result)
            }
        }
        return list
    }
}