import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract
import java.util.*

val orderUid: UUID = UUID.fromString("36856fc6-d6ec-47cb-bbee-d20e78299eb9")

contract {
    description = "OrderItem not found"
    request {
        url = url("/api/v1/items/$orderUid/state")
        method = GET
    }
    response {
        status = NOT_FOUND
        headers {
            header(CONTENT_TYPE, APPLICATION_JSON)
        }
        body = body(
            mapOf(
                "message" to "OrderItem '$orderUid' not found"
            )
        )
    }
}
