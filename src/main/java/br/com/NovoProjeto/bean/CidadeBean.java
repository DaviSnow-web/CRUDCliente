package br.com.NovoProjeto.bean;


import br.com.NovoProjeto.dao.CidadeDAO;
import br.com.NovoProjeto.dao.EstadoDAO;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;


import javax.faces.event.ActionEvent;
import br.com.NovoProjeto.domain.Cidade;
import br.com.NovoProjeto.domain.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable {

    private Cidade cidade;
    private List<Cidade> cidades;
    private List<Estado> estados;

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    @PostConstruct
    public void listar() {
        try {
            CidadeDAO cidadeDAO = new CidadeDAO();
            cidades = cidadeDAO.listar("nome");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as cidades");
            erro.printStackTrace();
        }
    }

    public void novo() {
        try {
            cidade = new Cidade();

            EstadoDAO estadoDAO = new EstadoDAO();
            estados = estadoDAO.listar("nome");

        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar gerar uma nova cidade");
            erro.printStackTrace();
        }
    }

    public void salvar() {
        try {
            CidadeDAO cidadeDAO = new CidadeDAO();
            cidadeDAO.merge(cidade);

            cidade = new Cidade();

            EstadoDAO estadoDAO = new EstadoDAO();
            estados = estadoDAO.listar();

            cidades = cidadeDAO.listar();

            Messages.addGlobalInfo("Cidade salva com sucesso");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar uma nova cidade");
            erro.printStackTrace();
        }
    }

    public void excluir(ActionEvent evento) {
        try {
            cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");

            CidadeDAO cidadeDAO = new CidadeDAO();
            cidadeDAO.excluir(cidade);

            cidades = cidadeDAO.listar();

            Messages.addGlobalInfo("Cidade removida com sucesso");
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover a cidade");
            erro.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        try {
            cidade = (Cidade) evento.getComponent().getAttributes().get("cidadeSelecionada");

            EstadoDAO estadoDAO = new EstadoDAO();
            estados = estadoDAO.listar();
        } catch (RuntimeException erro) {
            Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar uma cidade");
            erro.printStackTrace();
        }
    }
}
