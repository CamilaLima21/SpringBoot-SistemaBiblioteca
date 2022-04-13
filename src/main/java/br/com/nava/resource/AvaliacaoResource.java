package br.com.nava.resource;

import java.net.URI;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.nava.constantes.Messages;
import br.com.nava.entity.Aluno;
import br.com.nava.entity.AlunoDisciplina;
import br.com.nava.entity.Avaliacao;
import br.com.nava.entity.Disciplina;
import br.com.nava.service.AvaliacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = Messages.SWAGGER_TAG_AVALIACAO_TURMA_ENDPOINT)
@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoResource {

	@Autowired
	private AvaliacaoService service;
	
	@Operation(description = Messages.SWAGGER_GET_ALL)
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Avaliacao>> listarAvaliacao(){
		List<Avaliacao> listaAvaliacao = service.findAll();
		return ResponseEntity.ok().body(listaAvaliacao);
	}
	
	@Operation(description = Messages.SWAGGER_PAGINACAO_API_V1)
	@GetMapping(value="/v1/page")
	public ResponseEntity<Page<Avaliacao>> listarAvaliacoesPorPaginacaoV1(
			@RequestParam(value="pagina", defaultValue = "0") int pagina,
			@RequestParam(value = "linhasPagina", defaultValue = "24") int linhasPagina,
			@RequestParam(value= "direcao", defaultValue = "ASC") String direcao,
			@RequestParam(value= "orderBy", defaultValue = "nome") String orderBy)
	{
		Page<Avaliacao> listaAvaliacao = service.buscaPorPaginacao(pagina, linhasPagina, direcao, orderBy);
		return ResponseEntity.ok().body(listaAvaliacao);
		
	}
	
	@Operation(description = Messages.SWAGGER_PAGINACAO_API_V2)
	@GetMapping(value="/v2/page")
	public ResponseEntity<Page<Avaliacao>> listarAvaliacoesPorPaginacaoV2(
			@RequestParam(value="pagina", defaultValue = "0") int pagina,
			@RequestParam(value= "direcao", defaultValue = "ASC") String direcao,
			@RequestParam(value= "orderBy", defaultValue = "nome") String orderBy)
	{
		Page<Avaliacao> listaAvaliacao = service.buscaPorPaginacao(pagina, 10, direcao, orderBy);
		return ResponseEntity.ok().body(listaAvaliacao);
		
	}
	
	@Operation(description = Messages.SWAGGER_INSERT)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Avaliacao objAvaliacao) {
		objAvaliacao = service.save(objAvaliacao);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objAvaliacao.getAlunoDisciplina()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@Operation(description = Messages.SWAGGER_GET_AVALIACAO_IdALUNO_IdDISCIPLINA)
	@RequestMapping(value = "/{idAluno}/{idDisciplina}", method = RequestMethod.GET)
	public ResponseEntity<Avaliacao> buscarAvaliacaoAlunoPorDisciplina(@PathVariable Integer idAluno, @PathVariable Integer idDisciplina){
		Aluno aluno = new Aluno();
		aluno.setId(idAluno);
		
		Disciplina disciplina = new Disciplina();
		disciplina.setId(idDisciplina);
		
		AlunoDisciplina alunoDisciplina = new AlunoDisciplina();
		alunoDisciplina.setAluno(aluno);
		alunoDisciplina.setDisciplina(disciplina);
		
		Avaliacao avaliacao = service.buscarNotaAlunoDisciplina(alunoDisciplina);
		
		return ResponseEntity.ok().body(avaliacao);
		
		
	}
}
