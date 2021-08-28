import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract
import java.util.*

val orderUid: UUID = UUID.fromString("3affedc8-7338-4f5c-9462-b3579ec84652")

val legoTechnic42082ItemUid: UUID = UUID.fromString("667c15c8-09eb-4a53-8d4c-69ce70ba2ba9")
val legoTechnic42082Name: String = "Lego Technic 42082"

val legoTechnic42115ItemUid: UUID = UUID.fromString("61b6fff3-6192-4488-8622-3bd6402ee49f")
val legoTechnic42115Name: String = "Lego Technic 42115"

contract {
    description = "Order successfully processed to delivery"
    request {
        url = url("/api/v1/items/$orderUid/checkout")
        method = POST
    }
    response {
        status = OK
        headers {
            header(CONTENT_TYPE, APPLICATION_JSON)
        }
        body = body(
            mapOf(
                "orderUid" to orderUid,
                "state" to "READY_FOR_DELIVERY",
                "items" to listOf(
                    mapOf(
                        "name" to legoTechnic42082Name,
                        "itemUid" to legoTechnic42082ItemUid
                    ),
                    mapOf(
                        "name" to legoTechnic42115Name,
                        "itemUid" to legoTechnic42115ItemUid
                    )
                )
            )
        )
    }
}
