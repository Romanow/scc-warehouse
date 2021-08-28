package ru.romanow.warehouse.model

import java.util.*

class ItemsFullInfoResponse(
    val count: Int? = null,
    itemUid: UUID? = null,
    name: String? = null
) : ItemsShortInfo(itemUid, name)