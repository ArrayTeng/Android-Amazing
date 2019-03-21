package com.example.build;

/**
 * @author tengfei
 */
public class People {
    private String name;
    private String age;
    private String favourite;

    public People(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.favourite = builder.favourite;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", favourite='" + favourite + '\'' +
                '}';
    }

    public static class Builder {

        private String name;
        private String age;
        private String favourite;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(String age) {
            this.age = age;
            return this;
        }

        public Builder favourite(String favourite) {
            this.favourite = favourite;
            return this;
        }

        public People build() {
            return new People(this);
        }
    }
}
