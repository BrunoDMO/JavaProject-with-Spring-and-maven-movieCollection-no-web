package brunoO.myproject.First.java.project.with.spring.and.maven.model;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {
    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double avaliacao;
    private LocalDate dataLancamento;


    public Episodio(Integer temporada, DadosEpisodio dadosEpisodio) {
        this.temporada = temporada;
        this.titulo = dadosEpisodio.Title();
        this.numeroEpisodio = dadosEpisodio.Episode();
        try {
            this.avaliacao = Double.valueOf(dadosEpisodio.ImdbRating());
        }catch (NumberFormatException e) {
            this.avaliacao = 0.0;
        }
        try {
            this.dataLancamento = LocalDate.parse(dadosEpisodio.Released());
        }catch (DateTimeParseException e){
            this.dataLancamento = null;
        }
    }

    @Override
    public String toString() {
        return    "temporada=" + temporada +
                ", titulo='" + titulo + '\'' +
                ", numeroEpisodio=" + numeroEpisodio +
                ", avaliacao=" + avaliacao +
                ", dataLancamento=" + dataLancamento;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public Integer getTemporada() {
        return temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }
}
