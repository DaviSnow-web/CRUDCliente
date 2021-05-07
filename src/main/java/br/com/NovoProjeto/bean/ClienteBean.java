/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.NovoProjeto.bean;

import br.com.NovoProjeto.dao.CidadeDAO;
import br.com.NovoProjeto.dao.ClienteDAO;
import br.com.NovoProjeto.dao.EstadoDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import br.com.NovoProjeto.domain.Cidade;
import br.com.NovoProjeto.domain.Cliente;
import br.com.NovoProjeto.domain.Estado;
import java.util.ArrayList;
import org.omnifaces.util.Messages;

/**
 *
 * @author davi
 */
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {

    private List<Estado> estados;
    private List<Cidade> cidades;

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    private Estado estado;

    private Cliente cliente;
    private List<Cliente> clientes;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setCliente(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @PostConstruct
    public void listar() {
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            clientes = clienteDAO.listar("nome");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar listar as cliente");
            erro.printStackTrace();
        }
    }

    public void novo() {
        try {

            cliente = new Cliente();
            EstadoDAO estadoDAO = new EstadoDAO();
            estados = estadoDAO.listar();

            cidades = new ArrayList<Cidade>();

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar gerar uma nova cliente");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        try {
            cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionada");

        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar selecionar um cliente");
        }
    }

    public void salvar() {
        try {
            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.merge(cliente);

            clientes = clienteDAO.listar("nome");

            cliente = new Cliente();

            Messages.addGlobalInfo("Cliente salvo com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar o cliente");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");
            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.excluir(cliente);
            clientes = clienteDAO.listar();

            Messages.addGlobalInfo("Cliente removido com sucesso! ");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o cliente");
            erro.printStackTrace();
        }
    }

    public void popular() {
        try {
            if (estado != null) {
                System.out.println("aqui"); 
                CidadeDAO cidadeDAO = new CidadeDAO();
                cidades = cidadeDAO.buscarPorEstado(estado.getCodigo());
            } else {
                cidades = new ArrayList<>();
            }
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar filtrar as cidades");
            erro.printStackTrace();
        }
    }
}
