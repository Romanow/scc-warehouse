package ru.romanow.warehouse.service

import com.google.common.base.Joiner.on
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.romanow.warehouse.domain.Items
import ru.romanow.warehouse.domain.OrderItems
import ru.romanow.warehouse.domain.enums.OrderState
import ru.romanow.warehouse.exceptions.EntityAvailableException
import ru.romanow.warehouse.exceptions.EntityNotFoundException
import ru.romanow.warehouse.exceptions.OrderItemAlreadyExistsException
import ru.romanow.warehouse.model.*
import ru.romanow.warehouse.repository.ItemsRepository
import ru.romanow.warehouse.repository.OrderItemsRepository
import java.util.*

@Service
class WarehouseServiceImpl(
    private val itemsRepository: ItemsRepository,
    private val orderItemsRepository: OrderItemsRepository
) : WarehouseService {

    @Transactional(readOnly = true)
    override fun getOrderByUid(orderUid: UUID): OrderItems {
        return orderItemsRepository
            .findByOrderUid(orderUid)
            .orElseThrow { EntityNotFoundException("OrderItem '$orderUid' not found") }
    }

    @Transactional(readOnly = true)
    override fun items(pageable: Pageable): PageableItemsResponse {
        val items = itemsRepository
            .findAll({ root, query, criteriaBuilder -> criteriaBuilder.greaterThan(root.get("count"), 0) }, pageable)
        val totalSize = items.totalElements.toInt()
        return PageableItemsResponse(
            page = pageable.pageNumber,
            pageSize = if (pageable.pageSize < totalSize) pageable.pageSize else totalSize,
            totalSize = totalSize,
            items = items.content.map { buildItemsFullInfoResponse(it) }
        )
    }

    @Transactional(readOnly = true)
    override fun orderItemState(orderUid: UUID): OrderItemResponse {
        val orderItems = getOrderByUid(orderUid)
        return buildOrderItemsResponse(orderItems)
    }

    @Transactional
    override fun takeItems(orderUid: UUID, request: TakeItemsRequest): OrderItemResponse {
        if (orderItemsRepository.findByOrderUid(orderUid).isPresent) {
            throw OrderItemAlreadyExistsException("OrderItem '$orderUid' already exists")
        }
        val itemUids = request.itemsUid!!
        val items: List<Items> = itemsRepository.findByUids(itemUids)
        if (items.size != itemUids.size) {
            throw EntityNotFoundException("Not all items [${on(",").join(itemUids)}] found")
        }
        val absentItems = items.filter { it.count == 0 }
        if (absentItems.isNotEmpty()) {
            throw EntityAvailableException("Items ${absentItems.map { it.uid }} is empty (available count = 0)")
        }
        var orderItems = OrderItems(
            state = OrderState.CREATED,
            orderUid = orderUid,
            items = items
        )
        orderItems = orderItemsRepository.save(orderItems)
        logger.info("Create orderItems '{}'", orderItems)
        items.forEach { it.decrementCount() }
        return buildOrderItemsResponse(orderItems)
    }

    @Transactional
    override fun checkout(orderUid: UUID): OrderItemResponse {
        val orderItems: OrderItems = getOrderByUid(orderUid)
        orderItems.state = OrderState.READY_FOR_DELIVERY
        logger.info("Update orderItem state: {}, '{}'", OrderState.READY_FOR_DELIVERY, orderItems)
        return buildOrderItemsResponse(orderItems)
    }


    private fun buildItemsFullInfoResponse(item: Items) =
        ItemsFullInfoResponse(
            count = item.count,
            itemUid = item.uid,
            name = item.name
        )

    private fun buildItemsShortInfoResponse(item: Items) =
        ItemsShortInfo(
            itemUid = item.uid,
            name = item.name
        )

    private fun buildOrderItemsResponse(orderItems: OrderItems) =
        OrderItemResponse(
            state = orderItems.state,
            orderUid = orderItems.orderUid,
            items = orderItems.items?.map { buildItemsShortInfoResponse(it) }
        )

    companion object {
        private val logger = LoggerFactory.getLogger(WarehouseServiceImpl::class.java)
    }
}