package io.sdkman.site

import io.sdkman.repos.Candidate
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpec}
import ratpack.test.MainClassApplicationUnderTest
import support.Mongo

class SdkPageAccSpec extends WordSpec with Matchers with BeforeAndAfterAll {

  override def beforeAll =
    Mongo.insertCandidate(
      Candidate(
        candidate = "java",
        name = "Java",
        description = "Java Platform, Standard Edition (or Java SE) is a widely used platform",
        default = "8.0.163-zulu",
        websiteUrl = "https://zulu.org/",
        distribution = "PLATFORM_SPECIFIC"))

  new MainClassApplicationUnderTest(classOf[SiteMain]).test { client =>
    "SDKs page" should {

      val response = client.get("/sdks")

      "render an OK status" in {
        response.getStatusCode shouldBe 200
      }

      "render an sdk heading" in {
        response.getBody.getText should include("<h4>Java</h4>")
      }

      "render an sdk site url" in {
        response.getBody.getText should include("<a href='https://zulu.org/' target='_blank'>https://zulu.org/</a></br>")
      }

      "render an sdk description" in {
        response.getBody.getText should include("<p>Java Platform, Standard Edition (or Java SE) is a widely used platform")
      }

      "render an sdk install command" in {
        response.getBody.getText should include("<code>$ sdk install java</code>")
      }
    }
  }

  override def afterAll = Mongo.dropAllCollections()
}
