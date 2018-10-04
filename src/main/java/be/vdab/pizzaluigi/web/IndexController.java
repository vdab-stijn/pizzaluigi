package be.vdab.pizzaluigi.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
class IndexController {

	@GetMapping
	public final String index() {
		return "/WEB-INF/JSP/index.jsp";
	}
}
