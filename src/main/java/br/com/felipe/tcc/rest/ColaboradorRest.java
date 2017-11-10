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

import br.com.felipe.tcc.model.Colaborador;
import br.com.felipe.tcc.service.ColaboradorService;

@RestController
@RequestMapping("/rest/colaborador")
public class ColaboradorRest {
	
	@Autowired
	private ColaboradorService service; 
	
	@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Colaborador>> listAll() {
        List<Colaborador> colaboradores = (List<Colaborador>) service.findAll();
        if(colaboradores.isEmpty()){
            return new ResponseEntity<List<Colaborador>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Colaborador>>(colaboradores, HttpStatus.OK);
    }
	
	@RequestMapping(value="/findActive", method = RequestMethod.GET)
    public ResponseEntity<List<Colaborador>> listActive() {
        List<Colaborador> colaboradores = (List<Colaborador>) service.findActive();
        if(colaboradores.isEmpty()){
            return new ResponseEntity<List<Colaborador>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Colaborador>>(colaboradores, HttpStatus.OK);
    }
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
    public ResponseEntity<Colaborador> save(@RequestBody Colaborador colaborador) {
		service.save(colaborador);
        return new ResponseEntity<Colaborador>(colaborador, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Colaborador> delete(@PathVariable("id") int id){
		Colaborador colaborador = service.find(id);
		service.delete(id);
		return new ResponseEntity<Colaborador>(colaborador, HttpStatus.OK);
	}
	
}
