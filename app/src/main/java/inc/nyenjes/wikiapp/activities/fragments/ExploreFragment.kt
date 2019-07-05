package inc.nyenjes.wikiapp.activities.fragments


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import inc.nyenjes.wikiapp.R
import inc.nyenjes.wikiapp.activities.SearchActivity
import inc.nyenjes.wikiapp.activities.adapters.ArticleCardRecyclerAdapter
import inc.nyenjes.wikiapp.activities.providers.ArticleDataProvider
import kotlinx.android.synthetic.main.fragment_explore.*
import kotlin.jvm.java

// TODO: Rename parameter arguments, choose names that match
/**
 * A simple [Fragment] subclass.
 *
 */
class ExploreFragment : Fragment() {
    private val articleProvider: ArticleDataProvider = ArticleDataProvider()
    private val adapter: ArticleCardRecyclerAdapter = ArticleCardRecyclerAdapter()

    var searchCardView: CardView? = null
    var exploreRecycler: RecyclerView? = null
    var refresher: SwipeRefreshLayout? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_explore, container, false)

        searchCardView = view.findViewById<CardView>(R.id.search_card_view)
        exploreRecycler = view.findViewById<RecyclerView>(R.id.explore_article_recycler)
        refresher = view.findViewById<SwipeRefreshLayout>(R.id.refresher)

        searchCardView!!.setOnClickListener{
            val searchIntent = Intent(context, SearchActivity::class.java)
            context!!.startActivity(searchIntent)
        }

        exploreRecycler!!.layoutManager = LinearLayoutManager(context)
        exploreRecycler!!.adapter = adapter

        refresher!!.setOnRefreshListener {
            getRandomArticles()
        }

        getRandomArticles()

        return  view

    }

    fun getRandomArticles() {
        refresher?.isRefreshing = true
        try {

            articleProvider.getRandom(15, {wikiresult ->
                adapter.currentReslts.clear()
                adapter.currentReslts.addAll(wikiresult.query!!.pages)
                activity!!.runOnUiThread {
                    adapter.notifyDataSetChanged()
                    refresher!!.isRefreshing = false

                }
            })
        }
        catch (ex: Exception) {
            val builder = AlertDialog.Builder(activity)
            builder.setMessage(ex.message).setTitle("oops!")

            var dialog = builder.create()
            dialog.show()
        }
    }
}
