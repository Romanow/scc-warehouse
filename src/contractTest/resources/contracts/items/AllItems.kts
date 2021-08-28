import org.springframework.cloud.contract.spec.ContractDsl.Companion.contract
import java.util.*

val legoTechnic42082ItemUid: UUID = UUID.fromString("667c15c8-09eb-4a53-8d4c-69ce70ba2ba9")
val legoTechnic42082Name: String = "Lego Technic 42082"

val legoTechnic42115ItemUid: UUID = UUID.fromString("61b6fff3-6192-4488-8622-3bd6402ee49f")
val legoTechnic42115Name: String = "Lego Technic 42115"

val legoTechnic9398ItemUid: UUID = UUID.fromString("d01e5c08-df58-4540-833e-022ecd654802")
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
