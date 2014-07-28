package cities

import com.mongodb.Mongo
import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan

@EnableAutoConfiguration
@ComponentScan
class Application {
    static void main(String[] args) {
        SpringApplication.run Application, args
    }

    @Bean
    public Mongo mongo() {
        new EmbeddedMongoBuilder()
                .version('2.4.5')
                .port(12345)
                .build()
    }

}