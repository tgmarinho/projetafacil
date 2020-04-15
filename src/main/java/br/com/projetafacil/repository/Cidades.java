package br.com.projetafacil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetafacil.model.Cidade;

public interface Cidades extends JpaRepository<Cidade, Long> {

	List<Cidade> findByEstadoId(Long idEstado);

//	public List<Etapa> findBynomeContaining(String nome);
//	
//	@Query("SELECT e FROM Etapa e WHERE e.nome LIKE %?1%")
//	public List<Etapa> findByNome(String nome);
//	
//	
//	 @Query(value = "SELECT max(codigo) FROM etapa", nativeQuery = true)
//	 public String findLastCodigo();
	
}
