package ru.romanow.warehouse.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.romanow.warehouse.domain.OrderItems
import java.util.*

interface OrderItemsRepository : JpaRepository<OrderItems, Int> {
    fun findByOrderUid(orderUid: UUID): Optional<OrderItems>
}