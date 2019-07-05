package models

class WikiPage(
        val pageId: Int? = null,
        val title: String? = null,
        val fullUrl: String? = null,
        val thumbnail: WikiThumbnail? = null
)
