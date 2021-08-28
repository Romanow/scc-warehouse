package ru.romanow.warehouse.web

import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.rules.TestName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import ru.romanow.warehouse.config.DatabaseTestConfiguration

@ActiveProfiles("test")
@SpringBootTest
@Transactional
@AutoConfigureTestEntityManager
@Import(DatabaseTestConfiguration::class)
abstract class BaseContractTest {

    @Autowired
    private lateinit var exceptionController: ExceptionController

    @Autowired
    private lateinit var warehouseController: WarehouseController

    @Rule
    var testName = TestName()

    @BeforeEach
    fun mockMvcInit() {
        val standaloneMockMvcBuilder = MockMvcBuilders
            .standaloneSetup(warehouseController)
            .setControllerAdvice(exceptionController)
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder)
    }
}