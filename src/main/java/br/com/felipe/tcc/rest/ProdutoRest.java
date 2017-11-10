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

import br.com.felipe.tcc.model.Produto;
import br.com.felipe.tcc.service.ProdutoService;

@RestController
@RequestMapping("/rest/produto")
public class ProdutoRest {
	
	@Autowired
	private ProdutoService service; 
	
	@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Produto>> listAll() {
        List<Produto> produtos = (List<Produto>) service.findAll();
        if(produtos.isEmpty()){
            return new ResponseEntity<List<Produto>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
    }
	
	@RequestMapping(value="/findActive", method = RequestMethod.GET)
    public ResponseEntity<List<Produto>> listActive() {
        List<Produto> produtos = (List<Produto>) service.findActive();
        if(produtos.isEmpty()){
            return new ResponseEntity<List<Produto>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
    }
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
    public ResponseEntity<Produto> save(@RequestBody Produto produto) {
		service.save(produto);
        return new ResponseEntity<Produto>(produto, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Produto> delete(@PathVariable("id") int id){
		Produto produto = service.find(id);
		service.delete(id);
		return new ResponseEntity<Produto>(produto, HttpStatus.OK);
	}
	
}
