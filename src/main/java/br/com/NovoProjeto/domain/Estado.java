package br.com.NovoProjeto.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "estado")
public class Estado extends GenericDomain {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(length = 2, nullable = false )
    private String sigla;

    @Column(length = 50, nullable = false )
    private String nome;
    
    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
