package hh.swd20.bookstore.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class BookController {

	//LISTAUS INDEX.html
	@RequestMapping(value="/index", method= RequestMethod.GET)
	public String getBooks(Model model) {
		return "index";
	}
}
