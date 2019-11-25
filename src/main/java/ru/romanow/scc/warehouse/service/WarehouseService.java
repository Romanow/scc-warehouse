package ru.romanow.scc.warehouse.service;

import ru.romanow.scc.warehouse.domain.OrderItems;
import ru.romanow.scc.warehouse.model.OrderItemResponse;
import ru.romanow.scc.warehouse.model.PageableItemsResponse;
import ru.romanow.scc.warehouse.model.TakeItemsRequest;

import javax.annotation.Nonnull;
import java.util.UUID;

public interface WarehouseService {

    @Nonnull
    OrderItems getOrderByUid(@Nonnull UUID orderUid);

    @Nonnull
    PageableItemsResponse items(@Nonnull Integer page, @Nonnull Integer size);

    @Nonnull
    OrderItemResponse orderItemState(@Nonnull UUID orderUid);

    @Nonnull
    OrderItemResponse takeItems(@Nonnull UUID orderUid, @Nonnull TakeItemsRequest request);

    @Nonnull
    OrderItemResponse checkout(@Nonnull UUID orderUid);
}
