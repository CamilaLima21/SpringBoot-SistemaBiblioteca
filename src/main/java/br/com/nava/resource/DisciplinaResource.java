package br.com.nava.resource;

import java.net.URI;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.nava.constantes.Messages;
import br.com.nava.entity.Disciplina;
import br.com.nava.service.DisciplinaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = Messages.SWAGGER_TAG_DISCIPLINA_ENDPOINT)
@RestController
@ RequestMapping("/disciplina")
public class DisciplinaResource {

	@Autowired
	private DisciplinaService discService;
	
	
	// buscar todas disciplinas
	@Operation(description = Messages.SWAGGER_GET_ALL)
	@GetMapping
	public ResponseEntity<List<Disciplina>> listarDisciplinas() {
		List<Disciplina> disciplinas = discService.listarTodasDisciplinas();
		return ResponseEntity.ok().body(disciplinas);
	}
	
	@Operation(description = Messages.SWAGGER_PAGINACAO_API_V1)
	@GetMapping(value="/v1/page")
	public ResponseEntity<Page<Disciplina>> listarDisciplinasPorPaginacaoV1(
			@RequestParam(value="pagina", defaultValue = "0") int pagina,
			@RequestParam(value = "linhasPagina", defaultValue = "24") int linhasPagina,
			@RequestParam(value= "direcao", defaultValue = "ASC") String direcao,
			@RequestParam(value= "orderBy", defaultValue = "nome") String orderBy)
	{
		Page<Disciplina> disciplinas= discService.buscaPorPaginacao(pagina, linhasPagina, direcao, orderBy);
		return ResponseEntity.ok().body(disciplinas);		
	}
	
	@Operation(description = Messages.SWAGGER_PAGINACAO_API_V2)
	@GetMapping(value="/v2/page")
	public ResponseEntity<Page<Disciplina>> listarTurmasPorPaginacaoV2(
			@RequestParam(value="pagina", defaultValue = "0") int pagina,
			@RequestParam(value= "direcao", defaultValue = "ASC") String direcao,
			@RequestParam(value= "orderBy", defaultValue = "nome") String orderBy)
	{
		Page<Disciplina> disciplinas = discService.buscaPorPaginacao(pagina, 10, direcao, orderBy);
		return ResponseEntity.ok().body(disciplinas);
		
	}
	
	// busca por id
	@Operation(description = Messages.SWAGGER_GET)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Disciplina> buscarPorID(@PathVariable Integer id) throws ObjectNotFoundException {
		Disciplina disc = discService.buscaPorID(id);
		return ResponseEntity.ok().body(disc);
	}
	
	// inserir
	@Operation(description = Messages.SWAGGER_INSERT)
	@PostMapping
	public ResponseEntity<Void> inserir(@RequestBody Disciplina disciplina) {
		Disciplina disc = discService.salvar(disciplina);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(disc.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	// excluir
	@Operation(description = Messages.SWAGGER_DELETE)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable Integer id){
		discService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	// alterar
	@Operation(description = Messages.SWAGGER_UPDATE)
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<Void> alterar(@RequestBody Disciplina objDisciplina, @PathVariable Integer id){
		objDisciplina.setId(id);
		discService.alterar(objDisciplina);
		return ResponseEntity.noContent().build();
	}
	
	
}
