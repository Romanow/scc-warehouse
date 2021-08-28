package ru.romanow.warehouse.web

import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import ru.romanow.warehouse.domain.Items
import ru.romanow.warehouse.repository.ItemsRepository

class BaseTakeItemControllerTest : BaseContractTest() {

    @Autowired
    private lateinit var itemsRepository: ItemsRepository

    @BeforeEach
    fun init() {
        val items = listOf(
            Items(uid = legoTechnic42082ItemUid, name = legoTechnic42082Name, count = 2),
            Items(uid = legoTechnic42115ItemUid, name = legoTechnic42115Name, count = 1),
        )
        itemsRepository.saveAll(items)
    }
}