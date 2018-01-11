package io.sdkman.site

import java.util

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}
import ratpack.func.Action
import ratpack.test.handling.RequestFixture

@RunWith(classOf[JUnitRunner])
class ContextualHandlerSpec extends WordSpec with Matchers {
  "ContextualHandler" should {
    "render 200 if a context is present" in {
      val action: Action[RequestFixture] = fix => fix.pathBinding(binding("context", "sdks"))

      val result = RequestFixture.handle(new ContextualHandler, action)

      result.getStatus.getCode shouldBe 200
    }

    "render 404 if no context is present" in {
      val action: Action[RequestFixture] = _ => Unit

      val result = RequestFixture.handle(new ContextualHandler, action)

      result.getStatus.getCode shouldBe 404
    }
  }

  private def binding(key: String, value: String) = new util.HashMap[String, String] {
    put(key, value)
  }
}
