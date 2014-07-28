package cities

import grails.mongodb.geo.Point
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import static org.springframework.web.bind.annotation.RequestMethod.GET
import static org.springframework.web.bind.annotation.RequestMethod.POST

@RestController
class CityController {

    @RequestMapping(value = "/", method = GET)
    List index() {
        City.list().collect { [name: it.name, pos: it.location] }
    }

    @RequestMapping(value = "/near/{cityName}", method = GET)
    ResponseEntity near(@PathVariable String cityName) {
        def city = City.where { name == cityName }.find()
        if (city) {
            List<City> closest = City.findAllByLocationNear(city.location)
            return new ResponseEntity([name: closest[1].name], HttpStatus.OK)
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @RequestMapping(value = "/", method = POST)
    ResponseEntity insertCity(@RequestBody City city) {
        city.location = Point.valueOf(1.0, 2.0)
        City.withTransaction {
            city.save()
        }
        return new ResponseEntity(HttpStatus.OK)
    }

//    @PostConstruct
//    void populateCities() {
//        City.withTransaction{
//            City.collection.remove(new BasicDBObject())
//            City.saveAll(
//                [ new City( name:"London", location: Point.valueOf([-0.125487, 51.508515])),
//                  new City( name:"Paris", location: Point.valueOf([2.352222, 48.856614])),
//                  new City( name:"New York", location: Point.valueOf([-74.005973, 40.714353])),
//                  new City( name:"San Francisco", location: Point.valueOf([-122.419416, 37.774929])) ]
//            )
//        }
//    }
}

