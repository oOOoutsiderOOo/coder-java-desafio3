package com.coder.desafio3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coder.desafio3.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
