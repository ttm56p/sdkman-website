package io.sdkman.site

import io.sdkman.repos.Candidate
import org.scalatest.concurrent.Eventually
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpec}
import ratpack.test.MainClassApplicationUnderTest
import support.Mongo

class SdkPageAccSpec extends WordSpec with Matchers with BeforeAndAfterAll with Eventually {

  override def beforeAll = {
    Mongo.dropAllCollections()
    Mongo.insertCandidate(
      Candidate(
        candidate = "java",
        name = "Java",
        description = "Java Platform, Standard Edition (or Java SE) is a widely used platform",
        default = Some("8.0.163-zulu"),
        websiteUrl = "https://zulu.org/",
        distribution = "PLATFORM_SPECIFIC"))
  }

  new MainClassApplicationUnderTest(classOf[SiteMain]).test { client =>
    "SDKs page" should {

      val response = client.get("/sdks")

      "render an OK status" in {
        eventually {
          response.getStatusCode shouldBe 200
        }
      }

      "render an sdk heading" in {
        eventually {
          response.getBody.getText should include("<h4>Java (8.0.163-zulu)</h4>")
        }
      }

      "render an sdk site url" in {
        eventually {
          response.getBody.getText should include("<a href='https://zulu.org/' target='_blank'>https://zulu.org/</a></br>")
        }
      }

      "render an sdk description" in {
        eventually {
          response.getBody.getText should include("<p>Java Platform, Standard Edition (or Java SE) is a widely used platform")
        }
      }

      "render an sdk install command" in {
        eventually {
          response.getBody.getText should include("<code>$ sdk install java</code>")
        }
      }
    }
  }
}
