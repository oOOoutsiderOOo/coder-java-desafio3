package com.coder.desafio3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coder.desafio3.ClienteEdadCalculada;
import com.coder.desafio3.models.Cliente;
import com.coder.desafio3.services.ClienteService;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/todos")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(clienteService.getAllClientes());
    }

    @GetMapping("/calcular-edad/{id}")
    public ResponseEntity<?> calcularEdad(@PathVariable Long id) {

        ClienteEdadCalculada respuesta = clienteService.calcularEdad(id);
        if (respuesta == null) {
            return ResponseEntity.badRequest().body("No se encuentra el cliente con id: " + id);
        }
        return ResponseEntity.ok().body(respuesta);
    }

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@RequestBody Cliente cliente) {

        if (cliente.getNombre() == null || cliente.getApellido() == null
                || cliente.getFechaDeNacimiento() == null) {
            return ResponseEntity.badRequest().body("Todos los campos deben estar completos");
        }
        try {
            clienteService.nuevo(cliente);
            return ResponseEntity.ok().body(cliente);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al crear el cliente");
        }
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleException(HttpMessageNotReadableException e) {
        if (e.getMessage().contains("java.time.LocalDate")) {
            return ResponseEntity.badRequest().body("La fecha de nacimiento debe tener el formato aaaa-mm-dd");
        }
        return ResponseEntity.badRequest().body("Bad request");
    }

}
