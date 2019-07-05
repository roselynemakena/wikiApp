package inc.nyenjes.wikiapp.activities.providers

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import models.Urls
import models.WikiResult
import java.io.Reader

class ArticleDataProvider {
    class WikiPediaDataDeserializer : ResponseDeserializable<WikiResult> {
        override fun deserialize(reader: Reader): WikiResult? = Gson().fromJson(reader, WikiResult::class.java)
    }

    init {
        FuelManager.instance.baseHeaders = mapOf("User-Agent" to "Nyenjes")
    }


    fun Search(term: String, skip: Int, take: Int, responseHandler: (result: WikiResult) -> Unit?) {
        Urls.getSearchUrl(term, skip, take).httpGet()
                .responseObject(WikiPediaDataDeserializer()){_, response, result ->
                    if(response.statusCode != 200) {
                        throw Exception("Unable to get articles")
                    }
                    val(data,_) = result
                    responseHandler.invoke(data as WikiResult)

                }
    }

    fun getRandom(take: Int, responseHandler: (result: WikiResult) -> Unit?) {
        Urls.getRandomArticles(take).httpGet()
                .responseObject(WikiPediaDataDeserializer()){_,response, result ->
                    if(response.statusCode != 200) {
                        throw Exception("Unable to get articles")
                    }
                    val(data, _) = result
                    responseHandler.invoke(data as WikiResult)
                }
    }
}
