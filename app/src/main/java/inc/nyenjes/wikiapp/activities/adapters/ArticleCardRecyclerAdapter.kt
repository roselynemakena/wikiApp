package inc.nyenjes.wikiapp.activities.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import inc.nyenjes.wikiapp.R
import inc.nyenjes.wikiapp.activities.holders.CardViewHolder
import models.WikiPage

class ArticleCardRecyclerAdapter() : RecyclerView.Adapter<CardViewHolder>() {

    var currentReslts : ArrayList<WikiPage> = ArrayList<WikiPage>()
    override fun getItemCount(): Int {
        return currentReslts.size
    }

    override fun onBindViewHolder(holder: CardViewHolder?, position: Int) {
        var page = currentReslts[position]
        holder?.updateWithPage(page)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CardViewHolder {
        var cardItem = LayoutInflater.from(parent?.context).inflate(R.layout.article_card, parent, false)
        return CardViewHolder(cardItem)
    }
}