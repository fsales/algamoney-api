package com.algaworks.algamoneyapi.algamoney.api.repository.lancamento;

import com.algaworks.algamoneyapi.algamoney.api.model.Lancamento;
import com.algaworks.algamoneyapi.algamoney.api.repository.filter.LancamentoFilter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class LancamentoRepositoryImpl implements  LancamentoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteriaQuery = builder.createQuery(Lancamento.class);

        Root<Lancamento> root = criteriaQuery.from(Lancamento.class);

        Predicate[] predicate = criarRestricoes(lancamentoFilter, builder, root);

        criteriaQuery.where(predicate);

        TypedQuery<Lancamento> query = manager.createQuery(criteriaQuery);

        return query.getResultList();
    }

    private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder criteriaBuilder, Root<Lancamento> root){
        List<Predicate> predicates = new ArrayList<>();

        if(StringUtils.isNotEmpty(lancamentoFilter.getDescricao())){
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("descricao")), "%" + lancamentoFilter.getDescricao().toLowerCase() + "%"));
        }

        if(lancamentoFilter.getDataVencimentoDe() != null){
            predicates.add();
        }

        if(lancamentoFilter.getDataVencimentoAte() != null){
            predicates.add();
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }
}
