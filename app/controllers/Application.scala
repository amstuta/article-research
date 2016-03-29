package controllers

import play.api._
import play.api.mvc._
import play.api.cache.Cache
import play.api.Play.current
import play.api.db._
import play.api.libs.json._
import play.api.data._
import play.api.data.Forms._

object Application extends Controller {

  val form = Form("keyword" -> text)

  def index = Action {
    Ok(views.html.form())
  }

  def submit = Action { implicit request =>
    import utils.Article
    import api.FarooApi

    val keyword = form.bindFromRequest.get
    val result = FarooApi search keyword

    result match {
      case Some(s) => {
        Ok(views.html.articleview(s))
      }
      case None => Ok("Failed")
    }
  }
}
