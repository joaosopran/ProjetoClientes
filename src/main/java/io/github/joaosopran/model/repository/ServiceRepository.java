package io.github.joaosopran.model.repository;

import io.github.joaosopran.model.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Integer> {

}
