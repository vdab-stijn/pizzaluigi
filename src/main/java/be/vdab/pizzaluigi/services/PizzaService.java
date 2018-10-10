package be.vdab.pizzaluigi.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import be.vdab.pizzaluigi.entities.Pizza;

public interface PizzaService {

	void create(final Pizza pizza);
	Optional<Pizza> read(final long id);
	void update(final Pizza pizza);
	void delete(final long id);
	List<Pizza> findAll();
	List<Pizza> findByPriceBetween(final BigDecimal low, final BigDecimal high);
	long findNumberOfPizzas();
	List<BigDecimal> findUniquePrices();
	List<Pizza> findByPrice(final BigDecimal price);
}
