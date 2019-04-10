package hh.swd20.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.Category;
import hh.swd20.bookstore.domain.BookRepository;
import hh.swd20.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	// String title, String author, Integer year, String isbn, String price, crepository.findbyName("kategoria").get(0)
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository crepository) {
		return (args) -> {
			crepository.save(new Category("Kauhu"));
			crepository.save(new Category("Jännitys"));
			
			repository.save(new Book("Kuinka käyttää 6 tuntia Javaan", "Janne Saikkonen", 2019, "WW22211", "20", crepository.findByName("Kauhu").get(0)));
			repository.save(new Book("Kuinka keittää ponua kellarissa", "Seppo Taalasmaa", 1999, "WW14563", "15", crepository.findByName("Jännitys").get(0)));
		};
	

	}
}

