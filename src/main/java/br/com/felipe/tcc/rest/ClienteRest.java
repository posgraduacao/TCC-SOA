package br.com.felipe.tcc.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.tcc.model.Cliente;
import br.com.felipe.tcc.service.ClienteService;

@RestController
@RequestMapping("/rest/cliente")
public class ClienteRest {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Cliente>> listAll() {
        List<Cliente> clientes = (List<Cliente>) service.findAll();
        if(clientes.isEmpty()){
            return new ResponseEntity<List<Cliente>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
    }
	
	@RequestMapping(value="/findActive", method = RequestMethod.GET)
    public ResponseEntity<List<Cliente>> listActive() {
        List<Cliente> clientes = (List<Cliente>) service.findActive();
        if(clientes.isEmpty()){
            return new ResponseEntity<List<Cliente>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
    }
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
		service.save(cliente);
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Cliente> delete(@PathVariable("id") int id){
		Cliente cliente = service.find(id);
		service.delete(id);
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
}
