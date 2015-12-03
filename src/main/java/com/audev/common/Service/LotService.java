package com.audev.common.Service;

import com.audev.common.Entity.Lot;

import java.util.List;

/**
 * Created by cosxt on 01.12.2015.
 */
public interface LotService {
    public Lot getOne(long id);
    public List<Lot> getAll();
    public void deleteOne(long id);
    public void updateOne(long id);
    public void addOne(Lot lot);
}
