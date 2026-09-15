/*
package com.seleniumexpress.demo.reactivevsstream02;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        // how stream api works
        Stream<String> stringStream = Stream.of("fun","learning", "reactive","programming");
        //Q1 is above stringStream prints on the console if we try to print
        System.out.println("stringStream --------> " + stringStream);

        // streams are lazy and we can print with help of terminal operations
        stringStream.forEach(word -> System.out.println(word));
        // we can add terminal operator to the stream once stream is ready
        // once the stream is constructed, then only we can consume it
        // here forEach is pulling the data from stream pipeline one by one (sequentially)

        // Q2:
        Stream.of(1,2,3,4,5,6,7,8,9)
                .peek(no -> System.out.println("\nA" + no))
                .peek(no -> System.out.println("B" + no))
                .forEach(no -> System.out.println("C"+no));

        // Q3:
        Stream.of(1,2,3,4,5,6,7,8,9)
                .peek(no -> System.out.println("\nA" + no))
                .limit(4)
                .peek(no -> System.out.println("B" + no))
                .forEach(no -> System.out.println("C"+no));
        System.out.println("-----------------------------------------");
        // Q4:
        Stream.of(1,2,3,4,5,6,7,8,9)
                .peek(no -> System.out.print("\nA" + no))
                .skip(4)
                .peek(no -> System.out.print("B" + no))
                .forEach(no -> System.out.print("C"+no));

        Stream.of("one", "two", "three", "four")
                .filter(e -> e. length() > 3)
                .peek(e -> System. out. println("Filtered value: " + e)) // "three", "four"
                .map(String::toUpperCase) // THREE FOUR
                .peek(e -> System. out. println("Mapped value: " + e))
                .collect(Collectors. toList());
    }
}
*/
