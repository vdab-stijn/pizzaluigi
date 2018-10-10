package be.vdab.pizzaluigi.repositories;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import be.vdab.pizzaluigi.entities.Pizza;
import be.vdab.pizzaluigi.exceptions.PizzaNotFoundException;


@Repository
public class JdbcPizzaRepository implements PizzaRepository {

	private final JdbcTemplate template;
	private final SimpleJdbcInsert insert;
	
	private final RowMapper<Pizza> pizzaRowMapper = (resultSet, rowNum) ->
		new Pizza(
				resultSet.getLong("id"),
				resultSet.getString("name"),
				resultSet.getBigDecimal("price"),
				resultSet.getBoolean("spicy"));
	private final RowMapper<BigDecimal> priceRowMapper = (resultSet, rowNum) ->
		resultSet.getBigDecimal("price");
	
	public JdbcPizzaRepository(final JdbcTemplate template) {
		this.template = template;
		this.insert = new SimpleJdbcInsert(this.template);
		
		insert.withTableName("pizzas");
		insert.usingGeneratedKeyColumns("id");
	}
	
	@Override
	public void create(Pizza pizza) {
		final Map<String, Object> columns = new HashMap<>();
		
		columns.put("name", pizza.getName());
		columns.put("price",  pizza.getPrice());
		columns.put("spicy", pizza.isSpicy());
		final Number id = insert.executeAndReturnKey(columns);
		
		pizza.setId(id.longValue());
	}

	private static final String READ
	= "SELECT id, name, price, spicy FROM pizzas WHERE id = ?";
	@Override
	public Optional<Pizza> read(long id) {
		try {
			return Optional.of(template.queryForObject(READ, pizzaRowMapper, id));
		}
		catch (final IncorrectResultSizeDataAccessException irsdae) {
			return Optional.empty();
		}
	}

	private static final String UPDATE
	= "UPDATE pizzas SET name=?, price=?, spicy=? WHERE id=?";
	@Override
	public void update(Pizza pizza) {
		if (template.update(UPDATE,
				pizza.getName(),
				pizza.getPrice(),
				pizza.isSpicy(),
				pizza.getId()) == 0) {
			throw new PizzaNotFoundException(
					"Couldn't find a pizza with ID " + pizza.getId());
		}
	}

	private static final String DELETE
	= "DELETE FROM pizzas WHERE id=?";
	@Override
	public void delete(long id) {
		template.update(DELETE, id);
	}

	private static final String SELECT_ALL
	= "SELECT id, name, price, spicy FROM pizzas ORDER BY id";
	@Override
	public List<Pizza> findAll() {
		return template.query(SELECT_ALL, pizzaRowMapper);
	}

	private static final String SELECT_BY_PRICE_BETWEEN
	= "SELECT id, name, price, spicy FROM pizzas WHERE price " +
			"BETWEEN ? AND ? ORDER BY price";
	@Override
	public List<Pizza> findByPriceBetween(BigDecimal low, BigDecimal high) {
		return template.query(SELECT_BY_PRICE_BETWEEN,  pizzaRowMapper, low, high);
	}

	private static final String SELECT_NUMBER
	= "SELECT COUNT(*) FROM pizzas";
	@Override
	public long findNumberOfPizzas() {
		return template.queryForObject(SELECT_NUMBER, Long.class);
	}

	private static final String SELECT_UNIQUE_PRICES
	= "SELECT DISTINCT price FROM pizzas ORDER BY price";
	@Override
	public List<BigDecimal> findUniquePrices() {
		return template.query(SELECT_UNIQUE_PRICES, priceRowMapper);
	}

	private static final String SELECT_BY_PRICE
	= "SELECT id, name, price, spicy FROM pizzas WHERE price = ? ORDER BY name";
	@Override
	public List<Pizza> findByPrice(BigDecimal price) {
		return template.query(SELECT_BY_PRICE, pizzaRowMapper, price);
	}

}
