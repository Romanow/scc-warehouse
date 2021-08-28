package ru.romanow.warehouse

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import ru.romanow.warehouse.config.DatabaseTestConfiguration
import ru.romanow.warehouse.web.WarehouseController

@ActiveProfiles("test")
@SpringBootTest
@Import(DatabaseTestConfiguration::class)
internal class WarehouseApplicationTest {

    @Autowired
    private lateinit var warehouseController: WarehouseController

    @Test
    fun test() {
        assertThat(warehouseController).isNotNull
    }
}