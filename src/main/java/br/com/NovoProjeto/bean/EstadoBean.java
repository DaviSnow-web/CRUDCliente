package br.com.NovoProjeto.bean;

import br.com.NovoProjeto.dao.EstadoDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import br.com.NovoProjeto.domain.Estado;
import org.omnifaces.util.Messages;

@ManagedBean
@ViewScoped
public class EstadoBean implements Serializable {

    private Estado estado;
    private List<Estado> estados;

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    @PostConstruct
    public void listar() {
        try {
            EstadoDAO estadoDAO = new EstadoDAO();
            estados = estadoDAO.listar("nome");
        } catch (RuntimeException erro) {
            Messages.addGlobalError(" Ocorreu um erro ao tentar listar os estados ");
            erro.printStackTrace();
        }
    }

    public void novo() {
        estado = new Estado();
    }

    public void salvar() {
        try {
            EstadoDAO estadoDAO = new EstadoDAO();
            estadoDAO.merge(estado);

            novo();
            estados = estadoDAO.listar();

            Messages.addGlobalInfo("Estado salvo com sucesso");
        } catch (RuntimeException erro) {
            Messages.addGlobalError("Ocorreu um erro ao tentar salvar o estado");
            erro.printStackTrace();
        }

    }

    public void excluir(ActionEvent evento) {
        try {
            estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
            EstadoDAO estadoDAO = new EstadoDAO();
            estadoDAO.excluir(estado);
            estados = estadoDAO.listar();

            Messages.addGlobalInfo("Estado removido com sucesso! ");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o estado");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {

        estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
    }
}
