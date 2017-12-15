package it.cgl.justmarket.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import it.cgl.justmarket.models.ProdottoAcquistato;
import it.cgl.justmarket.models.Transazione;

public interface ProdottoAcquistatoRepository extends CrudRepository<ProdottoAcquistato, Integer> {
	
}
