package reprator.dukaan.config

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class AppConfigKtTest{
    @Test
    @DisplayName("Basic Test")
    fun testRequests() = withTestApplication(Application::mainModule) {
        with(handleRequest(HttpMethod.Get, "/")) {
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals("Hi Satya", response.content)
        }
    }
}