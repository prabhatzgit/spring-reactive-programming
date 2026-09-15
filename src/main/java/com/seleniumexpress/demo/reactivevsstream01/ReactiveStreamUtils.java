package com.seleniumexpress.demo.reactivevsstream01;

import reactor.core.publisher.Flux;

public class ReactiveStreamUtils {
    // Java Reactive process or mechanism

    public static Flux<String> wordReactiveStream() {
        // Flux is a way to create data stream or source of data using reactive way
        return Flux.just("learning", "Spring", "Reactive", "by", "Selenium", "Express");

        // when this method calls, it returns a bunch of data
    }
}
