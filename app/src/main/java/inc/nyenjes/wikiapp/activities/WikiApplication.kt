package inc.nyenjes.wikiapp.activities

import android.app.Application
import inc.nyenjes.wikiapp.activities.managers.WikiManager
import inc.nyenjes.wikiapp.activities.providers.ArticleDataProvider
import inc.nyenjes.wikiapp.activities.repositories.ArticleDatabaseOpenHelper
import inc.nyenjes.wikiapp.activities.repositories.FavoritesRepository
import inc.nyenjes.wikiapp.activities.repositories.HistoryRepository

class WikiApplication : Application() {
    var dbHelper: ArticleDatabaseOpenHelper? = null
    var favoritesRepository: FavoritesRepository? = null
    var historyRepository: HistoryRepository? = null
    var wikiProvider: ArticleDataProvider? = null
    var wikiManager: WikiManager? = null
        private set


    override fun onCreate() {
        super.onCreate()

        dbHelper = ArticleDatabaseOpenHelper(applicationContext)

        favoritesRepository = FavoritesRepository(dbHelper!!)
        historyRepository = HistoryRepository(dbHelper!!)

        wikiProvider = ArticleDataProvider()
        wikiManager = WikiManager(wikiProvider!!, favoritesRepository!!, historyRepository!!)
    }
}
