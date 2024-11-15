package com.thinne.backend.repositories;

import com.thinne.backend.models.Address;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {
    void save(Address address);
    List<Address> findAll();
}
