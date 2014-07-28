package cities

import com.lordofthejars.nosqlunit.annotation.UsingDataSet
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration

/**
 *
 * Date: 08/07/2014
 * Time: 21:04
 * @author Geoffroy Warin (http://geowarin.github.io)
 */
@ContextConfiguration(classes = [Application])
@UsingDataSet
class CityServiceTest extends FongoSpec {

    @Autowired
    CityService cityService

    def "Test gmongo"() {
        when:
        def cities = City.findAll()

        then:
        cities.size() == 1
        cities.name == ['Kikoo']
    }
}
