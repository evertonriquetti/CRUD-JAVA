package br.com.springboot.crud_nexum.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import br.com.springboot.crud_nexum.model.Pessoa;
import br.com.springboot.crud_nexum.repository.PessoaRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
    /**
     *
     * @param name the name to greet
     * @return greeting text
     * 
     * @author Everton Riquetti
     * @version 1.0
     * @since 2022-10-03
     */
    //End-Point Listar todos
    @GetMapping(value = "listatodos")
    @ResponseBody /*Retorna os dados para o corpo da resposta*/
    public ResponseEntity<List<Pessoa>> listaPessoa(){
    	List<Pessoa> pessoas = pessoaRepository.findAll(); /*consulta no banco de dados*/
    	
    	return new ResponseEntity<List<Pessoa>>(pessoas, HttpStatus.OK); /*Retorna a lista em JSON*/
    }
    
    //End-Point salvar no banco
    @PostMapping(value = "salvar") /*Mapeia a url*/
    @ResponseBody /*Descrição da reposta*/
    public ResponseEntity<Pessoa> salvar(@RequestBody Pessoa pessoa){ /*Recebe os dados para salvar*/
    	Pessoa user = pessoaRepository.save(pessoa);
    	
    	return new ResponseEntity(user, HttpStatus.CREATED);
    }
    
    //End-Point deletar no banco
    @DeleteMapping(value = "delete") /*Mapeia a url*/
    @ResponseBody /*Descrição da reposta*/
    public ResponseEntity<String> delete(@RequestParam Long idpessoa){ /*Recebe os dados para deletar*/
    	
    	pessoaRepository.deleteById(idpessoa);
    	
    	return new ResponseEntity<String>("Pessoa foi deletado com sucesso", HttpStatus.OK);
    }
    
    //End-Point buscar ID no banco
    @GetMapping(value = "buscarid") /*Mapeia a url*/
    @ResponseBody /*Descrição da reposta*/
    public ResponseEntity<Pessoa> buscarid(@RequestParam(name = "idpessoa") Long idpessoa){ /*Recebe os dados para consultar*/
    	
    	Pessoa pessoa = pessoaRepository.findById(idpessoa).get();
    	
    	return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
    }
    
    //End-Point atualizar no banco
    @PutMapping(value = "atualizar") /*Mapeia a url*/
    @ResponseBody /*Descrição da reposta*/
    public ResponseEntity<?> atualizar(@RequestBody Pessoa pessoa){ /*REQUESTBODY -> Recebe os dados para atualizar*/
    	
    	//verificar se ID foi informado para atualizar
    	if (pessoa.getId() == null) {
    		return new ResponseEntity<String>("Id não foi informado para atualização", HttpStatus.BAD_REQUEST);
    	}
    	
    	Pessoa user = pessoaRepository.saveAndFlush(pessoa);
    	
    	return new ResponseEntity<Pessoa>(user, HttpStatus.OK);
    }
    
    //End-Point buscar por partes no banco
    @GetMapping(value = "buscarPorNome") /*Mapeia a url*/
    @ResponseBody /*Descrição da reposta*/
    public ResponseEntity<List<Pessoa>> buscarPorNome(@RequestParam(name = "name") String name){ /*Recebe os dados para consultar*/
    	
    	List<Pessoa> pessoa = pessoaRepository.buscarPorNome(name.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Pessoa>>(pessoa, HttpStatus.OK);
    }
}
