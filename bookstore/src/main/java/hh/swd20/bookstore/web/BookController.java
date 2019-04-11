package hh.swd20.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;
import hh.swd20.bookstore.domain.Category;
import hh.swd20.bookstore.domain.CategoryRepository;

@Controller
public class BookController {

	//AUTOWIRED
	@Autowired
	private BookRepository repository;
	@Autowired 
	private CategoryRepository Crepository;
	
	//LOGIN
	@RequestMapping(value= {"/login", "/"})
	public String login() {
		return "login";
	}

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
		model.addAttribute("book", repository.findById(id));
		model.addAttribute("categories", Crepository.findAll());
		return "editbook";
	}
	//RESTful service to get all books
	@RequestMapping(value="/books", method = RequestMethod.GET) 
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) repository.findAll();
	}
	//RESTful service to get book by id
	@RequestMapping(value="/books/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long id) {
		return repository.findById(id);
	}
	//RESTful service to get all categories
	@RequestMapping(value="/categories", method = RequestMethod.GET) 
	public @ResponseBody List<Category> categoryListRest() {
		return (List<Category>) Crepository.findAll();
	}
	//RESTful service to get categories by id
	@RequestMapping(value="/categories/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Category> findCategoryRest(@PathVariable("categoryid") Long id) {
		return Crepository.findById(id);
	}
}

