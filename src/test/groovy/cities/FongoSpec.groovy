package cities

import com.github.fakemongo.Fongo
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule
import com.mongodb.Mongo
import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder
import org.junit.Rule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import spock.lang.Specification

/**
 * Created by gewarin on 15/05/2014.
 */
@ContextConfiguration(classes = [FongoConfig])
@WebAppConfiguration
abstract class FongoSpec extends Specification {

    @Autowired
    @SuppressWarnings('GroovyUnusedDeclaration')
    ApplicationContext applicationContext

    @Rule
    public MongoDbRule mongoDbRule = MongoDbRule.MongoDbRuleBuilder.newMongoDbRule().defaultSpringMongoDb('test');

    @Configuration
    static class FongoConfig {

        @Bean
        public Mongo mongo() {
            return new EmbeddedMongoBuilder()
                    .version("2.4.5")
                    .port(12345)
                    .build()
        }
    }
}
