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

import br.com.felipe.tcc.model.AcaoChamado;
import br.com.felipe.tcc.service.AcaoChamadoService;

@RestController
@RequestMapping("/rest/acaoChamado")
public class AcaoChamadoRest {
	
	@Autowired
	private AcaoChamadoService service; 
	
	@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AcaoChamado>> listAll() {
        List<AcaoChamado> acoesChamados = (List<AcaoChamado>) service.findAll();
        if(acoesChamados.isEmpty()){
            return new ResponseEntity<List<AcaoChamado>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<AcaoChamado>>(acoesChamados, HttpStatus.OK);
    }
	
	@RequestMapping(value="/getAcoesChamado/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<AcaoChamado>> getAcoesChamado(@PathVariable("id") int id) {
        List<AcaoChamado> acoesChamados = (List<AcaoChamado>) service.getAcoesChamado(id);
        if(acoesChamados.isEmpty()){
            return new ResponseEntity<List<AcaoChamado>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<AcaoChamado>>(acoesChamados, HttpStatus.OK);
    }
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
    public ResponseEntity<AcaoChamado> save(@RequestBody AcaoChamado acaoChamado) {
		service.save(acaoChamado);
        return new ResponseEntity<AcaoChamado>(acaoChamado, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<AcaoChamado> delete(@PathVariable("id") int id){
		AcaoChamado acaoChamado = service.find(id);
		service.delete(id);
		return new ResponseEntity<AcaoChamado>(acaoChamado, HttpStatus.OK);
	}
	
}
