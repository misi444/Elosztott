package hu.miskolc.uni.iit.dist.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import hu.miskolc.uni.iit.dist.domain.Country;
import hu.miskolc.uni.iit.dist.domain.Currency;

@Component
public class CountryRepository
{
	private static final List<Country> MOCKED_COUNTRIES = new ArrayList<Country>();
	
	static
	{
		Country hungary = new Country();
		hungary.setName("Hungary");
		hungary.setCapital("Budapest");
		hungary.setCurrency(Currency.HUF);
		hungary.setPopulation(9811178);

		MOCKED_COUNTRIES.add(hungary);
		
		Country spain = new Country();
		spain.setName("Spain");
		spain.setCapital("Madrid");
		spain.setCurrency(Currency.EUR);
		spain.setPopulation(46704314);

		MOCKED_COUNTRIES.add(spain);

		Country poland = new Country();
		poland.setName("Poland");
		poland.setCapital("Warsaw");
		poland.setCurrency(Currency.PLN);
		poland.setPopulation(38186860);

		MOCKED_COUNTRIES.add(poland);

		Country uk = new Country();
		uk.setName("United Kingdom");
		uk.setCapital("London");
		uk.setCurrency(Currency.GBP);
		uk.setPopulation(63705000);

		MOCKED_COUNTRIES.add(uk);
	}

	public Country findCountry(String name) {
		Assert.notNull(name);

		Country result = null;

		for (Country country : MOCKED_COUNTRIES) {
			if (name.equals(country.getName())) {
				result = country;
			}
		}

		return result;
	}
}