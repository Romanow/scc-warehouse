package ru.romanow.warehouse.model

import java.util.*
import javax.validation.constraints.NotEmpty

data class TakeItemsRequest(
    @field:NotEmpty(message = "{field.is.empty")
    val itemsUid: List<UUID>? = null
)