package pl.fullsoft.soap.server.repositories;

import org.springframework.stereotype.Component;
import pl.fullsoft.soap_server.countries.Country;
import pl.fullsoft.soap_server.countries.Currency;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class CountryRepository {

    private static final Map<String, Country> countries = new HashMap<>();

    @PostConstruct
    public void initData() {
        Country spain = new Country();
        spain.setCapital("Madrid");
        spain.setCurrency(Currency.EUR);
        spain.setName("Spain");
        spain.setPopulation(46704314);

        countries.put("Spain", spain);
    }

    public Country findCountry(String name) {
        return countries.get(name);
    }
}