package inc.nyenjes.wikiapp.activities.repositories

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Parcelable
import org.jetbrains.anko.db.INTEGER
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper
import org.jetbrains.anko.db.TEXT
import org.jetbrains.anko.db.createTable

class ArticleDatabaseOpenHelper(context: Context)
    : ManagedSQLiteOpenHelper(context, "ArticlesDatabase.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        //Define schema
        db?.createTable("favorites", true,
                "id" to INTEGER,
                "title" to TEXT,
                "url" to TEXT,
                "thumbnail" to TEXT
        )

        db?.createTable("history", true,
                "id" to INTEGER,
                "title" to TEXT,
                "url" to TEXT,
                "thumbnail" to TEXT
        )
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}