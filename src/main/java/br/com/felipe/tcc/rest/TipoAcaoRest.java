package br.com.felipe.tcc.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.felipe.tcc.service.TipoAcaoService;

@RestController
@RequestMapping("/rest/tipoAcao")
public class TipoAcaoRest {
	
	@Autowired
	private TipoAcaoService service;
	
	@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<String>> listAll() {
        List<String> tipos = (List<String>) service.findAll();
        if(tipos.isEmpty()){
            return new ResponseEntity<List<String>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<String>>(tipos, HttpStatus.OK);
    }

}
