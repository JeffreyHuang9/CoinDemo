package com.example.springbootcoindesk.repository;

import com.example.springbootcoindesk.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, String> {
}
