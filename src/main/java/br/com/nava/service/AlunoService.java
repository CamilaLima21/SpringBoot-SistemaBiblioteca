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

import br.com.nava.entity.Aluno;
import br.com.nava.repository.AlunoRepository;

@Service
public class AlunoService {

	
	@Autowired
	private AlunoRepository alunoRepo;
	
	//todos os alunos da base de dados
	public List<Aluno> listaTodosAlunos(){
		return alunoRepo.findAll();		
	}
	
	// criar um método para paginação
			public Page<Aluno> buscaPorPaginacao(int pagina, int linhasPagina, String direction, String orderBy){
				PageRequest pageRequest = PageRequest.of(pagina, linhasPagina, Direction.valueOf(direction), orderBy);
				return new PageImpl<>(alunoRepo.findAll(), pageRequest, linhasPagina);
			}
	
	//busca por id
	public Aluno buscaPorID(Integer id) throws ObjectNotFoundException{
		Optional<Aluno> aluno = alunoRepo.findById(id);
		return aluno.orElseThrow(()-> new ObjectNotFoundException(null, "Aluno não Encontrado"));
	}
	
	//salvar
	public Aluno salvar(Aluno aluno) {
		return alunoRepo.save(aluno);
	}
	
	//excluir
	public void excluir(Integer id) {
		alunoRepo.deleteById(id);
	}
	
	//alterar
	public Aluno alterar(Aluno objAluno) {
		Aluno aluno = buscaPorID(objAluno.getId());
		aluno.setNome(objAluno.getNome());
		aluno.setTurma(objAluno.getTurma());
		aluno.setDisciplinas(objAluno.getDisciplinas());
		return salvar(aluno);
	}
	
	
	
}
