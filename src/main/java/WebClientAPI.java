import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class WebClientAPI {
    private WebClient webClient;

    public WebClientAPI() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/crushs")
                .build();
    }

    public static void main(String[] args) {
        WebClientAPI api = new WebClientAPI();

        api.postNewCrush()
                .thenMany(api.getAllCrushs())
                .take(1)
                .flatMap(crush -> api.updateCrush(crush.getId(), "Zac Efron", "Im sexy, an love girls who love HSM", 30.00))
                .flatMap(crush -> api.deleteCrush(crush.getId()))
                .thenMany(api.getAllCrushs())
                .thenMany(api.getAllEvents())
                .subscribe(System.out::println);
    }

    private Mono<ResponseEntity<Crush>> postNewCrush() {
        return webClient.post()
                .body(Mono.just(new Crush(null, "Rodrigo Santoro", "Love hot girls", 45.00)), Crush.class)
                .exchange()
                .flatMap(response -> response.toEntity(Crush.class))
                .doOnSuccess(o -> System.out.println("########## POST: " + o));
    }

    private Flux<Crush> getAllCrushs() {
        return webClient.get()
                .retrieve()
                .bodyToFlux(Crush.class)
                .doOnNext(o -> System.out.println("########## GET: " + o));
    }

    private Mono<Crush> updateCrush(String id, String name, String description, Double age) {
        return webClient.put()
                .uri("/{id}", id)
                .body(Mono.just(new Crush(null, name, description, age)), Crush.class)
                .retrieve()
                .bodyToMono(Crush.class)
                .doOnSuccess(o -> System.out.println("########## PUT: " + o));
    }

    private Mono<Void> deleteCrush(String id) {
        return webClient.delete()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnSuccess(o -> System.out.println("########## DELETE: " + o));
    }

    private Flux<CrushEvent> getAllEvents() {
        return webClient.get()
                .uri("/events")
                .retrieve()
                .bodyToFlux(CrushEvent.class);

    }


}
