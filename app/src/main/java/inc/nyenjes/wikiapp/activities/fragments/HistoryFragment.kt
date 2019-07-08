package inc.nyenjes.wikiapp.activities.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import inc.nyenjes.wikiapp.R
import inc.nyenjes.wikiapp.activities.WikiApplication
import inc.nyenjes.wikiapp.activities.adapters.ArticleListItemRecyclerAdapter
import inc.nyenjes.wikiapp.activities.managers.WikiManager
import models.WikiPage
import org.jetbrains.anko.*

/**
 * A simple [Fragment] subclass.
 *
 */
class HistoryFragment : Fragment() {

    var historyRecycler: RecyclerView? = null
    var wikiManager: WikiManager? = null

    val adapter: ArticleListItemRecyclerAdapter = ArticleListItemRecyclerAdapter()

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        wikiManager = (activity!!.applicationContext as WikiApplication).wikiManager
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_history, container, false)

        historyRecycler = view.findViewById<RecyclerView>(R.id.history_recycler)

        historyRecycler!!.layoutManager = LinearLayoutManager(context)
        historyRecycler!!.adapter = adapter

        return view
    }

    override fun onResume() {
        super.onResume()

        doAsync {
            val historyArticles = wikiManager!!.getHistory()
            adapter.currentResults.clear()
            adapter.currentResults.addAll(historyArticles as ArrayList<WikiPage>)
            activity!!.runOnUiThread { adapter.notifyDataSetChanged() }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.history_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == R.id.action_clear_history) {
            activity!!.alert ( "Are you sure?" , "Confirm") {
                yesButton {
                    //confirmed
                    //clearing..
                    adapter.currentResults.clear()
                    doAsync {
                        activity!!.runOnUiThread {
                            wikiManager?.clearHistory()
                        }
                    }
                }
                noButton {
                    //
                    activity!!.applicationContext.toast("history not cleared")
                }
            }.show()
        }
        return true
    }
}
