import com.tkpd.devcamp.connect_to_internet.data.model.NewsApiTopHeadlinesResponse
import com.tkpd.devcamp.connect_to_internet.network.ApiErrorType
import com.tkpd.devcamp.connect_to_internet.network.ApiResult
import com.tkpd.devcamp.recycler_view.model.Article
import com.tkpd.devcamp.recycler_view.model.Source

object DataHelper {
    fun createFakeResponse(): NewsApiTopHeadlinesResponse = NewsApiTopHeadlinesResponse(
        articles = listOf(
            NewsApiTopHeadlinesResponse.Article(
                author = "",
                content = "",
                description = "",
                publishedAt = "",
                source = NewsApiTopHeadlinesResponse.Article.Source(id = "", name = ""),
                title = "",
                url = "",
                urlToImage = "",
            )
        ),
        status = "",
        totalResults = 10,
    )
    fun createFakeClass(response: NewsApiTopHeadlinesResponse = createFakeResponse()): ApiResult<NewsApiTopHeadlinesResponse> {
        val data = NewsApiTopHeadlinesResponse(
            articles = listOf(
                NewsApiTopHeadlinesResponse.Article(
                    author = "",
                    content = "",
                    description = "",
                    publishedAt = "",
                    source = NewsApiTopHeadlinesResponse.Article.Source(id = "", name = ""),
                    title = "",
                    url = "",
                    urlToImage = "",
                )
            ),
            status = "",
            totalResults = 10,
        )
        return ApiResult.Success(data)
    }

    fun createFakeError(error: ApiErrorType): ApiResult.Error {
        return ApiResult.Error(error)
    }

    fun mapData(response: NewsApiTopHeadlinesResponse.Article) = Article(
        author = response.author.orEmpty(),
        content = response.content.orEmpty(),
        description = response.description.orEmpty(),
        publishedAt = response.publishedAt.orEmpty(),
        source = Source(id = response.source?.id.orEmpty(), name = response.source?.name.orEmpty()),
        title = response.title.orEmpty(),
        url = response.url.orEmpty(),
        urlToImage = response.urlToImage.orEmpty(),
    )
}