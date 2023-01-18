package br.com.springboot.crud_nexum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.crud_nexum.model.Pessoa;

/**
*
* @author Everton Riquetti
* @version 1.0
* @since 2022-10-03
*/

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	@Query(value = "select p from Pessoa p where upper(trim(p.nome)) like %?1%") /*Pesquisar por partes*/
	List<Pessoa> buscarPorNome(String name); 
}
