package inc.nyenjes.wikiapp.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.gson.Gson
import inc.nyenjes.wikiapp.R
import kotlinx.android.synthetic.main.activity_article_layout.*
import models.WikiPage

class ArticleDetailActivity : AppCompatActivity() {

    private var currentPage: WikiPage? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_article_layout)
        setSupportActionBar(toolBar);
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val wikiPageJson = intent.getStringExtra("page")
        currentPage = Gson().fromJson<WikiPage>(wikiPageJson, WikiPage::class.java)

        article_detail_webview?.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return true
            }
        }

        article_detail_webview.loadUrl(currentPage!!.fullurl)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            finish()
        }
        return true
    }

}