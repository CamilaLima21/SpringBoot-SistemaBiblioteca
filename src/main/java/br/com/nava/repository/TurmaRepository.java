package br.com.nava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.nava.entity.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer>{

	
}
