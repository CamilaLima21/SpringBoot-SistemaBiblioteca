package br.com.nava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nava.entity.AlunoDisciplina;
import br.com.nava.entity.Avaliacao;
import br.com.nava.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {

	@Autowired
	AvaliacaoRepository repo;
	
	// salvar
	public Avaliacao save(Avaliacao avaliacao) {
		return repo.save(avaliacao);
	}
	
	// listagem
	public List<Avaliacao> findAll(){
		return repo.findAll();
	}
	
	// listar notas do aluno por Disciplina
	public Avaliacao buscarNotaAlunoDisciplina(AlunoDisciplina alunoDisciplina) {
		return repo.findByAlunoDisciplina(alunoDisciplina);
	}
	
}
