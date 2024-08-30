package com.example.springboottransaction.propagation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private int age;

    private int money;

    public Member(String name, int age, int money) {
        this.name = name;
        this.age = age;
        this.money = money;
    }

    public Member() {
    }

    public void updateMoney(int money) {
        this.money = money;
    }
}
