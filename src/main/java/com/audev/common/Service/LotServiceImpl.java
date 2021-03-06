package com.audev.common.Service;

import com.audev.common.Entity.Lot;
import com.audev.common.Repository.LotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * LotServiceImpl
 */

@Service
public class LotServiceImpl implements LotService {

    @Autowired
    private LotRepository lotRepository;

    public Lot getOne(long id) {
        return lotRepository.findOne(id);
    }

    public List<Lot> getAll() {
        return lotRepository.findAll();
    }

    public void deleteOne(long id) {
        lotRepository.delete(id);
    }

    public void updateOne(long id) {
    }

    public void addOne(Lot lot) {
        lotRepository.save(lot);
    }

    public List<Lot> getBySearch(String s) {
        return lotRepository.findBySearchString(s);
    }

    @Override
    public Lot getOneByName(String name) {
        return lotRepository.findBylotName(name);
    }

    @Override
    public List<Lot> getLastSix() {
        return lotRepository.getLastSix();
    }
}
