package api

import utils.Article


private[api] trait ApiCall {

  protected val length = "20"
  protected val language = "en"
  protected val format = "json"
  protected val keyfile = "conf/apikey.txt"
  protected val key = loadKey

  def search(keyword: String): Option[Array[Article]]

  protected def loadKey: String = {
    import scala.io.Source

    val source = Source.fromFile(keyfile)

    try source.mkString
    catch { case _ => null }
    finally source.close()
  }

  protected def parse(src: String): Option[Array[Article]] = {
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


object FarooApi extends ApiCall {

  private val url = "http://www.faroo.com/api"

  override def search(keyword: String): Option[Array[Article]] = {
    import scalaj.http.Http

    if (key == null) return None

    val request = s"$url?key=$key&q=$keyword&start=1&l=$language&length=$length&src=web&i=false&f=json"
    val response = Http(request).asString

    if (response.code != 200) {
      println(response.code)
      return None
    }
    parse(response.body)
  }
}
