/*
package com.seleniumexpress.demo.reactivevsstream01;

uncomment and practice

public class Java8AndReactiveAppStreamUtils {
    public static void main(String[] args) {

        // CASE1:
        // this is a pure Java Stream: It is a way where process huge amount of data
        // Consume data from Java8 Stream
        {
            Java8StreamUtils.wordStream().forEach(word -> System.out.println(word));
        }

        // Consume data from Reactive Stream

        // Flux<String> Publisher - get stream of data -> "learning", "Spring", "Reactive", "by", "Selenium", "Express"
        // Publisher return by wordReactiveStream()
        // I want to get all data from wordReactiveStream()
        // forEach accept consumer, same way subscribe also accept consumer

        ReactiveStreamUtils.wordReactiveStream().subscribe(word -> System.out.println(word));

        // CASE2: convert string in array to upper case
        Java8StreamUtils
                .wordStream()
                .map(word -> word.toUpperCase())
                .forEach(word -> System.out.println(word));

        ReactiveStreamUtils
                .wordReactiveStream()
                .map(word -> word.toUpperCase())
                .subscribe(word -> System.out.println(word));
    }
}
*/
