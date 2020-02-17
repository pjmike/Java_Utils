package com.pjmike.id;

/**
 * @description:
 * @author: pjmike
 * @create: 2020/02/17
 */
public class RandomIdGeneratorTest {
    public static void main(String[] args) throws IdGenerationFailureException {
        IdGenerator idGenerator = new RandomIdGenerator();
        String id = idGenerator.generate();
        System.out.println("id : " + id);
    }
}
