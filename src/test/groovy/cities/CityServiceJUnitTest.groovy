package cities

import com.lordofthejars.nosqlunit.annotation.UsingDataSet
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule
import com.mongodb.Mongo
import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule

/**
 *
 * Date: 08/07/2014
 * Time: 21:04
 * @author Geoffroy Warin (http://geowarin.github.io)
 */
@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(classes = [EmbeddedConfig, Application])
@UsingDataSet
//@WebAppConfiguration
class CityServiceJUnitTest {

    @Autowired
    CityService cityService

    @Autowired
    @SuppressWarnings('GroovyUnusedDeclaration')
    ApplicationContext applicationContext

    @Rule
    public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb('test')

    @Test
    void test() {
        City.withTransaction {
            new City(name: 'Lauwl', location: [1, 2]).save()
        }
        def cities = City.findAll()
        println cities

        assert cities.size() == 2
    }

    @Configuration
    static class EmbeddedConfig {

        @Bean
        public Mongo mongo() {
            return new EmbeddedMongoBuilder()
                    .version("2.4.5")
                    .port(12345)
                    .build()
        }
    }
}
