package com.dev.restfullapi.domain.repository;

import com.dev.restfullapi.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("select o from Order o left join fetch o.items")
    List<Order> findAllFetchItems();
}
