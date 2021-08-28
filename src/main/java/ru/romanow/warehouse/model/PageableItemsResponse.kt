package ru.romanow.warehouse.model

data class PageableItemsResponse(
    val page: Int = 0,
    val totalSize: Int = 0,
    val pageSize: Int = 0,
    val items: List<ItemsFullInfoResponse>? = null
)