package org.b2w.client.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.b2w.client.Planeta;
import org.b2w.client.repository.PlanetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanetaController {

	@Autowired
	private PlanetaRepository planetaRepository;

	@RequestMapping(value = "/planetaTeste")
	public Planeta planetas() {
		// List<Planeta> listaLivros = planetaRepos itory.findAll();
		Planeta planeta = new Planeta("nome1", "usuario1", "terreno1" );
		planetaRepository.save(planeta);
		return planeta;

	}
	@PostMapping(value="/planeta")
	public Planeta adicionarPlaneta(@RequestBody Planeta planeta) {
		return planetaRepository.save(planeta);
	}
	
	@RequestMapping(value = "/planeta/{id}")
	public Optional<Planeta> encontraPeloId(@PathVariable Long id){
		return planetaRepository.findById(id);
	}
	
	@RequestMapping(value = "/planeta/nome/{nome}")
	public Planeta encontraPeloNome(@PathVariable String nome){
		Planeta planeta = planetaRepository.planetaPeloNome(nome); 
		if(planeta != null) {
			return planeta;
		}
		return new Planeta();
	}
	
	
	@RequestMapping(value = "/planeta/{id}/deleta")
	public String deleta(@PathVariable Long id){
		Optional<Planeta> planeta = planetaRepository.findById(id);
		if (planeta != null) {
			planetaRepository.deleteById(id);
			return "Planeta deletado com sucesso";
		}
		return "Não foi possível deletar porque o planeta não existe"; 
	}
	
	
	@RequestMapping(value = "/listarPlanetas")
	public List<Planeta> listarPlanetas() {
		List<Planeta> planetas = new ArrayList<Planeta>();
		planetas = planetaRepository.findAll();
		return planetas ;
	}
}