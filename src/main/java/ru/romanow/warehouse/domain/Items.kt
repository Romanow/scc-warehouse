package ru.romanow.warehouse.domain

import java.util.*
import javax.persistence.*

@Entity
@Table(
    name = "items",
    indexes = [
        Index(name = "idx_items_uid", columnList = "uid", unique = true),
        Index(name = "idx_items_name", columnList = "name", unique = true)
    ]
)
data class Items(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(name = "uid", nullable = false, unique = true, updatable = false)
    var uid: UUID? = null,

    @Column(name = "name", length = 1024, nullable = false, unique = true)
    var name: String? = null,

    @Column(name = "count", nullable = false)
    var count: Int? = null,

    @ManyToMany(mappedBy = "items")
    var orderItems: List<OrderItems>? = null,
) {
    fun decrementCount(): Int {
        count = (count ?: 1) - 1
        return count!!
    }

    override fun toString(): String {
        return "Items(id=$id, uid=$uid, name=$name, count=$count)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Items

        if (uid != other.uid) return false
        if (name != other.name) return false
        if (count != other.count) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uid?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (count ?: 0)
        return result
    }
}
