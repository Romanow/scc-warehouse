package ru.romanow.warehouse.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import ru.romanow.warehouse.domain.Items
import java.util.*

interface ItemsRepository : JpaRepository<Items, Int>, JpaSpecificationExecutor<Items> {

    @Query("select it from Items it where it.uid in :itemsUids")
    fun findByUids(@Param("itemsUids") itemsUid: List<UUID>): List<Items>
}