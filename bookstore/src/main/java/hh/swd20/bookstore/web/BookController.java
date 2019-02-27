package hh.swd20.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;
import hh.swd20.bookstore.domain.CategoryRepository;

@Controller
public class BookController {

	//AUTOWIRED
	@Autowired
	private BookRepository repository;
	@Autowired CategoryRepository Crepository;

	//BOOKLIST
	@RequestMapping(value="/booklist")
	public String getBooks(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	//LISÄTÄÄN NIITTÄ KIRJOJA JA IMPORTATAAN domain.book
	@RequestMapping(value="/addbooks")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", Crepository.findAll());
		return "addbooks";
	}
	//LISÄYKSEN JÄLKEEN /SAVE PALAUTTAA KÄYTTÄJÄN SAVEN JLK TAKAISIN BOOKLIST.html
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveBook(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}
	//DELETOINTI JA PALUU BOOKLISTIIN
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		repository.deleteById(id);
		return "redirect:../booklist";
	}
	//EDITOINTI
	@RequestMapping(value="/edit/{id}")
	public String editBook(@PathVariable("id") Long id, Model model) {
		repository.findById(id);
		model.addAttribute("book");
		return "editbook";
	}

}

