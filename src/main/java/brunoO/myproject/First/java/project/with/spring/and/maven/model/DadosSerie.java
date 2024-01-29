package brunoO.myproject.First.java.project.with.spring.and.maven.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(String Title,
                         @JsonAlias("totalSeasons") int TotalSeasons,
                         String Runtime,
                         String Genre,
                         String Language,
                         @JsonAlias("imdbRating") double ImdbRating) {
}
