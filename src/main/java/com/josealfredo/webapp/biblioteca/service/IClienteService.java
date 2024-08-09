package com.josealfredo.webapp.biblioteca.service;

import java.util.List;

import com.josealfredo.webapp.biblioteca.model.Cliente;

public interface IClienteService {

    public List<Cliente> listaClientes();

    public Cliente buscarClienteporId(Long dpi);

    public Cliente guardarCliente(Cliente cliente);

    public void eliminarCliente(Cliente cliente); 

}
