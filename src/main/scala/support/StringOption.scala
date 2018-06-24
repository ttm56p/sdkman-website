package support

object StringOption {
  def apply(x: String): Option[String] = Option(x).map(_.trim).filter(_.nonEmpty)
}