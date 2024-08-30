package com.example.springboottransaction.propagation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberServiceTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @AfterEach
    void tearDown() {
        transactionHistoryRepository.deleteAllInBatch();
    }

    @DisplayName("Propagation이 REQUIRED인 트랜잭션이 걸린 함수를 호출하고, 해당 트랜잭션이 끝난 다음 진행 중인 트랜잭션에서 예외가 발생하면 롤백이 되는지 확인한다")
    @Test
    void propagation_REQUIRED_test() {
        // when
        try {
            memberService.buySomethingWithPropagationREQUIRED(6, 20_000);
        } catch (RuntimeException ignored) {
        }

        // then
        List<TransactionHistory> transactionHistories = transactionHistoryRepository.findAll();
        assertThat(transactionHistories).hasSize(0);
    }

    @DisplayName("Propagation이 REQUIRES_NEW인 트랜잭션이 걸린 함수를 호출하고, 해당 트랜잭션이 끝난 다음 진행 중인 트랜잭션에서 예외가 발생하면 롤백이 되는지 확인한다")
    @Test
    void propagation_REQUIRES_NEW_test() {
        // when
        try {
            memberService.buySomethingWithPropagationWithPropagationREQUIRES_NEW(6, 20_000);
        } catch (RuntimeException ignored) {
        }

        // then
        List<TransactionHistory> transactionHistories = transactionHistoryRepository.findAll();
        assertThat(transactionHistories).hasSize(1);
    }
}