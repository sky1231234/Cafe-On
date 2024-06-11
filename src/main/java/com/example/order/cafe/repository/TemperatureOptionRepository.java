package com.example.order.cafe.repository;

import com.example.order.cafe.domain.TemperatureOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureOptionRepository extends JpaRepository<TemperatureOption, Long> {

}
