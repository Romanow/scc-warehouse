package ru.romanow.warehouse.service

import org.springframework.data.domain.Pageable
import ru.romanow.warehouse.domain.OrderItems
import ru.romanow.warehouse.model.OrderItemResponse
import ru.romanow.warehouse.model.PageableItemsResponse
import ru.romanow.warehouse.model.TakeItemsRequest
import java.util.*

interface WarehouseService {
    fun getOrderByUid(orderUid: UUID): OrderItems
    fun items(pageable: Pageable): PageableItemsResponse
    fun orderItemState(orderUid: UUID): OrderItemResponse
    fun takeItems(orderUid: UUID, request: TakeItemsRequest): OrderItemResponse
    fun checkout(orderUid: UUID): OrderItemResponse
}