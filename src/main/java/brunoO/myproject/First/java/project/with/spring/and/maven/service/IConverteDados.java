package brunoO.myproject.First.java.project.with.spring.and.maven.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
