package br.com.compass.mscatalog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compass.mscatalog.dto.VariationDto;
import br.com.compass.mscatalog.dto.VariationFormDto;
import br.com.compass.mscatalog.service.VariationService;

@RestController
@RequestMapping("/v1/variations")
public class VariationController {
	
	@Autowired
	private VariationService variationService;
	
	@PostMapping
	public ResponseEntity<VariationDto> save(@RequestBody @Valid VariationFormDto variationFormDto){
		return new ResponseEntity<VariationDto>(variationService.save(variationFormDto), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<VariationDto> update(@PathVariable Long id, @RequestBody @Valid VariationFormDto variationFormDto){
		return new ResponseEntity<VariationDto>(variationService.update(id, variationFormDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		variationService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
