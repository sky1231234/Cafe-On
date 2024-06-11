package com.example.order.cafe.repository;

import com.example.order.cafe.domain.CafeMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CafeMenuRepository extends JpaRepository<CafeMenu, Long> {

}
