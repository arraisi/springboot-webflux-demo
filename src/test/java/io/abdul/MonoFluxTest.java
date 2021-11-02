package io.abdul;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void monoFluxTest() {
        Mono<String> monoString = Mono.just("Webflux Demo").log();
        monoString.subscribe(System.out::println);
    }
}
