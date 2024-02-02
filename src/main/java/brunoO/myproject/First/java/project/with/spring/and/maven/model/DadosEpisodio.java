package brunoO.myproject.First.java.project.with.spring.and.maven.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio(String Title,
                            Integer Season,
                            Integer Episode,
                            String Released,
                            //String Runtime,
                            //String Genre,
                            //String Language,
                            @JsonAlias("imdbRating") String ImdbRating){
}
