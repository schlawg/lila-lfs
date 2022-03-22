package lila.coordinate

import play.api.Configuration
import com.softwaremill.macwire._

import lila.common.config.CollName

final class Env(
    appConfig: Configuration,
    db: lila.db.Db
)(implicit ec: scala.concurrent.ExecutionContext) {

  private lazy val scoreColl = db(appConfig.get[CollName]("coordinate.collection.score"))

  lazy val api = wire[CoordinateApi]

  lazy val forms = CoordinateForm
}

sealed abstract class CoordMode(val key: String)

object CoordMode {
  case object FindSquare extends CoordMode("findSquare")
  case object NameSquare extends CoordMode("nameSquare")
  val all = List[CoordMode](FindSquare, NameSquare)
}
