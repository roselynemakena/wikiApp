package inc.nyenjes.wikiapp.activities.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import inc.nyenjes.wikiapp.R
import inc.nyenjes.wikiapp.activities.holders.ListItemHolder
import models.WikiPage

class ArticleListItemRecyclerAdapter() : RecyclerView.Adapter<ListItemHolder>() {
    val currentResults: ArrayList<WikiPage> = ArrayList<WikiPage>()

    override fun getItemCount(): Int {
        return currentResults.size
    }

    override fun onBindViewHolder(holder: ListItemHolder?, position: Int) {
        var page = currentResults[position]
        holder?.updateWithPage(page)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListItemHolder {
        var cardListItem = LayoutInflater.from(parent?.context).inflate(R.layout.article_list, parent, false)
        return ListItemHolder(cardListItem)
    }
}