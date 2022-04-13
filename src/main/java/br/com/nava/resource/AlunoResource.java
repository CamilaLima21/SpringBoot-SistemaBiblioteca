package br.com.nava.resource;

import java.net.URI;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
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
import br.com.nava.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = Messages.SWAGGER_TAG_ALUNO_ENDPOINT)
@RestController
@RequestMapping("/aluno")
public class AlunoResource {
	
	@Autowired
	private AlunoService alunoService;
	
	@Operation(description = Messages.SWAGGER_GET_ALL)
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Aluno>> listarAluno(){
		List<Aluno> alunos = alunoService.listaTodosAlunos();
		return ResponseEntity.ok().body(alunos);
	}
	
	@Operation(description = Messages.SWAGGER_PAGINACAO_API_V1)
	@GetMapping(value="/v1/page")
	public ResponseEntity<Page<Aluno>> listarAlunosPorPaginacaoV1(
			@RequestParam(value="pagina", defaultValue = "0") int pagina,
			@RequestParam(value = "linhasPagina", defaultValue = "24") int linhasPagina,
			@RequestParam(value= "direcao", defaultValue = "ASC") String direcao,
			@RequestParam(value= "orderBy", defaultValue = "nome") String orderBy)
	{
		Page<Aluno> listaAvaliacao = alunoService.buscaPorPaginacao(pagina, linhasPagina, direcao, orderBy);
		return ResponseEntity.ok().body(listaAvaliacao);
		
	}
	
	@Operation(description = Messages.SWAGGER_PAGINACAO_API_V2)
	@GetMapping(value="/v2/page")
	public ResponseEntity<Page<Aluno>> listarAlunosPorPaginacaoV2(
			@RequestParam(value="pagina", defaultValue = "0") int pagina,
			@RequestParam(value= "direcao", defaultValue = "ASC") String direcao,
			@RequestParam(value= "orderBy", defaultValue = "nome") String orderBy)
	{
		Page<Aluno> listaAvaliacao = alunoService.buscaPorPaginacao(pagina, 10, direcao, orderBy);
		return ResponseEntity.ok().body(listaAvaliacao);
		
	}
	
	@Operation(description = Messages.SWAGGER_GET)
	@RequestMapping(value="/{id}", method= RequestMethod.GET)
	public ResponseEntity<Aluno> buscaPorID(@PathVariable Integer id) throws ObjectNotFoundException{
		Aluno aluno = alunoService.buscaPorID(id);
		return ResponseEntity.ok().body(aluno);
	}
	
	@Operation(description = Messages.SWAGGER_INSERT)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Aluno objAluno){
		Aluno aluno = alunoService.salvar(objAluno);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aluno.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@Operation(description = Messages.SWAGGER_DELETE)
	@RequestMapping(value= "/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable Integer id){
		alunoService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(description = Messages.SWAGGER_UPDATE)
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> alterar(@RequestBody Aluno objAluno, @PathVariable Integer id){
		objAluno.setId(id);
		alunoService.alterar(objAluno);
		return ResponseEntity.noContent().build();
	}
	
}