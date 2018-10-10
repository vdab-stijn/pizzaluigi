package be.vdab.pizzaluigi.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import be.vdab.pizzaluigi.entities.Pizza;
import be.vdab.pizzaluigi.exceptions.PizzaNotFoundException;

@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(JdbcPizzaRepository.class)
@Sql("/insertPizza.sql")
public class JdbcPizzaRepositoryTest
extends AbstractTransactionalJUnit4SpringContextTests {
	
	private static final String PIZZAS = "pizzas";
	
	@Autowired
	private JdbcPizzaRepository repository;
	
	@Test
	public void findNumber() {
		assertEquals(super.countRowsInTable(PIZZAS),
			repository.findNumberOfPizzas());
	}
	
	@Test
	public void findAll() {
		final List<Pizza> pizzas = repository.findAll();
		assertEquals(super.countRowsInTable(PIZZAS), pizzas.size());
		
		// Sorted ascending ?
		long previousId = 0;
		
		for (final Pizza pizza : pizzas) {
			assertTrue(pizza.getId() > previousId);
			previousId = pizza.getId();
		}
	}
	
	@Test
	public void create() {
		int numPizzas = super.countRowsInTable(PIZZAS);
		final Pizza pizza = new Pizza("testPizza2", BigDecimal.TEN, false);
		
		repository.create(pizza);
		
		assertNotEquals(0, pizza.getId());
		assertEquals(numPizzas + 1, this.countRowsInTable(PIZZAS));
		assertEquals(1, super.countRowsInTableWhere(PIZZAS, "id=" + pizza.getId()));
	}
	
	public long idOfTestPizza() {
		return super.jdbcTemplate.queryForObject(
				"SELECT id FROM pizzas WHERE name='testPizza'", Long.class);
	}
	
	@Test
	public void delete() {
		final long id = idOfTestPizza();
		final int numOfPizzas = super.countRowsInTable(PIZZAS);
		
		repository.delete(id);
		
		assertEquals(numOfPizzas - 1, super.countRowsInTable(PIZZAS));
		assertEquals(0, super.countRowsInTableWhere(PIZZAS, "id=" + id));
	}
	
	@Test
	public void update() {
		final long id = idOfTestPizza();
		final Pizza pizza = new Pizza(id, "testPizza", BigDecimal.ONE, false);
		
		repository.update(pizza);
		
		assertEquals(0, BigDecimal.ONE.compareTo(
				super.jdbcTemplate.queryForObject(
						"SELECT price FROM pizzas WHERE id=?",
						BigDecimal.class, id)));
	}
	
	@Test(expected = PizzaNotFoundException.class)
	public void nonExistantPizza() {
		repository.update(new Pizza(-1, "test", BigDecimal.ONE, false));
	}
	
	@Test
	public void findByPriceBetween() {
		final List<Pizza> pizzas
		= repository.findByPriceBetween(BigDecimal.ONE, BigDecimal.TEN);
		
		BigDecimal previousPrice = BigDecimal.ZERO;
		
		for (final Pizza pizza : pizzas) {
			assertTrue(pizza.getPrice().compareTo(BigDecimal.ONE) >= 0);
			assertTrue(pizza.getPrice().compareTo(BigDecimal.TEN)<= 0);
			assertTrue(previousPrice.compareTo(pizza.getPrice()) <= 0);
			
			previousPrice = pizza.getPrice();
		}
		
		assertEquals(super.countRowsInTableWhere(PIZZAS,
				"price BETWEEN 1 AND 10"), pizzas.size());
	}
	
	@Test
	public void findUniquePricesReturnsPricesAscending() {
		final List<BigDecimal> prices = repository.findUniquePrices();
		final long numberOfPrices = super.jdbcTemplate.queryForObject(
				"SELECT COUNT(DISTINCT price) FROM pizzas", Long.class);
		
		assertEquals(numberOfPrices, prices.size());
		
		BigDecimal previousPrice = BigDecimal.valueOf(-1);
		for (final BigDecimal price : prices) {
			assertTrue(price.compareTo(previousPrice) > 0);
			
			previousPrice = price;
		}
	}
	
	@Test
	public void findByPrice() {
		final List<Pizza> pizzas = repository.findByPrice(BigDecimal.TEN);
		
		String previousName = "";
		
		for (final Pizza pizza : pizzas) {
			assertEquals(0, BigDecimal.TEN.compareTo(pizza.getPrice()));
			assertTrue(previousName.compareTo(pizza.getName()) <= 0);
			
			previousName = pizza.getName();
		}
		
		assertEquals(super.countRowsInTableWhere(
				PIZZAS,  "price=10"), pizzas.size());
	}
}
