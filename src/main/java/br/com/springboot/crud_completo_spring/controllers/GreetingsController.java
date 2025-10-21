package br.com.springboot.crud_completo_spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	
    //@RequestMapping(value = "/{name}", method = RequestMethod.GET) // URL padrão '{}' significa que é uma variável
	@RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET) // URL específica(modificada por mim)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "CRUD  Spring Boot REST API: " + name + "!";
    }
	
	// criando segundo método
	@RequestMapping(value = "/olamundo/{umNome}") // mapeamento de requisição, pelo valor da URL
	@ResponseStatus(HttpStatus.OK) // tipo de resposta esperado
	public String metodoRetornaString(@PathVariable String umNome ) { // anotação do spring
	
		Usuario usuario = new Usuario(); // instancia o objeto
		usuario.setName(umNome);
		
		usuarioRepository.save(usuario); // grava no banco
		
		return "Olá Mundo: " + umNome;
	}
	
	// criando o método de listar usuários
	@GetMapping(value="listatodos") // indica o mapeamento pela requisição
	@ResponseBody // retorna os dados para o corpo da resposta
	public ResponseEntity<List<Usuario>> listar() {
		// 1. percorrer todos os dados da tabela Usuario
		List<Usuario> usuarios = usuarioRepository.findAll(); // executa a consulta
		
		// 2. retornar a resposta e retornar seu status. 
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); // retorna a lista em JSON
	}
}
