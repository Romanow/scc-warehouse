import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract
import java.util.*

val orderUid: UUID = UUID.fromString("47940b20-28f6-44ff-9ac1-47dfcc1db76c")

contract {
    description = "Requested items not found"
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