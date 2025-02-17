package com.ifmg.utilix.localdata

class DatabaseContract {
    companion object {
        const val DATABASE_NAME = "utilix.db"
        const val DATABASE_VERSION = 1

        const val SQL_CREATE_TABLES = USER.SQL_CREATE
        const val SQL_DROP_TABLES = USER.SQL_DROP
    }

    object USER {
        const val TABLE_NAME = "users"

        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PASSWORD = "password"

        const val SQL_CREATE = """
            CREATE TABLE IF NOT EXISTS $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT NOT NULL,
                $COLUMN_EMAIL TEXT NOT NULL UNIQUE,
                $COLUMN_PASSWORD TEXT NOT NULL
            );
        """

        const val SQL_DROP = "DROP TABLE IF EXISTS $TABLE_NAME;"
    }
}
