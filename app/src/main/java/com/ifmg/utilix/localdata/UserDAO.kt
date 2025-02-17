package com.ifmg.utilix.localdata

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class UserDAO(context: Context) {
    private val dbHelper = DatabaseSQLite(context)

    // Função para registrar usuário
    fun registerUser(name: String, email: String, password: String): Boolean {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseContract.USER.COLUMN_NAME, name)
            put(DatabaseContract.USER.COLUMN_EMAIL, email)
            put(DatabaseContract.USER.COLUMN_PASSWORD, password)
        }

        val newRowId = db.insert(DatabaseContract.USER.TABLE_NAME, null, values)
        db.close()
        return newRowId != -1L
    }

    // Função para verificar login
    fun checkLogin(email: String, password: String): Boolean {
        val db = dbHelper.readableDatabase
        val selection = "${DatabaseContract.USER.COLUMN_EMAIL} = ? AND ${DatabaseContract.USER.COLUMN_PASSWORD} = ?"
        val selectionArgs = arrayOf(email, password)
        val cursor: Cursor = db.query(
            DatabaseContract.USER.TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null, null, null
        )

        val loginSuccess = cursor.count > 0
        cursor.close()
        db.close()
        return loginSuccess
    }
}
