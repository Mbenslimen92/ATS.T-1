package de.tritux.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;




@ComponentScan("de.tritux.db")
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		
        

		SpringApplication.run(Application.class, args);
	}

}











/*@SpringBootApplication
public class Application implements RepositoryRestConfigurer {
	@Autowired
	UserRepository userRepository;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(User.class);
        userRepository.save(new User(1,"med","benslimen","benslimen92@gmail.com", null));
    	
    	userRepository.findAll().forEach(p->{
    		System.out.println(p.toString());
    	});
    }
}
/*@SpringBootApplication
public class Application implements CommandLineRunner{
@Autowired
UserRepository userRepository;
@Autowired
private RepositoryRestConfiguration restConfiguration;
public static void main(String[] args) {
	SpringApplication.run(Application.class, args);

	
}
	

	
	@Override
	public void run(String... args) throws Exception {
		restConfiguration.exposeIdsFor(User.class);
	userRepository.save(new User(null,"med","benslimen","benslimen92@gmail.com", null));
	
	userRepository.findAll().forEach(p->{
		System.out.println(p.toString());
	
	});

	}
}*/