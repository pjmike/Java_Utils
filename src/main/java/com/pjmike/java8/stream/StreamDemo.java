package com.pjmike.java8.stream;

import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @description:
 * @author: pjmike
 * @create: 2020/03/30
 */
public class StreamDemo {
    public static void main(String[] args) {
        // method invoke
    }
    public static void streamOperation() {
        List<String> list = Arrays.asList("a1", "a2", "b1", "c1", "c2");
        list.stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
    }

    public static void IntStream() {
        IntStream intStream = IntStream.range(1, 10);
        intStream.forEach(System.out::println);
    }

    public static void NoEndStream() {
        // 创建无限 Stream，使用 limit 截断
        Stream.generate(() -> "echo").limit(5).forEach(System.out::println);
        // 创建无限 Stream，使用 limit 截断
        Stream.iterate(3, i -> i * 2).limit(5).forEach(System.out::println);
    }

    public static int getLastElementOfStream() {
        int[] nums = new int[]{1, 3, 5, 7, 9};
        int lastElement = Arrays.stream(nums)
                .reduce((a, b) -> b)
                .orElseThrow(() -> new IllegalStateException("no last element"));
        System.out.println(lastElement);
        return lastElement;
    }

    public static void getStreamWithRepeatedElements() {
        Stream<String> repeatedStream = IntStream.range(0, 20)
                .mapToObj(i -> "x");
        repeatedStream.forEach(System.out::printf);
    }

    public static void map() {
        List<String> list = Arrays.asList("abc1", "abc2", "abc3");
        list.stream()
                .map(element -> element.substring(0, 3))
                .forEach(System.out::println);
    }

    public static void flapMap() {
        List<List<String>> names = Arrays.asList(
                Arrays.asList("pjmike", "pj"),
                Arrays.asList("Bob", "zhangshan"));
        List<String> result = names.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        result.forEach(System.out::println);
    }

    public static void reduce() {

        Optional<Integer> result1 = Stream.of(1, 2, 3, 4).reduce(Integer::sum);
        Integer result2 = Stream.of(1, 2, 3, 4).reduce(10, (x, y) -> x + y);
        System.out.println("result1: " + result1.get());
        System.out.println("result2: " + result2);

        List<User> users = Arrays.asList(new User("pj", 22), new User("pjmike", 21));
        Integer reduce = users.stream()
                .map(User::getAge)
                .reduce(0, Integer::sum);
        System.out.println(reduce);
        Integer ageSum = users.stream().reduce(0, (integer, user) -> integer + user.getAge(), Integer::sum);
        System.out.println("the sum of ages: " + ageSum);
    }


    public static void collect() {
        List<User> users = Arrays.asList(
                new User("pj", 20),
                new User("pjmike", 20),
                new User("bob", 22));
        Map<Integer, List<User>> map = users.stream()
                .collect(Collectors.groupingBy(user -> user.getAge()));
        map.forEach((age, user) -> System.out.printf("age %s: %s\n", age, user));
    }

    public static void distinct() {
        Stream.of(1, 2, 3, 4, 4, 5, 5, 6)
                .distinct()
                .forEach(System.out::println);
    }

    public static void parallelStream() {
        int sum = IntStream.range(1, 101)
                .parallel()
                .sum();
        System.out.println(sum);
    }


    @Data
    static
    class User {
        private String name;
        private int age;

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

    }
}
