package br.com.joojlog.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.joojlog.models.Jogo;

public interface JogoService extends JpaRepository<Jogo, Long>{

	@Query("select j from Jogo j where j.nome like %:nome% ")
	public List<Jogo> buscarPorNome(String nome);
	
}
