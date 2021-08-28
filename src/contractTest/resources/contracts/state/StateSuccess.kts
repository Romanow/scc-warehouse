import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract
import java.util.*

val orderUid: UUID = UUID.fromString("3D8F39B4-02CA-44D7-A40D-8A2A5433400F")

val legoTechnic42082ItemUid: UUID = UUID.fromString("7CB34608-5E6B-4DAA-898A-60E256944DCD")
val legoTechnic42082Name: String = "Lego Technic 42082"

val legoTechnic42115ItemUid: UUID = UUID.fromString("64A2CDBE-10F6-4668-A918-6AAAEFA258C5")
val legoTechnic42115Name: String = "Lego Technic 42115"

contract {
    description = "Return order info"
    request {
        url = url("/api/v1/items/$orderUid/state")
        method = GET
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
