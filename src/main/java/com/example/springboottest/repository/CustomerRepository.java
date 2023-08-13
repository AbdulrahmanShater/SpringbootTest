package com.example.springboottest.repository;

import com.example.springboottest.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "select customer.* from customer,address,city where city.name =?1 and address.city_id = city.id and customer.id = address.customer_id group by customer.id", nativeQuery = true)
    List<Customer> findCustomersByCity(String name);

    List<Customer> findByPhoneNumberStartsWith(String prefix);
}
