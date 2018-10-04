package be.vdab.pizzaluigi.web;

import java.time.LocalTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
class IndexController {

	@GetMapping
	public final String index() {
		int hour = LocalTime.now().getHour();
		
		if (hour < 12)
			return "Good morning";
		
		if (hour < 18)
			return "Good afternoon";
		
		return "Good evening";
	}
}
