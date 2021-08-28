import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract
import java.util.*

val orderUid: UUID = UUID.fromString("3affedc8-7338-4f5c-9462-b3579ec84652")

contract {
    description = "Order already exists"
    request {
        url = url("/api/v1/items/$orderUid/take")
        method = POST
        headers {
            header(CONTENT_TYPE, APPLICATION_JSON)
        }
        body = body(
            mapOf(
                "itemsUid" to listOf(anyUuid)
            )
        )
    }
    response {
        status = CONFLICT
        headers {
            header(CONTENT_TYPE, APPLICATION_JSON)
        }
        body = body(
            mapOf(
                "message" to "OrderItem '$orderUid' already exists"
            )
        )
    }
}