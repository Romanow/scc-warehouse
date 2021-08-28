package ru.romanow.warehouse.web

import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import ru.romanow.warehouse.domain.Items
import ru.romanow.warehouse.domain.OrderItems
import ru.romanow.warehouse.domain.enums.OrderState
import ru.romanow.warehouse.repository.ItemsRepository
import ru.romanow.warehouse.repository.OrderItemsRepository

class BaseTakeItemControllerTest : BaseContractTest() {

    @Autowired
    private lateinit var itemsRepository: ItemsRepository

    @Autowired
    private lateinit var orderItemsRepository: OrderItemsRepository

    @BeforeEach
    fun init() {
        val items = listOf(
            Items(uid = legoTechnic9398ItemUid, name = legoTechnic9398Name, count = 0),
            Items(uid = legoTechnic42082ItemUid, name = legoTechnic42082Name, count = 2),
            Items(uid = legoTechnic42115ItemUid, name = legoTechnic42115Name, count = 1),
        )
        itemsRepository.saveAll(items)

        orderItemsRepository.save(OrderItems(orderUid = orderUid, state = OrderState.READY_FOR_DELIVERY))
    }
}