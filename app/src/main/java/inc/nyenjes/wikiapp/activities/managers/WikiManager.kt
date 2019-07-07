package inc.nyenjes.wikiapp.activities.managers

import inc.nyenjes.wikiapp.activities.providers.ArticleDataProvider
import inc.nyenjes.wikiapp.activities.repositories.FavoritesRepository
import inc.nyenjes.wikiapp.activities.repositories.HistoryRepository
import models.WikiPage

class WikiManager(
        private val provider: ArticleDataProvider,
        private val favoritesRepository: FavoritesRepository,
        private val historyRepository: HistoryRepository) {

    private var favoritesCache: ArrayList<WikiPage>? = null
    private var historyCache: ArrayList<WikiPage>? = null

}