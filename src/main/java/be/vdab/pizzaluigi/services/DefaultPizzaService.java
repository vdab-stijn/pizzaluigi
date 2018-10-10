package be.vdab.pizzaluigi.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.pizzaluigi.entities.Pizza;
import be.vdab.pizzaluigi.repositories.PizzaRepository;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultPizzaService implements PizzaService {

	private final PizzaRepository pizzaRepository;
	
	public DefaultPizzaService(final PizzaRepository pizzaRepository) {
		this.pizzaRepository = pizzaRepository;
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void create(Pizza pizza) {
		pizzaRepository.create(pizza);
	}

	@Override
	public Optional<Pizza> read(long id) {
		return pizzaRepository.read(id);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void update(Pizza pizza) {
		pizzaRepository.update(pizza);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public void delete(long id) {
		pizzaRepository.delete(id);
	}

	@Override
	public List<Pizza> findAll() {
		return pizzaRepository.findAll();
	}

	@Override
	public List<Pizza> findByPriceBetween(BigDecimal low, BigDecimal high) {
		return pizzaRepository.findByPriceBetween(low, high);
	}

	@Override
	public long findNumberOfPizzas() {
		return pizzaRepository.findNumberOfPizzas();
	}

	@Override
	public List<BigDecimal> findUniquePrices() {
		return pizzaRepository.findUniquePrices();
	}

	@Override
	public List<Pizza> findByPrice(BigDecimal price) {
		return pizzaRepository.findByPrice(price);
	}

}
