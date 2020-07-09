package com.kiroule.vaadin.bakeryapp.backend.service;

import com.kiroule.vaadin.bakeryapp.backend.data.entity.Capex;
import com.kiroule.vaadin.bakeryapp.backend.data.entity.User;
import com.kiroule.vaadin.bakeryapp.backend.repositories.CapexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CapexService implements FilterableCrudService<Capex> {

	private final CapexRepository productRepository;

	@Autowired
	public CapexService(CapexRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Page<Capex> findAnyMatching(Optional<String> filter, Pageable pageable) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return productRepository.findByNameLikeIgnoreCase(repositoryFilter, pageable);
		} else {
			return find(pageable);
		}
	}

	@Override
	public long countAnyMatching(Optional<String> filter) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return productRepository.countByNameLikeIgnoreCase(repositoryFilter);
		} else {
			return count();
		}
	}

	public Page<Capex> find(Pageable pageable) {
		return productRepository.findBy(pageable);
	}

	@Override
	public JpaRepository<Capex, Long> getRepository() {
		return productRepository;
	}

	@Override
	public Capex createNew(User currentUser) {
		return new Capex();
	}

	@Override
	public Capex save(User currentUser, Capex entity) {
		try {
			return FilterableCrudService.super.save(currentUser, entity);
		} catch (DataIntegrityViolationException e) {
			throw new UserFriendlyDataException(
					"There is already a product with that name. Please select a unique name for the product.");
		}

	}

}
