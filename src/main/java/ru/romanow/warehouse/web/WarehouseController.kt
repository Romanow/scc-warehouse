package ru.romanow.warehouse.web

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springdoc.api.annotations.ParameterObject
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import ru.romanow.warehouse.model.ErrorResponse
import ru.romanow.warehouse.model.OrderItemResponse
import ru.romanow.warehouse.model.PageableItemsResponse
import ru.romanow.warehouse.model.TakeItemsRequest
import ru.romanow.warehouse.service.WarehouseService
import java.util.*
import javax.validation.Valid

@Tag(name = "Warehouse")
@RestController
@RequestMapping("/api/v1/items")
class WarehouseController(
    private val warehouseService: WarehouseService
) {

    @Operation(
        summary = "Show all available items",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "All available (count > 0) items",
                content = [Content(schema = Schema(implementation = PageableItemsResponse::class))]
            )
        ]
    )
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun items(@ParameterObject pageable: Pageable): PageableItemsResponse {
        return warehouseService.items(pageable)
    }

    @Operation(
        summary = "Get information by order",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Order state information",
                content = [Content(schema = Schema(implementation = OrderItemResponse::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "Order not found",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    @GetMapping(value = ["/{orderUid}/state"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun orderItemState(@PathVariable orderUid: UUID): OrderItemResponse {
        return warehouseService.orderItemState(orderUid)
    }

    @Operation(
        summary = "Create order and take items from warehouse",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Order successfully created",
                content = [Content(schema = Schema(implementation = OrderItemResponse::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "Requested item not found",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "Item not available (count == 0)",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            ),
            ApiResponse(
                responseCode = "409",
                description = "Order already created",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
        value = ["/{orderUid}/take"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun takeItems(@PathVariable orderUid: UUID, @Valid @RequestBody request: TakeItemsRequest): OrderItemResponse {
        return warehouseService.takeItems(orderUid, request)
    }

    @Operation(
        summary = "Prepare order for delivery",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Order state changed to Ready For Delivery",
                content = [Content(schema = Schema(implementation = OrderItemResponse::class))]
            ),
            ApiResponse(
                responseCode = "404",
                description = "Order not found",
                content = [Content(schema = Schema(implementation = ErrorResponse::class))]
            )
        ]
    )
    @PostMapping(value = ["/{orderUid}/checkout"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun checkout(@PathVariable orderUid: UUID): OrderItemResponse {
        return warehouseService.checkout(orderUid)
    }
}