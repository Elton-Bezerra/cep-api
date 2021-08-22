package br.com.boroco.cepapi.adapters.outbound.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.boroco.cepapi.core.entities.EnderecoModel;

@Repository
public interface SpringCEPRepository extends JpaRepository<EnderecoModel, UUID> {

	Optional<EnderecoModel> findByCep(String cep);
}
