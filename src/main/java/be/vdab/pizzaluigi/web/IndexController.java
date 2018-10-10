package be.vdab.pizzaluigi.web;

import java.time.LocalTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.pizzaluigi.valueobjects.Address;
import be.vdab.pizzaluigi.valueobjects.Person;

@Controller
@RequestMapping("/")
class IndexController {

	@GetMapping
	public final ModelAndView index() {
		String boodschap = "Good evening";
		
		int hour = LocalTime.now().getHour();
		
		if (hour < 12)
			boodschap = "Goodmorning";
		else if (hour < 18)
			boodschap = "Good afternoon";
		
		final ModelAndView modelAndView
		= new ModelAndView("index", "welcomeMessage", boodschap);
		
		modelAndView.addObject("unluckyNumber", 13);
		modelAndView.addObject("manager",
				new Person("Luigi", "Peperone", 7, true,
						new Address("Grote Markt", "3", 9700, "Oudenaarde")));
		
		return modelAndView;
	}
}
