package hh.swd20.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.bookstore.domain.User;
import hh.swd20.bookstore.domain.UserRepository;
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
	public CommandLineRunner demo(BookRepository repository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			crepository.save(new Category("Kauhu"));
			crepository.save(new Category("Jännitys"));
			
			repository.save(new Book("Kuinka käyttää 6 tuntia Javaan", "Janne Saikkonen", 2019, "WW22211", "20", crepository.findByName("Kauhu").get(0)));
			repository.save(new Book("Kuinka keittää ponua kellarissa", "Seppo Taalasmaa", 1999, "WW14563", "15", crepository.findByName("Jännitys").get(0)));
			
			// Create users: admin/admin user/user (String username, String passwordHash, String email, String role)
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user@user.com", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "admin@admin.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
		};
	

	}
}

