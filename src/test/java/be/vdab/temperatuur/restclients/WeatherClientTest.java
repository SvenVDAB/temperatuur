package be.vdab.temperatuur.restclients;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class WeatherClientTest {
    private final WeatherClient client;

    public WeatherClientTest(WeatherClient client) {
        this.client = client;
    }

    @Test
    void findBestaandeGemeente() {
        assertThat(client.findTemperatuurByGemeente("Brussels"))
                .hasValueSatisfying(
                        temp -> {
                            assertThat(temp.compareTo(BigDecimal.valueOf(-60)))
                                    .isGreaterThanOrEqualTo(0);
                            assertThat(temp.compareTo(BigDecimal.valueOf(60))).isLessThanOrEqualTo(0);
                        }
                );
    }

    @Test
    void findOnbestaandeGemeente() {
        assertThat(client.findTemperatuurByGemeente("Jakamakka")).isEmpty();
    }
}

