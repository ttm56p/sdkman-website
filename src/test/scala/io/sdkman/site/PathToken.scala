package io.sdkman.site

import java.util

object PathToken {
  def binding(key: String, value: String): util.HashMap[String, String] = new util.HashMap[String, String] {
    put(key, value)
  }
}