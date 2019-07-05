package inc.nyenjes.wikiapp.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import inc.nyenjes.wikiapp.R
import inc.nyenjes.wikiapp.activities.fragments.ExploreFragment
import inc.nyenjes.wikiapp.activities.fragments.FavoritesFragment
import inc.nyenjes.wikiapp.activities.fragments.HistoryFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search.*

class MainActivity : AppCompatActivity() {

    private val exploreFragment: ExploreFragment
    private val favoritesFragment: FavoritesFragment
    private val historyFragment: HistoryFragment

    init {
        exploreFragment = ExploreFragment()
        favoritesFragment = FavoritesFragment()
        historyFragment = HistoryFragment()
    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
       val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)

        when(item.itemId){
            R.id.navigation_explore -> transaction.replace(R.id.fragment_container, exploreFragment)
            R.id.navigation_favorites -> transaction.replace(R.id.fragment_container, favoritesFragment)
            R.id.navigation_history -> transaction.replace(R.id.fragment_container, historyFragment)
        }

        transaction.commit()

        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, exploreFragment)
        transaction.commit()
    }
}
