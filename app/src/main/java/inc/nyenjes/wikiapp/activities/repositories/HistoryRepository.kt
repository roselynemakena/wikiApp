package inc.nyenjes.wikiapp.activities.repositories

import com.google.gson.Gson
import models.WikiPage
import models.WikiThumbnail
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.rowParser

class HistoryRepository (private val databaseOpenHelper: ArticleDatabaseOpenHelper) {
    private val TABLE_NAME = "history"
    fun addHistory (page: WikiPage) {
        databaseOpenHelper.use {
            insert(TABLE_NAME,
            "id" to page.pageid,
            "title" to page.title,
            "url" to page.fullurl,
            "thumbnail" to Gson().toJson(page.thumbnail)
            )
        }
    }

    fun removeHistory(pageId: Int) {
        databaseOpenHelper.use {
            delete(TABLE_NAME,
                    "id = {pageId}",
                    "pageId" to pageId
            )
        }
    }

    fun getAllHistory() : ArrayList<WikiPage> {
        var pages = ArrayList<WikiPage>()

        val articleRowParser = rowParser{
            id: Int, title: String, url: String, thumbnailJson: String ->
            val page = WikiPage()
            page.title = title
            page.pageid = id
            page.fullurl = url
            page.thumbnail = Gson().fromJson(thumbnailJson, WikiThumbnail::class.java)

            pages.add(page)
        }
        return pages
    }
}