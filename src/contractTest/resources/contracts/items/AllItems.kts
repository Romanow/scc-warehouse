import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract
import java.util.*

val legoTechnic42082ItemUid: UUID = UUID.fromString("7CB34608-5E6B-4DAA-898A-60E256944DCD")
val legoTechnic42082Name: String = "Lego Technic 42082"

val legoTechnic42115ItemUid: UUID = UUID.fromString("64A2CDBE-10F6-4668-A918-6AAAEFA258C5")
val legoTechnic42115Name: String = "Lego Technic 42115"

val legoTechnic9398ItemUid: UUID = UUID.fromString("E3BC005E-93C4-4294-9A32-1D6EFD9262AA")
val legoTechnic9398Name: String = "Lego Technic 9398"

contract {
    description = "Return all available items"
    request {
        url = url("/api/v1/items")
        method = GET
    }
    response {
        status = OK
        headers {
            header(CONTENT_TYPE, APPLICATION_JSON)
        }
        body = body(
            mapOf(
                "page" to 0,
                "totalSize" to 3,
                "pageSize" to 3,
                "items" to listOf(
                    mapOf(
                        "name" to legoTechnic9398Name,
                        "itemUid" to legoTechnic9398ItemUid,
                        "count" to 1
                    ),
                    mapOf(
                        "name" to legoTechnic42082Name,
                        "itemUid" to legoTechnic42082ItemUid,
                        "count" to 2
                    ),
                    mapOf(
                        "name" to legoTechnic42115Name,
                        "itemUid" to legoTechnic42115ItemUid,
                        "count" to 1
                    )
                )
            )
        )
    }
}
