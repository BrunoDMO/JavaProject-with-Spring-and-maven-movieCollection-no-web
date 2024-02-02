package brunoO.myproject.First.java.project.with.spring.and.maven;

import brunoO.myproject.First.java.project.with.spring.and.maven.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstJavaProjectWithSpringAndMavenApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FirstJavaProjectWithSpringAndMavenApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String apiKey = "&apikey=4d4da9d1";
		String endereco = "https://www.omdbapi.com/?t=";

		Principal principal = new Principal();
		principal.exibeMenu(endereco,apiKey);

	}
}
