import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract
import java.util.*

val orderUid: UUID = UUID.randomUUID()

contract {
    description = "Requested items not found"
    request {
        url = url(value(client("/api/v1/items/$anyUuid/take"), server("/api/v1/items/$orderUid/take")))
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
        status = NOT_FOUND
        headers {
            header(CONTENT_TYPE, APPLICATION_JSON)
        }
        body = body(
            mapOf(
                "message" to "Not all items [" + fromRequest().body("$.itemsUid[0]") + "] found"
            )
        )
    }
}