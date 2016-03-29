package controllers

import play.api._
import play.api.mvc._
import play.api.cache.Cache
import play.api.Play.current
import play.api.db._
import play.api.libs.json._

object Application extends Controller {

  def index = Action {
    Ok("A simple article search tool")
  }

  def search(kw: String) = Action {
    import utils.Article
    import api.FarooApi

    val result = FarooApi search kw

    result match {
      case Some(s) => {
        s foreach println
        Ok("Success")
      }
      case None => Ok("Fail")
    }
  }

}
