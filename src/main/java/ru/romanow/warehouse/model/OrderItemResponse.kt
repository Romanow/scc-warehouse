package ru.romanow.warehouse.model

import ru.romanow.warehouse.domain.enums.OrderState
import java.util.*

data class OrderItemResponse(
    val orderUid: UUID? = null,
    val state: OrderState? = null,
    val items: List<ItemsShortInfo>? = null
)