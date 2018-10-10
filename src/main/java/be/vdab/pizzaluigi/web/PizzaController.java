package be.vdab.pizzaluigi.web;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.pizzaluigi.entities.Pizza;
import be.vdab.pizzaluigi.services.EuroService;
import be.vdab.pizzaluigi.services.PizzaService;

@Controller
@RequestMapping("pizzas")
public class PizzaController {
	
	private static final Logger LOGGER
	= LoggerFactory.getLogger(PizzaController.class);

	private static final String PIZZA_VIEW = "pizza";
	private static final String PIZZAS_VIEW = "pizzas";
	
//	private final List<Pizza> pizzas = Arrays.asList(
//			new Pizza(1, "Prosciutto", BigDecimal.valueOf(4), true),
//			new Pizza(2, "Margherita", BigDecimal.valueOf(5), false),
//			new Pizza(3, "Calzone", BigDecimal.valueOf(4), false));
//	private final Map<Long, Pizza> pizzas = new LinkedHashMap<>();
	
	
	private final EuroService euroService;
	private final PizzaService pizzaService;
	
	@Autowired
	public PizzaController(
			final EuroService euroService,
			final PizzaService pizzaService) {
		this.euroService = euroService;
		this.pizzaService = pizzaService;
		
//		pizzas.put(1L, new Pizza(
//				1, "Prosciutto", BigDecimal.valueOf(4), true));
//		pizzas.put(2L, new Pizza(
//				2, "Margherita", BigDecimal.valueOf(5), false));
//		pizzas.put(3L, new Pizza(
//				3, "Calzone", BigDecimal.valueOf(4), false));
//		pizzas.put(4L, new Pizza(
//				4, "Funghi & Olive", BigDecimal.valueOf(5), false));
	}
			
//	@GetMapping
//	public final ModelAndView pizzas() {
//		return new ModelAndView(PIZZAS_VIEW, "pizzas", pizzas);
//	}
	
	@GetMapping
	public final ModelAndView pizzas() {
		return new ModelAndView(PIZZAS_VIEW, "pizzas", pizzaService.findAll());
	}
	
//	@GetMapping("pizza")
//	public final ModelAndView pizza(long id) {
//		final ModelAndView modelAndView = new ModelAndView(PIZZA_VIEW);
//		Pizza pizza;
//		
//		if (id <= 0) {
//			pizza = new Pizza();
//		}
//		else {
//			pizza = pizzas.get(id);
//		}
//		
//		if (id > 0 && pizza == null) {
//			LoggerFactory.getLogger(PizzaController.class).error(
//					pizzas.size() + " pizza" + (pizzas.size() != 1 ? "s" : ""));
//			throw new PizzaNotFoundException(
//					"Couldn't find the pizza with id " + id);
//			
//			
//		}
//	
//		modelAndView.addObject(pizza);
//		modelAndView.addObject(
//				"inDollar", euroService.toDollar(pizza.getPrice()));
//		
//		return modelAndView;
//	}
	
	@GetMapping("{id}")
	public final ModelAndView pizza(@PathVariable long id) {
		final ModelAndView modelAndView = new ModelAndView(PIZZA_VIEW);
		pizzaService.read(id).ifPresent(pizza -> {
			modelAndView.addObject(pizza);
			modelAndView.addObject("inDollar",
				euroService.toDollar(pizza.getPrice()));
		});
		
		return modelAndView;
	}
	
	private static final String PRICES_VIEW = "prices";
	@GetMapping(params="prices")
	ModelAndView prices() {
//		return new ModelAndView(PRICES_VIEW, "prices",
//				pizzas.values().stream()
//				.map(pizza -> pizza.getPrice())
//				.distinct()
//				.collect(Collectors.toSet()));
		return new ModelAndView(PRICES_VIEW, "prices",
				pizzaService.findUniquePrices());
	}
	
	@GetMapping(params="price")
	public final ModelAndView pizzasWithPrice(BigDecimal price) {
//		return new ModelAndView(PRICES_VIEW, "pizzas",
//				pizzas.values().stream()
//				.filter(pizza -> pizza.getPrice().equals(price))
//				.collect(Collectors.toList()))
//				.addObject("price", price);
//		return new ModelAndView(PRICES_VIEW, "pizzas",
//				pizzas.values().stream()
//				.filter(pizza -> pizza.getPrice().equals(price))
//				.collect(Collectors.toList()))
//				.addObject("price", price)
//				.addObject("prices", pizzas.values().stream()
//						.map(pizza -> pizza.getPrice())
//						.distinct()
//						.collect(Collectors.toSet()));
		return new ModelAndView(PRICES_VIEW, "pizzas",
				pizzaService.findByPrice(price))
				.addObject("price", price)
				.addObject("prices", pizzaService.findUniquePrices());
	}
	
	private static final String FROM_TO_PRICE_VIEW = "fromtoprice";
	@GetMapping("fromtoprice")
	public final ModelAndView findFromToPrice() {
		final FromToPriceForm form = new FromToPriceForm();
		form.setFrom(BigDecimal.ZERO);
		form.setTo(BigDecimal.ZERO);
		
		return new ModelAndView(FROM_TO_PRICE_VIEW).addObject(form);
	}
	
//	@GetMapping(params = {"from", "to"})
//	ModelAndView findFromToPrice(final FromToPriceForm form) {LOGGER.info("OK OK");
//		return new ModelAndView(FROM_TO_PRICE_VIEW, "pizzas",
//				pizzaService.findByPriceBetween(form.getFrom(),  form.getTo()));
//	}
	
	@GetMapping(params = {"from", "to"})
	ModelAndView findFromToPrice(
			final FromToPriceForm form,
			final BindingResult bindingResult) {
		final ModelAndView modelAndView = new ModelAndView(FROM_TO_PRICE_VIEW);
		if (bindingResult.hasErrors()) {
			LOGGER.error("Input validation errors in form");
			return modelAndView;
		}
		
		final List<Pizza> pizzas
		= pizzaService.findByPriceBetween(form.getFrom(), form.getTo());
		
		if (pizzas.isEmpty())
			bindingResult.reject("noPizzasFound");
		else
			modelAndView.addObject("pizzas", pizzas);
		
		return modelAndView;
	}
}
