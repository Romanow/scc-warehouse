package ru.romanow.scc.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.romanow.scc.warehouse.domain.OrderItems;

import java.util.Optional;
import java.util.UUID;

public interface OrderItemsRepository
        extends JpaRepository<OrderItems, Integer> {
    Optional<OrderItems> findByOrderUid(UUID orderUid);
}
