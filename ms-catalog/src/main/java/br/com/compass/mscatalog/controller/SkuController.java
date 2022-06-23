package br.com.compass.mscatalog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compass.mscatalog.dto.SkuDto;
import br.com.compass.mscatalog.dto.SkuFormDto;
import br.com.compass.mscatalog.service.SkuService;

@RestController
@RequestMapping("/v1/skus")
public class SkuController {

	@Autowired
	private SkuService skuService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<SkuDto> save(@RequestBody @Valid SkuFormDto skuFormDto){
		return new ResponseEntity<SkuDto>(skuService.save(skuFormDto), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<SkuDto> update(@PathVariable Long id, @RequestBody @Valid SkuFormDto skuFormDto){
		return new ResponseEntity<SkuDto>(skuService.update(id, skuFormDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		skuService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}