package com.stuart.testcontroller.service

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class DogServiceSpec extends Specification {
    def "test findDogs"() {
        given:
        def service = new DogService()

        when:
        def result = service.findDogs(id, age, smellyInd)

        then:
        result.size == expected

        where:
        id    | age | smellyInd | expected
        "001" | 7   | true      | 1
        null  | 11  | null      | 2
        "001" | 21  | null      | 0
        null  | 11  | true      | 1
        null  | 11  | null      | 2
        ""    | 0   | false     | 2
        null  | 0   | null      | 4
    }

    def "test findDogs2"() {
        given:
        def service = new DogService()

        when:
        def result = service.findDogs2(id, age, smellyInd)

        then:
        result.size == expected

        where:
        id    | age | smellyInd | expected
        "001" | 7   | true      | 1
        null  | 11  | null      | 2
        "001" | 21  | null      | 0
        null  | 11  | true      | 1
        null  | 11  | null      | 2
        ""    | 0   | false     | 2
        null  | 0   | null      | 4
    }
}
