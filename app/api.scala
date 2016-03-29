package api

import utils.Article


private[api] trait ApiCall {

  protected val length = "20"
  protected val language = "en"
  protected val format = "json"

  def search(keyword: String): Option[Array[Article]]

  protected def parse(src: String): Option[Array[Article]]
}


object FarooApi extends ApiCall {

  private val url = "http://www.faroo.com/api"
  private val key = ""

  override def search(keyword: String): Option[Array[Article]] = {
    import scalaj.http.Http

    val response = Http(url)
      .param("q", keyword)
      .param("start", "1")
      .param("length", length)
      .param("l", language)
      .param("src", "web")
      .param("i", "false")
      .param("f", format)
      .param("key", key)
      .asString

    if (response.code != 200) {
      println(response.code)
      return None
    }
    parse(response.body)
  }

  override def parse(src: String): Option[Array[Article]] = {
    import play.api.libs.json._

    val js = Json parse src
    val articles = (js \ "results").as[Array[JsValue]]

    try {
      Option(for (article <- articles)
      yield new Article(
        (article \ "url").as[String],
        (article \ "title").as[String],
        (article \ "author").as[String],
        (article \ "date").as[Long],
        (article \ "content").as[String])
      )
    }
    catch { case e: Exception => None }
  }
}
