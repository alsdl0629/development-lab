package com.example.springboottransaction.propagation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final TransactionService transactionService;

    public MemberService(MemberRepository memberRepository, TransactionService transactionService) {
        this.memberRepository = memberRepository;
        this.transactionService = transactionService;
    }

    @Transactional
    public void buySomethingWithPropagationREQUIRED(Integer memberId, int amount) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        member.updateMoney(member.getMoney() - amount);

        transactionService.createTransactionHistoryWithPropagationREQUIRED(member, amount);

        throw new RuntimeException("새로운 트랜잭션이 끝난 후 예외발생");
    }

    @Transactional
    public void buySomethingWithPropagationWithPropagationREQUIRES_NEW(Integer memberId, int amount) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        member.updateMoney(member.getMoney() - amount);

        transactionService.createTransactionHistoryWithPropagationREQUIRES_NEW(member, amount);

        throw new RuntimeException("새로운 트랜잭션이 끝난 후 예외발생");
    }
}
