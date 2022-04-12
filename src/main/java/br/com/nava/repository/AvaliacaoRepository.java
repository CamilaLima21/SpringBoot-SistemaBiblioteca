package br.com.nava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nava.entity.AlunoDisciplina;
import br.com.nava.entity.Avaliacao;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, AlunoDisciplina>{

	Avaliacao findByAlunoDisciplina(AlunoDisciplina alunoDisciplina);
}
