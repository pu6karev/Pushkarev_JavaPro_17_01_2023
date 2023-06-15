package ua.ithillel.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrencyAPITest {

    private static WireMockServer wireMockServer;

    @BeforeAll
    public static void setup() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());

        // Определение имитации POST-запроса и ответа
        stubFor(post(urlEqualTo("/v3/latest"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"USD\" : \"1\", \"EUR\" : \"0.97\"}")));

        stubFor(get(urlEqualTo("/v3/latest"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"USD\" : \"1\", \"EUR\" : \"0.97\"}")));
    }

    @AfterAll
    public static void teardown() {
        wireMockServer.stop();
    }

    @Test
    public void testCurrencyAPI() throws IOException, InterruptedException {
        // Ваш код теста здесь
        // Например, используя HttpClient или другую библиотеку, отправляем POST-запрос
        // для передачи данных в нужном формате
        // Затем, отправляем GET-запрос и проверяем, что полученные данные соответствуют ожиданиям

        // Создание экземпляра HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Отправка POST-запроса для передачи данных
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + wireMockServer.port() + "/v3/latest"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"USD\" : \"1\", \"EUR\" : \"0.97\"}"))
                .build();
        HttpResponse<String> postResponse = client.send(postRequest, HttpResponse.BodyHandlers.ofString());

        // Отправка GET-запроса для получения данных
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + wireMockServer.port() + "/v3/latest"))
                .GET()
                .build();
        HttpResponse<String> getResponse = client.send(getRequest, HttpResponse.BodyHandlers.ofString());

        // Проверка, что полученные данные соответствуют ожиданиям
        String expectedResponse = "{\"USD\" : \"1\", \"EUR\" : \"0.97\"}";
        assertEquals(expectedResponse, getResponse.body());
    }
}