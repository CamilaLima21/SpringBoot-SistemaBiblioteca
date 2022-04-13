package br.com.nava.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
	
	// criar um método para paginação
		public Page<Avaliacao> buscaPorPaginacao(int pagina, int linhasPagina, String direction, String orderBy){
			PageRequest pageRequest = PageRequest.of(pagina, linhasPagina, Direction.valueOf(direction), orderBy);
			return new PageImpl<>(repo.findAll(), pageRequest, linhasPagina);
		}
	
	// listar notas do aluno por Disciplina
	public Avaliacao buscarNotaAlunoDisciplina(AlunoDisciplina alunoDisciplina) {
		return repo.findByAlunoDisciplina(alunoDisciplina);
	}
	
}
