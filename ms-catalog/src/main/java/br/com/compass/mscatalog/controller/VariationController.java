package br.com.compass.mscatalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping
	public ResponseEntity<List<VariationDto>> findAll(){
		return new ResponseEntity<List<VariationDto>>(variationService.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<VariationDto> save(@RequestBody VariationFormDto variationFormDto){
		return new ResponseEntity<VariationDto>(variationService.save(variationFormDto), HttpStatus.CREATED);
	}

}
