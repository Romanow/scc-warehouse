import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract
import java.util.*

val orderUid: UUID = UUID.fromString("3D8F39B4-02CA-44D7-A40D-8A2A5433400F")

val legoTechnic42082ItemUid: UUID = UUID.fromString("7CB34608-5E6B-4DAA-898A-60E256944DCD")
val legoTechnic42082Name: String = "Lego Technic 42082"

val legoTechnic42115ItemUid: UUID = UUID.fromString("64A2CDBE-10F6-4668-A918-6AAAEFA258C5")
val legoTechnic42115Name: String = "Lego Technic 42115"

contract {
    description = "Take items (create OrderItem and decrement available items count)"
    request {
        url = url("/api/v1/items/$orderUid/checkout")
        method = POST
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
        status = OK
        headers {
            header(CONTENT_TYPE, APPLICATION_JSON)
        }
        body = body(
            mapOf(
                "orderUid" to orderUid,
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
