package com.audev.common.Repository;

import com.audev.common.Entity.Lot;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by cosxt on 01.12.2015.
 */
public interface LotRepository extends JpaRepository<Lot, Long> {

    Lot findBylotName(String s);

}
