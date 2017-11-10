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

import br.com.felipe.tcc.model.Chamado;
import br.com.felipe.tcc.service.ChamadoService;

@RestController
@RequestMapping("/rest/chamado")
public class ChamadoRest {
	
	@Autowired
	private ChamadoService service; 
	
	@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Chamado>> listAll() {
        List<Chamado> chamados = (List<Chamado>) service.findAll();
        if(chamados.isEmpty()){
            return new ResponseEntity<List<Chamado>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Chamado>>(chamados, HttpStatus.OK);
    }
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
    public ResponseEntity<Chamado> save(@RequestBody Chamado chamado) {
		service.save(chamado);
        return new ResponseEntity<Chamado>(chamado, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Chamado> delete(@PathVariable("id") int id){
		Chamado chamado = service.find(id);
		service.delete(id);
		return new ResponseEntity<Chamado>(chamado, HttpStatus.OK);
	}
	
}
