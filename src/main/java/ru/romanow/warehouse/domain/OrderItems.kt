package ru.romanow.warehouse.domain

import ru.romanow.warehouse.domain.enums.OrderState
import java.util.*
import javax.persistence.*

@Entity
@Table(
    name = "order_items",
    indexes = [Index(name = "idx_order_items_order_uid", columnList = "order_uid", unique = true)]
)
data class OrderItems(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(name = "order_uid", nullable = false, unique = true)
    var orderUid: UUID? = null,

    @ManyToMany
    @JoinTable(
        name = "order_items_cross",
        joinColumns = [JoinColumn(
            name = "order_item_id",
            foreignKey = ForeignKey(name = "fk_order_items_cross_order_item_id")
        )],
        inverseJoinColumns = [JoinColumn(
            name = "items_id",
            foreignKey = ForeignKey(name = "fk_order_items_cross_items_id")
        )],
        indexes = [
            Index(name = "idx_order_items_cross_order_item_id", columnList = "order_item_id"),
            Index(name = "idx_order_items_cross_items_id", columnList = "items_id")
        ]
    )
    var items: List<Items>? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    var state: OrderState? = null
) {
    override fun toString(): String {
        return "OrderItems(id=$id, orderUid=$orderUid, state=$state)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OrderItems

        if (orderUid != other.orderUid) return false
        if (state != other.state) return false

        return true
    }

    override fun hashCode(): Int {
        var result = orderUid?.hashCode() ?: 0
        result = 31 * result + (state?.hashCode() ?: 0)
        return result
    }
}