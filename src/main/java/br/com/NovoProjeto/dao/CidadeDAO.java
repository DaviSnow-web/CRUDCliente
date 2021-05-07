package br.com.NovoProjeto.dao;

import java.util.List;
import br.com.NovoProjeto.domain.Cidade;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import br.com.NovoProjeto.util.HibernateUtil;

public class CidadeDAO extends GenericDAO<Cidade> {

    public List<Cidade> buscarPorEstado(Long estadoCodigo) {
        Session sessao = HibernateUtil.getSession();
        try {
            Criteria consulta = sessao.createCriteria(Cidade.class);
            consulta.add(Restrictions.eq("estado.codigo", estadoCodigo));
            consulta.addOrder(Order.asc("nome"));
            List<Cidade> resultado = consulta.list();
            return resultado;
        } catch (RuntimeException erro) {
            throw erro;
        } finally {
            sessao.close();
        }
    }
}
