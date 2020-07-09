package com.kiroule.vaadin.bakeryapp.backend.repositories;

import com.kiroule.vaadin.bakeryapp.backend.data.entity.Capex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CapexRepository extends JpaRepository<Capex, Long> {

	Page<Capex> findBy(Pageable page);

	Page<Capex> findByNameLikeIgnoreCase(String name, Pageable page);

	int countByNameLikeIgnoreCase(String name);

}
