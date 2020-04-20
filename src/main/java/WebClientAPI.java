import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class WebClientAPI {

    private WebClient webClient;

    public WebClientAPI() {
        //this.webClient = WebClient.create("http://localhost::8080/products");
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/crush")
                .build();
    }
    private Mono<ResponseEntity<Crush>> postNewCrush() {
        return webClient
                .post()
                .body(Mono.just(new Crush(null, "Rodrigo Hilbert", "Amo cozinhar pras minhas gatas", 35.0)), Crush.class)
                .exchange()
                .flatMap(clientResponse -> clientResponse.toEntity(Crush.class));

    }
}
