package br.com.nava.inicializacao;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.nava.entity.Aluno;
import br.com.nava.entity.AlunoDisciplina;
import br.com.nava.entity.Avaliacao;
import br.com.nava.entity.Disciplina;
import br.com.nava.entity.Turma;
import br.com.nava.repository.AlunoRepository;
//import br.com.nava.repository.AvaliacaoRepository;
import br.com.nava.service.AvaliacaoService;
import br.com.nava.service.DisciplinaService;
import br.com.nava.service.TurmaService;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	AlunoRepository alunoRepo;
	
	@Autowired
	TurmaService turmaService;
	
	@Autowired
	DisciplinaService disciplinaService;
	
	@Autowired
	AvaliacaoService avaliacaoService;
	
	
	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		Aluno aluno1 = new Aluno();
		aluno1.setNome("Camila");
		
		Aluno aluno2 = new Aluno();
		aluno2.setNome("Matheus");
		
		Aluno aluno3 = new Aluno();
		aluno3.setNome("João");
		
		Aluno aluno4 = new Aluno();
		aluno4.setNome("Maria");
		
		//alunoRepo.saveAll(Arrays.asList(aluno1, aluno2, aluno3));
				
		Turma ads = new Turma();
		ads.setNome("ADS");
		
		Turma rede = new Turma();
		rede.setNome("REDE");
		
		turmaService.salvar(ads);
		turmaService.salvar(rede);
						
		Turma turma1 = turmaService.buscaPorID(1);
		
		System.out.println(turma1.getNome());
		System.out.println("---------------------------------------------------------------");
		//turmaService.excluir(2);
				
		Turma turmaAlterar = new Turma();
		turmaAlterar.setId(2);
		turmaAlterar.setNome("REDES");
		
		turmaService.alterar(turmaAlterar);
		
		List<Turma> listaTurmas = turmaService.listarTodasTurmas();
		
		for (Turma turma : listaTurmas) {
			System.out.println("Nome da Turma: " + turma.getNome());
		}
		System.out.println("---------------------------------------------------------------");
		Disciplina java = new Disciplina();
		java.setNome("Java");
		
		disciplinaService.salvar(java);
		
		Disciplina java2 = new Disciplina();
		java2.setNome("Java II");
		
		disciplinaService.salvar(java2);
		
		Disciplina arquitetura = new Disciplina();
		arquitetura.setNome("Arquitetura");
		
		disciplinaService.salvar(arquitetura);
		
		
		
		
		Turma iot = new Turma();
		turmaAlterar.setId(3);
		iot.setNome("IOT");
		turmaService.salvar(iot);
		
		Turma python = new Turma();
		turmaAlterar.setId(4);
		iot.setNome("Python");
		turmaService.salvar(python);
		
		aluno1.setTurma(ads);
		aluno2.setTurma(python);
		aluno3.setTurma(iot);
		aluno4.setTurma(rede);
		
		aluno1.setDisciplinas(Arrays.asList(java, arquitetura, java2));
		aluno2.setDisciplinas(Arrays.asList(arquitetura, java2));
		aluno3.setDisciplinas(Arrays.asList(java, java2));
		aluno4.setDisciplinas(Arrays.asList(java, arquitetura, java2));
		
		alunoRepo.save(aluno1);
		alunoRepo.save(aluno2);
		alunoRepo.save(aluno3);
		alunoRepo.save(aluno4);
		
		Avaliacao avaliacaoAluno1 = new Avaliacao();
		
		AlunoDisciplina alunoDisciplina = new AlunoDisciplina();
		alunoDisciplina.setAluno(aluno1);
		alunoDisciplina.setDisciplina(arquitetura);

		avaliacaoAluno1.setAlunoDisciplina(alunoDisciplina);
		avaliacaoAluno1.setConceito("A");
		avaliacaoService.save(avaliacaoAluno1);
		
		
		Avaliacao avaliacaoAluno2 = new Avaliacao();
		
		AlunoDisciplina alunoDisc2 = new AlunoDisciplina();
		alunoDisc2.setAluno(aluno2);
		alunoDisc2.setDisciplina(java);
				
		avaliacaoAluno2.setAlunoDisciplina(alunoDisc2);
		avaliacaoAluno2.setConceito("B");		
		avaliacaoService.save(avaliacaoAluno2);
		
		Avaliacao aval = avaliacaoService.buscarNotaAlunoDisciplina(alunoDisciplina);
		
		System.out.println("Aluno: " + aval.getAlunoDisciplina().getAluno().getNome());
		System.out.println("Disciplina: "+ aval.getAlunoDisciplina().getDisciplina().getNome());
		System.out.println("Avaliação: " + aval.getConceito());
		System.out.println("---------------------------------------------------------------");
		
		Avaliacao aval2 = avaliacaoService.buscarNotaAlunoDisciplina(alunoDisc2);
		
		System.out.println("Aluno: " + aval2.getAlunoDisciplina().getAluno().getNome());
		System.out.println("Disciplina: "+ aval2.getAlunoDisciplina().getDisciplina().getNome());
		System.out.println("Avaliação: " + aval2.getConceito());
		System.out.println("---------------------------------------------------------------");
	}		
	
}

