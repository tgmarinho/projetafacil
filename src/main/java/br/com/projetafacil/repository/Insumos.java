package br.com.projetafacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetafacil.model.Insumo;
import br.com.projetafacil.repository.helper.InsumosQueries;

public interface Insumos  extends JpaRepository<Insumo, Long>, InsumosQueries     {

	public Insumo findByCodigoSINAPI(String codigo);



	

}
