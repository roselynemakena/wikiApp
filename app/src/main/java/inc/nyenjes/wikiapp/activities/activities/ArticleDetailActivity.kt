package inc.nyenjes.wikiapp.activities.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.gson.Gson
import inc.nyenjes.wikiapp.R
import inc.nyenjes.wikiapp.activities.WikiApplication
import inc.nyenjes.wikiapp.activities.managers.WikiManager
import kotlinx.android.synthetic.main.activity_article_layout.*
import models.WikiPage
import org.jetbrains.anko.toast
import java.lang.Exception

class ArticleDetailActivity : AppCompatActivity() {

    private var currentPage: WikiPage? = null
    private var wikiManager: WikiManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_article_layout)
        setSupportActionBar(toolBar);
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        wikiManager = (applicationContext as WikiApplication).wikiManager


        val wikiPageJson = intent.getStringExtra("page")
        currentPage = Gson().fromJson<WikiPage>(wikiPageJson, WikiPage::class.java)

        supportActionBar?.title = currentPage?.title

        article_detail_webview?.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return true
            }
        }

        article_detail_webview.loadUrl(currentPage!!.fullurl)
        wikiManager?.addHistory(currentPage!!)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.article_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        try {
            if (item!!.itemId == android.R.id.home) {
                finish()
            }else if(item!!.itemId == R.id.action_favorite) {
                // is article favorite?
                toast("tryinf to add articele")

                if(!wikiManager!!.getIsFavorite(currentPage!!.pageid!!)) {
                    wikiManager!!.removeFavorites(currentPage!!.pageid!!)
                    toast("acticle added to favorites")
                }
                else {
                    wikiManager!!.addFavorites(currentPage!!)
                    toast("acticle removed from favorites")
                }
            }

        }catch (ex: Exception) {
            toast("unable to update article")

        }
        return true
    }

}
