package io.sdkman.site

import org.scalatest.{Matchers, WordSpec}
import ratpack.test.MainClassApplicationUnderTest

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class HandlerChainSpec extends WordSpec with Matchers {

  new MainClassApplicationUnderTest(classOf[SiteMain]).test { client =>

    "The application" should {
      "respond with an index page on the root context" in {
        val response = client.get("/")
        response.getStatusCode shouldBe 200
        client.getText should include(
          "The Software Development Kit Manager")
      }

      "respond with an index page on the index context" in {
        val response = client.get("/index")
        response.getStatusCode shouldBe 200
        response.getBody.getText should include(
          "The Software Development Kit Manager")
      }

      "respond with the install page on the install context" in {
        val response = client.get("/install")
        response.getStatusCode shouldBe 200
        response.getBody.getText should include(
          "Installing SDKMAN! on UNIX-like platforms is as easy as ever.")
      }

      "respond with the sdks page on the sdks context" in {
        val response = client.get("/sdks")
        response.getStatusCode shouldBe 200
        response.getBody.getText should include(
          "SDK Installation Candidates")
      }

      "respond with the usage page on the usage context" in {
        val response = client.get("/usage")
        response.getStatusCode shouldBe 200
        response.getBody.getText should include(
          "Install the <strong>latest stable</strong> version of your SDK")
      }

      "respond with the vendors page on the vendors context" in {
        val response = client.get("/vendors")
        response.getStatusCode shouldBe 200
        response.getBody.getText should include(
          "SDKMAN! is unique in that it empowers SDK Vendors")
      }

      "respond with a not found page on an unknown context" in {
        val response = client.get("/unknown")
        response.getStatusCode shouldBe 404
        response.getBody.getText should include(
          "Oops! The page you are looking for does not exist!")
      }
    }
  }
}