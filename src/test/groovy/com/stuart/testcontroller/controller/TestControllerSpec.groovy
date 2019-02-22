package com.stuart.testcontroller.controller


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [TestController])
@TestPropertySource(properties = "test.controller.enabled=false")
class TestControllerDisabledSpec extends Specification {

    @Autowired
    MockMvc mvc

    def "test controller is disabled"() {
        when:
        def response = mvc.perform(get('/test'))

        then:
        response.andExpect(status().isNotFound())
    }
}


@WebMvcTest(controllers = [TestController])
@TestPropertySource(properties = "test.controller.enabled=true")
class TestControllerEnabledSpec extends Specification {

    @Autowired
    MockMvc mvc

    def "test controller is disabled"() {
        when:
        def response = mvc.perform(get('/test'))

        then:
        response.andExpect(status().isOk())
    }
}