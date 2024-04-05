package br.uece.contasapagarapi.domains.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uece.contasapagarapi.domains.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{

}
