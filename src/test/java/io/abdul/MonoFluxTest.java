package io.abdul;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void monoFluxTest() {
        Mono<?> monoString = Mono.just("Webflux Demo")
                .then(Mono.error(new RuntimeException("Exception occur")))
                .log();
        monoString.subscribe((value) -> {
            System.out.println(value);
        }, (error) -> {
            System.out.println(error.getMessage());
        });
    }
}
