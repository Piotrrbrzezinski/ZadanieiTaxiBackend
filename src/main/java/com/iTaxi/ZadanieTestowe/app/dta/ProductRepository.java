package com.iTaxi.ZadanieTestowe.app.dta;

import com.iTaxi.ZadanieTestowe.app.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
