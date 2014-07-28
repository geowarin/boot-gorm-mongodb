//package cities
//
//import com.lordofthejars.nosqlunit.annotation.UsingDataSet
//import com.mongodb.DB
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.test.context.ContextConfiguration
//
///**
// *
// * Date: 08/07/2014
// * Time: 21:04
// * @author Geoffroy Warin (http://geowarin.github.io)
// */
//@ContextConfiguration(classes = [Application])
//@UsingDataSet
//class CityServiceTest extends FongoSpec {
//
//    @Autowired
//    CityService cityService
//
//    @Autowired
//    DB db
//
//    def "Test gmongo"() {
//        when:
//        def cities = db.city.find()
//
//        then:
//        cities.size() == 1
//    }
//
////    def "Test"() {
////        when:
////        def cities = cityService.getAllCities()
////
////        then:
////        cities.size() == 1
////    }
//}
