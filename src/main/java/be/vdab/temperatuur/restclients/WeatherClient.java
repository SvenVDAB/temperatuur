package be.vdab.temperatuur.restclients;

import be.vdab.temperatuur.dto.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class WeatherClient {
    private final WebClient client;
    private final String uri;

    public WeatherClient(WebClient.Builder builder,
                         @Value("${reqres.user}") String uri) {
        client = builder.build();
        this.uri = uri;
    }

    public Optional<BigDecimal> findTemperatuurByGemeente(String gemeente) {
        try {
            return Optional.of(client.get()
                    .uri(uri, gemeente)
                    .retrieve()
                    .bodyToMono(Weather.class)
                    .block().main().temp());
        } catch (WebClientResponseException.NotFound ex) {
            return Optional.empty();
        }
    }
}
