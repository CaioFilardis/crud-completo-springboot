package br.com.springboot.crud_completo_spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.crud_completo_spring.model.Usuario;
import br.com.springboot.crud_completo_spring.repository.UsuarioRepository;

@RestController // permite a realização de requisições REST
public class GreetingsController {

	// declarar o usuário repository
	@Autowired(required=true) // CD ou CDI -> indica a injeção de dependência
	private UsuarioRepository usuarioRepository;
	
	// criando o método de listar usuários
	@GetMapping(value="listatodos") // indica o mapeamento pela requisição
	@ResponseBody // retorna os dados para o corpo da resposta
	public ResponseEntity<List<Usuario>> listar() {
		// 1. percorrer todos os dados da tabela Usuario
		List<Usuario> usuarios = usuarioRepository.findAll(); // executa a consulta
		
		// 2. retornar a resposta e retornar seu status. 
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); // retorna a lista em JSON
	}
	
	// método de salvar
	@PostMapping(value= "salvar") // indica o mapeamento da url
	@ResponseBody // indica a descrição da resposta
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) { // recebe os dados para salvar
		
		// 1. pega o novo usuário e salva
		Usuario user =  usuarioRepository.save(usuario);
		
		// 2. retorna novo objeto salvo
		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
	}
	
	// método para deletar
	@DeleteMapping(value="deletar")
	@ResponseBody
	public ResponseEntity<String> deletar(@RequestParam Long usuarioId) { // indica a passagem de parâmetro
		
		usuarioRepository.deleteById(usuarioId);
		
		return new ResponseEntity<String>("User deletado com sucesso", HttpStatus.OK);
	}
	
	
	
}
