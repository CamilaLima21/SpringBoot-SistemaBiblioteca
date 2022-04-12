package br.com.nava.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
