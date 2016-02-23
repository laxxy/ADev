package com.audev.common.Service;

import com.audev.common.Entity.Lot;

import java.util.List;

/**
 * Created by cosxt on 01.12.2015.
 */
public interface LotService {
    Lot getOne(long id);
    Lot getOneByName(String name);
    List<Lot> getAll();
    List<Lot> getLastSix();
    void deleteOne(long id);
    void updateOne(long id);
    void addOne(Lot lot);
    List<Lot> getBySearch(String s);
}
