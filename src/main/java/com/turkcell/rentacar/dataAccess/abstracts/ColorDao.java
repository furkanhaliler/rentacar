package com.turkcell.rentacar.dataAccess.abstracts;

import com.turkcell.rentacar.entities.concretes.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;

public interface ColorDao extends JpaRepository<Color,Integer> {
    boolean existsColorByName(String name);
    boolean existsById(Integer id);
}
