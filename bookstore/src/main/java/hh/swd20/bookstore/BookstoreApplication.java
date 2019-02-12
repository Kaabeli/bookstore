package hh.swd20.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
		Book book1 = new Book("Kuinka keittää ponua taloyhtiön kellarissa", "Seppo Taalasmaa", 1999, "123123", "1.99");
		Book book2 = new Book("Kuinka käyttää 6 tuntia Javatehtäviin tajuamatta importteja", "Janne Saikkonen", 2019, "111", "2");
		repository.save(book1);
		repository.save(book2);
		};
		

	}
}

