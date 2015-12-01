package com.audev.common.Service;

import com.audev.common.Entity.Lot;
import com.audev.common.Repository.LotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cosxt on 01.12.2015.
 */

@Service
public class LotServiceImpl implements LotService {

    @Autowired
    private LotRepository lotRepository;

    public Lot getOne(long id) {
        return lotRepository.getOne(id);
    }

    public List<Lot> getAll() {
        return lotRepository.findAll();
    }

    public void deleteOne(long id) {
        lotRepository.delete(id);
    }

    public void updateOne(long id) {

    }
}
