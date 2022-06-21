package br.com.compass.mscatalog.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compass.mscatalog.dto.VariationDto;
import br.com.compass.mscatalog.dto.VariationFormDto;
import br.com.compass.mscatalog.entity.Product;
import br.com.compass.mscatalog.entity.Variation;
import br.com.compass.mscatalog.repository.ProductRepository;
import br.com.compass.mscatalog.repository.VariationRepository;
import br.com.compass.mscatalog.service.exception.ObjectNotFoundException;

@Service
public class VariationService {

	@Autowired
	private VariationRepository variationRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private SequenceGeneratorService seqService;

	public List<VariationDto> findAll() {
		return variationRepository.findAll().stream().map(VariationDto::new).collect(Collectors.toList());
	}

	public VariationDto save(@Valid VariationFormDto variationFormDto) {
		Product product = productRepository.findById(variationFormDto.getProduct_id()).orElseThrow(
				() -> new ObjectNotFoundException("Product ID : " + variationFormDto.getProduct_id() + " not found."));
		Variation variation = new Variation();
		variation.setId(seqService.getSequenceNumber(Variation.SEQUENCE_NAME));
		variation.setProduct(product);
		variation.setColor(variationFormDto.getColor());
		variation.setPrice(variationFormDto.getPrice());
		variation.setQuantity(variationFormDto.getQuantity());
		variation.setSize(variationFormDto.getSize());	
		
		product.addVariation(variation);
		productRepository.save(product);
		
		return new VariationDto(variationRepository.save(variation));
	}

	public VariationDto update(Long id, @Valid VariationFormDto variationFormDto) {
		Product product = productRepository.findById(variationFormDto.getProduct_id()).orElseThrow(
				() -> new ObjectNotFoundException("Product ID : " + variationFormDto.getProduct_id() + " not found."));
		Variation variation = variationRepository.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("Variation ID : "+ id + " not found."));
		variation.setProduct(product);
		variation.setColor(variationFormDto.getColor());
		variation.setPrice(variationFormDto.getPrice());
		variation.setQuantity(variationFormDto.getQuantity());
		variation.setSize(variationFormDto.getSize());
		
		product.addVariation(variation);
		productRepository.save(product);
				
		return new VariationDto(variationRepository.save(variation));
	}

	public void deleteById(Long id) {
		variationRepository.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("Variation ID : "+ id + " not found."));
		variationRepository.deleteById(id);
	}
}
