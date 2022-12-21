package com.coder.desafio3.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coder.desafio3.ClienteEdadCalculada;
import com.coder.desafio3.models.Cliente;
import com.coder.desafio3.repositories.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public ClienteEdadCalculada calcularEdad(Long id) {
        Cliente cliente = null;
        try {
            cliente = clienteRepository.findById(id).get();
        } catch (Exception e) {
            if (e.getMessage().contains("No value present")) {
                return null;
            }
        }
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaNacimiento = cliente.getFechaDeNacimiento();
        int edad = Period.between(fechaNacimiento, fechaActual).getYears();

        ClienteEdadCalculada clienteCalculado = new ClienteEdadCalculada(cliente.getNombre(), cliente.getApellido(),
                edad);

        return clienteCalculado;
    }

    public Cliente nuevo(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}
