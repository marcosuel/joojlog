package br.com.joojlog.service;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joojlog.models.Jogo;

public interface JogoService extends JpaRepository<Jogo, Long>{

}
