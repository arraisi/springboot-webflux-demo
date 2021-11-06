package io.abdul;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoFluxTest {

    @Test
    public void monoFluxTest() {
        Mono<?> monoString = Mono.just("Webflux Demo").log();

        monoString.subscribe(System.out::println, (error) -> {
            System.out.println(error.getMessage());
        });
    }

    @Test
    public void monoFluxErrorTest() {
        Mono<?> monoString = Mono.just("Webflux Demo")
                .then(Mono.error(new RuntimeException("Exception occurred")))
                .log();
        monoString.subscribe((value) -> {
            System.out.println(value);
        }, (error) -> {
            System.out.println(error.getMessage());
        });
    }

    @Test
    public void monoTest() {
        Mono<String> monoString = Mono.just("Spring")
                .log();

        StepVerifier.create(monoString)
                .expectNext("Spring")
                .verifyComplete();
    }

    @Test
    public void monoTestError() {
        StepVerifier.create(Mono.error(new RuntimeException("Error Occured")).log())
                .expectError()
                .verify();
    }

    @Test
    public void fluxElementsWithErrorTest() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Error occurred")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring Boot")
                .expectNext("Reactive Spring")
//                .expectError(RuntimeException.class)
                .expectErrorMessage("Error occurred")
                .verify();
    }

    @Test
    public void fluxElementsCountWithErrorTest() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Error occurred")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNextCount(3)
                .expectErrorMessage("Error occurred")
                .verify();
    }
}
