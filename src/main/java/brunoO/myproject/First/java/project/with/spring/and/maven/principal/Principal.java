package brunoO.myproject.First.java.project.with.spring.and.maven.principal;

import brunoO.myproject.First.java.project.with.spring.and.maven.model.DadosEpisodio;
import brunoO.myproject.First.java.project.with.spring.and.maven.model.DadosSerie;
import brunoO.myproject.First.java.project.with.spring.and.maven.model.DadosTemporada;
import brunoO.myproject.First.java.project.with.spring.and.maven.model.Episodio;
import brunoO.myproject.First.java.project.with.spring.and.maven.service.ConsumoAPI;
import brunoO.myproject.First.java.project.with.spring.and.maven.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Principal {
    private ConsumoAPI consumo = new ConsumoAPI();

    public void exibeMenu(String ENDERECO, String APIKEY){
        ConverteDados converteDados = new ConverteDados();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite nome da série: ");
        var nomeSerie = scanner.nextLine();
        String endereco = ENDERECO + nomeSerie.replace(" ","+")+ APIKEY;

        //get data from API and convert to DadosSerie.class
        var json = consumo.ObterDados(endereco);
        DadosSerie dados = converteDados.obterDados(json,DadosSerie.class);
        //System.out.println(dados);

        List<DadosTemporada> listaTemporada = new ArrayList<>();
        for (int i=1 ; i<= dados.TotalSeasons(); i++ ) {
            endereco = ENDERECO + nomeSerie.replace(" ","+")+ "&season="+i + APIKEY;
            json = consumo.ObterDados(endereco);
            DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
            listaTemporada.add(dadosTemporada);
        }
//        listaTemporada.forEach(System.out::println);
//
//        for (int i = 0; i < dados.TotalSeasons(); i++ ) {
//            List<DadosEpisodio> episodiosTemporada = listaTemporada.get(i).episodios();
//            for (int j = 0; j < episodiosTemporada.size() ; j++){
//                System.out.println(episodiosTemporada.get(j).Title() + " Season = " +(i+1));
//            }
//        }
//       /\This double "for" is equal to this \/
//        listaTemporada.forEach(t -> t.episodios().forEach(e -> System.out.println(e.Title())));
//
//        List<DadosEpisodio> list = listaTemporada.stream()
//                .flatMap(t -> t.episodios().stream())
//                .collect(Collectors.toList());

        //Print list of ImdbRating rank sorted
//        list.stream()
//                .sorted(Comparator.comparing(DadosEpisodio::ImdbRating).reversed())
//                .limit(5)
//                .forEach(System.out::println);

        List<Episodio> episodios = listaTemporada.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.temporada(),d)))
                .collect(Collectors.toList());
//        episodios.forEach(System.out::println);

//        System.out.println("A partir de que ano você deseja ver os episódios? ");
//        var ano = scanner.nextInt();
//        scanner.nextLine();
//
//        LocalDate dataBusca = LocalDate.of(ano, 1, 1);
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
//
//
//        episodios.stream()
//                .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
//                .forEach(e -> System.out.println(
//                        "Temporada = " + e.getTemporada() +
//                                " Episódio: " + e.getTitulo() +
//                                " Data lançamento: " + e.getDataLancamento().format(dateTimeFormatter)
//                ));
        Map<Integer, Double> avaliacoesPorTemporada = episodios.stream()
                .filter(episodio -> episodio.getAvaliacao() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getAvaliacao)));
        System.out.println(avaliacoesPorTemporada);

    }
}
