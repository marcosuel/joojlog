package br.com.joojlog.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import br.com.joojlog.models.Jogo;
import br.com.joojlog.service.JogoService;

@RestController
@RequestMapping("/api")
public class JogoController {
	
	@Autowired
	JogoService jogoRep;

	@GetMapping("/jogos")
	public ResponseEntity listar() {
		try {
			return ResponseEntity.ok(jogoRep.findAll());
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
			
	@PostMapping("/jogos")
	public ResponseEntity<Jogo> criar(@RequestBody Jogo jogo) throws URISyntaxException{
		try {
			Jogo novoJogo = jogoRep.save(jogo);
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
		            .buildAndExpand(novoJogo.getId())
		            .toUri();
				
			return ResponseEntity.created(uri).body(novoJogo);
		} catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping("/jogos/{id}")
	public ResponseEntity<Jogo> atualizar(@PathVariable Long id, @RequestBody Jogo jogo){
		try {
			if(!jogoRep.existsById(id)) {
				return ResponseEntity.notFound().build();
			}
			
			jogo.setId(id);
			jogo = jogoRep.save(jogo);
			
			return ResponseEntity.ok(jogo);
		}catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
