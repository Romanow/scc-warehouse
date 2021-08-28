package ru.romanow.warehouse.web

import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import ru.romanow.warehouse.domain.Items
import ru.romanow.warehouse.domain.OrderItems
import ru.romanow.warehouse.domain.enums.OrderState
import ru.romanow.warehouse.repository.ItemsRepository
import ru.romanow.warehouse.repository.OrderItemsRepository

class BaseStateControllerTest : BaseContractTest() {

    @Autowired
    private lateinit var itemsRepository: ItemsRepository

    @Autowired
    private lateinit var orderItemsRepository: OrderItemsRepository

    @BeforeEach
    fun init() {
        val items = itemsRepository.saveAll(
            listOf(
                Items(uid = legoTechnic42082ItemUid, name = legoTechnic42082Name, count = 2),
                Items(uid = legoTechnic42115ItemUid, name = legoTechnic42115Name, count = 1),
            )
        )

        val orderItem = OrderItems(
            orderUid = orderUid,
            state = OrderState.READY_FOR_DELIVERY,
            items = items
        )
        orderItemsRepository.save(orderItem)
    }
}