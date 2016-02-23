package com.audev.common.Repository;

import com.audev.common.Entity.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *
 */
public interface LotRepository extends JpaRepository<Lot, Long> {

    Lot findBylotName(String s);

    @Query("select h from Lot h where lower(h.lotName) like CONCAT(:pattern,'%')")
    List<Lot> findBySearchString(@Param("pattern") String pattern);

    @Query(value = "select * from lot order by lot_id desc limit 6", nativeQuery = true)
    List<Lot> getLastSix();

}
