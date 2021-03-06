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

import br.com.nava.entity.Disciplina;
import br.com.nava.repository.DisciplinaRepository;

@Service
public class DisciplinaService {

	@Autowired
	private DisciplinaRepository disciplinaRepo;

	// listar todas as disciplinas
	public List<Disciplina> listarTodasDisciplinas() {
		return disciplinaRepo.findAll();
	}

	// criar um método para paginação
	public Page<Disciplina> buscaPorPaginacao(int pagina, int linhasPagina, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(pagina, linhasPagina, Direction.valueOf(direction), orderBy);
		return new PageImpl<>(disciplinaRepo.findAll(), pageRequest, linhasPagina);
	}

	// busca por ID
	public Disciplina buscaPorID(Integer id) throws ObjectNotFoundException {
		Optional<Disciplina> disciplina = disciplinaRepo.findById(id);
		return disciplina.orElseThrow(() -> new ObjectNotFoundException(null, "Disciplina não Encontrada!"));
	}

	// salvar
	public Disciplina salvar(Disciplina disciplina) {
		return disciplinaRepo.save(disciplina);
	}

	// excluir
	public void excluir(Integer id) {
		disciplinaRepo.deleteById(id);
	}

	// alterar
	public Disciplina alterar(Disciplina objDisciplina) {
		Disciplina disc = buscaPorID(objDisciplina.getId());
		disc.setNome(objDisciplina.getNome());
		return salvar(disc);
	}

}
