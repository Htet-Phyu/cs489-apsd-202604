package com.split.expense.repository;

import com.split.expense.entity.Group;
import com.split.expense.entity.Settlement;
import com.split.expense.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SettlementRepository extends JpaRepository<Settlement, UUID> {
    List<Settlement> findByGroup(Group group);
    List<Settlement> findByPayer(User payer);
    List<Settlement> findByPayee(User payee);
}
