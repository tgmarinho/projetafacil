package br.com.projetafacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetafacil.model.Estado;

public interface Estados extends JpaRepository<Estado, Long> {

//	public List<Etapa> findBynomeContaining(String nome);
//	
//	@Query("SELECT e FROM Etapa e WHERE e.nome LIKE %?1%")
//	public List<Etapa> findByNome(String nome);
//	
//	
//	 @Query(value = "SELECT max(codigo) FROM etapa", nativeQuery = true)
//	 public String findLastCodigo();
//	
}
