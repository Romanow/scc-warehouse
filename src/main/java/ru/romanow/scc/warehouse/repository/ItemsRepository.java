package ru.romanow.scc.warehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.romanow.scc.warehouse.domain.Items;

import java.util.List;
import java.util.UUID;

public interface ItemsRepository
        extends JpaRepository<Items, Integer> {

    @Query("select it from Items it where it.uid in :itemsUids")
    List<Items> findByUids(@Param("itemsUids") List<UUID> itemsUid);
}
