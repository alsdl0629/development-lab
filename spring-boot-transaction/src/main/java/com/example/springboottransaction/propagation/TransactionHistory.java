package com.example.springboottransaction.propagation;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Member member;

    private int amount;

    public TransactionHistory(Member member, int amount) {
        this.member = member;
        this.amount = amount;
    }

    public TransactionHistory() {
    }
}
