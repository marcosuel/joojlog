package br.com.joojlog.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import br.com.joojlog.models.Jogo;
import br.com.joojlog.service.JogoService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
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
	
	@GetMapping("/jogos/search")
	public ResponseEntity listarPorNome(@RequestParam String nome) {
		try {
			return ResponseEntity.ok(jogoRep.buscarPorNome(nome));
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
	
	@DeleteMapping("/jogos/{id}")
	public ResponseEntity<Jogo> deletar(@PathVariable Long id){
		try {
			if(!jogoRep.existsById(id)) {
				return ResponseEntity.notFound().build();
			}
			
			jogoRep.deleteById(id);
			
			return ResponseEntity.noContent().build();
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/jogos/{id}")
	public ResponseEntity<Optional<Jogo>> burcarPorId(@PathVariable Long id){
		try {
			if(!jogoRep.existsById(id)) {
				return ResponseEntity.notFound().build();
			}
			
			Optional<Jogo> jogo = jogoRep.findById(id);
			
			return ResponseEntity.ok(jogo);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
