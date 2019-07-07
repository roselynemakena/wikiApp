package inc.nyenjes.wikiapp.activities.managers

import inc.nyenjes.wikiapp.activities.providers.ArticleDataProvider
import inc.nyenjes.wikiapp.activities.repositories.FavoritesRepository
import inc.nyenjes.wikiapp.activities.repositories.HistoryRepository
import models.WikiPage
import models.WikiResult

class WikiManager(
        private val provider: ArticleDataProvider,
        private val favoritesRepository: FavoritesRepository,
        private val historyRepository: HistoryRepository) {

    private var favoritesCache: ArrayList<WikiPage>? = null
    private var historyCache: ArrayList<WikiPage>? = null

    fun search(term: String, skip: Int, take: Int, handler: (result: WikiResult) -> Unit?) {
        return provider.search(term, skip, take, handler)
    }

    fun getRandom(take: Int, handler: (result: WikiResult) -> Unit?) {
        return provider.getRandom(take, handler)
    }

    fun getHistory(): ArrayList<WikiPage>? {
        if (historyCache == null)
            historyCache = historyRepository.getAllHistory()
        return historyCache
    }

    fun getFavorites(): ArrayList<WikiPage>? {
        if (favoritesCache == null)
            favoritesCache = favoritesRepository.getAllFavorites()
        return favoritesCache
    }

    fun addFavorites(page: WikiPage) {
        favoritesCache?.add(page)
        favoritesRepository.addFavorites(page)
    }

    fun removeFavorites(pageId: Int) {
        favoritesRepository.removeFavorites(pageId = pageId)
        favoritesCache = favoritesCache!!.filter { it.pageid != pageId } as ArrayList<WikiPage>
    }

    fun getIsFavorite(pageId : Int) {
        favoritesRepository.isArticleFavorite(pageId = pageId)

    }
}
