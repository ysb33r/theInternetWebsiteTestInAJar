package org.ysb33r.theinternet

import spock.lang.Specification
import spock.lang.Timeout


/**
 * @author Schalk W. Cronjé
 */
class TestableWebServerSpec extends Specification {

    @Timeout(65)
    def "Start and stop the service"() {
        given:
        def ws = new TestableWebServer()

        when:
        ws.start()
        println "starting..."
        sleep 20000
        println "stopping"
        ws.stop()

        then:
        true // Expect to complete without exception.
    }
}