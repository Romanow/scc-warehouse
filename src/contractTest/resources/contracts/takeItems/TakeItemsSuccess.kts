import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract
import java.util.*

val orderUid: UUID = UUID.randomUUID()

val legoTechnic42082ItemUid: String = "667c15c8-09eb-4a53-8d4c-69ce70ba2ba9"
val legoTechnic42082Name: String = "Lego Technic 42082"

val legoTechnic42115ItemUid: String = "61b6fff3-6192-4488-8622-3bd6402ee49f"
val legoTechnic42115Name: String = "Lego Technic 42115"

contract {
    description = "Take items (create OrderItem and decrement available items count)"
    request {
        url = url(value(client("/api/v1/items/$anyUuid/take"), server("/api/v1/items/$orderUid/take")))
        method = POST
        headers {
            header(CONTENT_TYPE, APPLICATION_JSON)
        }
        body = body(
            mapOf(
                "itemsUid" to value(
                    server(listOf(legoTechnic42082ItemUid, legoTechnic42115ItemUid)),
                    client(listOf(anyUuid, anyUuid))
                )
            )
        )
    }
    response {
        status = CREATED
        headers {
            header(CONTENT_TYPE, APPLICATION_JSON)
        }
        body = body(
            mapOf(
                "orderUid" to fromRequest().path(3),
                "state" to "CREATED",
                "items" to listOf(
                    mapOf(
                        "name" to legoTechnic42082Name,
                        "itemUid" to fromRequest().body("$.itemsUid[0]")
                    ),
                    mapOf(
                        "name" to legoTechnic42115Name,
                        "itemUid" to fromRequest().body("$.itemsUid[1]")
                    )
                )
            )
        )
    }
}
