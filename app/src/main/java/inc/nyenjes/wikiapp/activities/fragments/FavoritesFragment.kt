package inc.nyenjes.wikiapp.activities.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import inc.nyenjes.wikiapp.R
import inc.nyenjes.wikiapp.activities.WikiApplication
import inc.nyenjes.wikiapp.activities.adapters.ArticleCardRecyclerAdapter
import inc.nyenjes.wikiapp.activities.adapters.ArticleListItemRecyclerAdapter
import inc.nyenjes.wikiapp.activities.managers.WikiManager
import models.WikiPage
import org.jetbrains.anko.doAsync

/**
 * A [FavoritesFragment] subclass.
 *
 */
class FavoritesFragment : Fragment() {

    private var wikiManager : WikiManager? = null
    var favoritesRecycler: RecyclerView? = null
    var adapter: ArticleCardRecyclerAdapter = ArticleCardRecyclerAdapter()


    override fun onAttach(context: Context?) {
        super.onAttach(context)

        wikiManager = (activity!!.applicationContext as WikiApplication).wikiManager
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_favorites, container, false)

        favoritesRecycler = view.findViewById<RecyclerView>(R.id.favorites_recycler)

        favoritesRecycler!!.layoutManager = LinearLayoutManager(context)
        favoritesRecycler!!.adapter = adapter

        return view
    }

    override fun onResume() {
        super.onResume()

        doAsync {
            val favoritesArticles = wikiManager!!.getFavorites()
            adapter.currentReslts.clear()
            adapter.currentReslts.addAll(favoritesArticles as ArrayList<WikiPage>)
            activity!!.runOnUiThread { adapter.notifyDataSetChanged() }
        }
    }
}
