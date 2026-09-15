package com.seleniumexpress.demo.reactivevsstream01;

import java.util.stream.Stream;

public class Java8StreamUtils {
    public static Stream<String> wordStream() {
        return Stream.of("Hello", "World","reactive","programming","fun","learning");
    }
}
