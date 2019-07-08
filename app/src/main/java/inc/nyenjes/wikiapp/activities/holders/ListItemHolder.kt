package inc.nyenjes.wikiapp.activities.holders

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import inc.nyenjes.wikiapp.R
import inc.nyenjes.wikiapp.activities.activities.ArticleDetailActivity
import models.WikiPage

class ListItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val articleImageView: ImageView = itemView.findViewById<ImageView>(R.id.result_icon)
    private val articleTitle: TextView = itemView.findViewById<TextView>(R.id.result_title)

    private var currentPage: WikiPage? = null

    init {
        itemView.setOnClickListener {view : View? ->
            var detailPageIntent = Intent(itemView.context, ArticleDetailActivity::class.java)
            var pageJson = Gson().toJson(currentPage)
            detailPageIntent.putExtra("page", pageJson)
            itemView.context.startActivity(detailPageIntent)
        }
    }

    fun updateWithPage(page: WikiPage) {

        if(page.thumbnail != null) {
            Picasso.with(itemView.context).load(page.thumbnail!!.source).into(articleImageView)
            articleTitle.text = page.title

            currentPage = page
        }
    }


}