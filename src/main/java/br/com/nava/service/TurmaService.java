package br.com.nava.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.nava.entity.Turma;
import br.com.nava.repository.TurmaRepository;

@Service
public class TurmaService {

	@Autowired
	TurmaRepository repo;
	
	// criar método para listar todas as turmas
	public List<Turma> listarTodasTurmas(){
		return repo.findAll();
	}	
	
	// criar um método para paginação
	public Page<Turma> buscaPorPaginacao(int pagina, int linhasPagina, String direction, String orderBy){
		PageRequest pageRequest = PageRequest.of(pagina, linhasPagina, Direction.valueOf(direction), orderBy);
		return new PageImpl<>(repo.findAll(), pageRequest, linhasPagina);
	}
	
	
	// criar método para listar turma por id
	public Turma buscaPorID(Integer id) throws ObjectNotFoundException {
		Optional<Turma> turma = repo.findById(id);
		return turma.orElseThrow(()-> new ObjectNotFoundException(null, "Turma não Encontrada!"));
	} 
	
	// criar método para inserir a turma
	public Turma salvar(Turma turma) {
		return repo.save(turma);
	}
	
	// criar método para fazer update da turma
	public Turma alterar(Turma objTurma) {
		Turma turma = buscaPorID(objTurma.getId());
		turma.setNome(objTurma.getNome());
		return salvar(turma);
	}
	
	// criar método para excluir turma
	public void excluir(Integer id) {
		repo.deleteById(id);
	}
	
}
