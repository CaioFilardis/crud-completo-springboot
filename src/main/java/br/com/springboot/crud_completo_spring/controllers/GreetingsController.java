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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.crud_completo_spring.model.Usuario;
import br.com.springboot.crud_completo_spring.repository.UsuarioRepository;

@RestController
public class GreetingsController {


	@Autowired(required=true)
	private UsuarioRepository usuarioRepository;
	

	@GetMapping(value="listatodos")
	@ResponseBody
	public ResponseEntity<List<Usuario>> listar() {

		List<Usuario> usuarios = usuarioRepository.findAll();
		
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}
	
	@PostMapping(value= "salvar")
	@ResponseBody
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
		
		Usuario user =  usuarioRepository.save(usuario);
		
		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "atualizar")
	@ResponseBody
	public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) { // retorno genério (retorna qualquer tipo)
		
		if (usuario.getId() == null) {
			return new ResponseEntity<String>("Id não foi informado", HttpStatus.OK);
		}
		
		Usuario user = usuarioRepository.saveAndFlush(usuario);
		
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}
	
	
	
	@DeleteMapping(value="deletar")
	@ResponseBody
	public ResponseEntity<String> deletar(@RequestParam Long usuarioId) {
		
		usuarioRepository.deleteById(usuarioId);
		
		return new ResponseEntity<String>("User deletado com sucesso", HttpStatus.OK);
	}
	
	@GetMapping(value="buscaruserid")
	@ResponseBody
	public ResponseEntity<Usuario> buscarUserId(@RequestParam(name = "userId") Long userId) {
		
		// 1. buscar o usuário pelo id
		Usuario usuario = usuarioRepository.findById(userId).get();
		
		// 2. retornar o usuário buscando com sucesso
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	
	
	
}
