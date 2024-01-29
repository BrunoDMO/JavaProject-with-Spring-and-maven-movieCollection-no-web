package brunoO.myproject.First.java.project.with.spring.and.maven;

import brunoO.myproject.First.java.project.with.spring.and.maven.model.DadosSerie;
import brunoO.myproject.First.java.project.with.spring.and.maven.service.ConsumoAPI;
import brunoO.myproject.First.java.project.with.spring.and.maven.service.ConverteDados;
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
		//inicialize the API response getter
		ConsumoAPI consumoAPI = new ConsumoAPI();
		String endereco = "https://www.omdbapi.com/?t=gilmore+girls&apikey=4d4da9d1";

		//json receive the json response.body
		var json = consumoAPI.ObterDados(endereco);
//		System.out.println(json);

		//inicialize convert object and convert the json fields from API to the record model
		ConverteDados convert = new ConverteDados();
		DadosSerie dados = convert.obterDados(json, DadosSerie.class);
		System.out.println(dados);
		System.out.println("Ok!");
	}
}
