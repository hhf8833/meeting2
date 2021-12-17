package com.hhf.hellospringboot.entity;

import lombok.Data;

/**
 * @author HP
 * @Data:注解在类上，相当于同时使用了@ToString、@EqualsAndHashCode、@Getter、@Setter和@RequiredArgsConstrutor这些注解，对于POJO类十分有用
 * book实体类
 */

@Data
public class Book {
    private String name;
    private String description;

}
