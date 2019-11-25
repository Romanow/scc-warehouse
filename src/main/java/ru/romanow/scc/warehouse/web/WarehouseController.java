package ru.romanow.scc.warehouse.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.romanow.scc.warehouse.model.OrderItemResponse;
import ru.romanow.scc.warehouse.model.PageableItemsResponse;
import ru.romanow.scc.warehouse.model.TakeItemsRequest;
import ru.romanow.scc.warehouse.service.WarehouseService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class WarehouseController {
    private final WarehouseService warehouseService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PageableItemsResponse items(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "0") Integer size) {
        return warehouseService.items(page, size);
    }

    @GetMapping(value = "/{orderUid}/state", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public OrderItemResponse orderItemState(@PathVariable UUID orderUid) {
        return warehouseService.orderItemState(orderUid);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{orderUid}/take",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public OrderItemResponse takeItems(@PathVariable UUID orderUid, @Valid @RequestBody TakeItemsRequest request) {
        return warehouseService.takeItems(orderUid, request);
    }

    @PostMapping(value = "/{orderUid}/checkout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public OrderItemResponse checkout(@PathVariable UUID orderUid) {
        return warehouseService.checkout(orderUid);
    }
}
