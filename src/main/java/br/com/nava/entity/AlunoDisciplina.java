package br.com.nava.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class AlunoDisciplina implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4348149382215556719L;
	@ManyToOne
	private Aluno aluno;
	@ManyToOne
	private Disciplina disciplina;
	
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	
	
	
}
