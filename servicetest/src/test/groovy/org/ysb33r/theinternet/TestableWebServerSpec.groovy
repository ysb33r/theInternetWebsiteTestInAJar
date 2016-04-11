package org.ysb33r.theinternet

import geb.spock.GebSpec
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Timeout


/**
 * @author Schalk W. Cronjé
 */
class TestableWebServerSpec extends GebSpec {

    static final int PORT = 4567

    @Shared
    @AutoCleanup("stop")
    TestableWebServer server = new TestableWebServer()

    void setupSpec() {
        server.start()
        sleep 10000
    }

    def "Check that we can load a page"() {

        when:
        go "http://localhost:${PORT}/checkboxes"

        then:
        $('h3').text() == 'Checkboxes'
    }
}