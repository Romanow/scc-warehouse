import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract
import java.util.*

val orderUid: UUID = UUID.fromString("829e2fa0-162a-460b-8e21-6d3dcddb37b1")
val legoTechnic9398ItemUid: UUID = UUID.fromString("d01e5c08-df58-4540-833e-022ecd654802")

contract {
    description = "Try to take out of stock item"
    request {
        url = url("/api/v1/items/$orderUid/take")
        method = POST
        headers {
            header(CONTENT_TYPE, APPLICATION_JSON)
        }
        body = body(
            mapOf(
                "itemsUid" to listOf(legoTechnic9398ItemUid)
            )
        )
    }
    response {
        status = NOT_FOUND
        headers {
            header(CONTENT_TYPE, APPLICATION_JSON)
        }
        body = body(
            mapOf(
                "message" to "Items [$legoTechnic9398ItemUid] is empty (available count = 0)"
            )
        )
    }
}