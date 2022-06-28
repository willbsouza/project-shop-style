package br.com.compass.mscatalog.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.compass.mscatalog.dto.SkuDto;
import br.com.compass.mscatalog.dto.SkuFormDto;
import br.com.compass.mscatalog.entity.Media;
import br.com.compass.mscatalog.entity.Product;
import br.com.compass.mscatalog.entity.Sku;
import br.com.compass.mscatalog.repository.MediaRepository;
import br.com.compass.mscatalog.repository.ProductRepository;
import br.com.compass.mscatalog.repository.SkuRepository;
import br.com.compass.mscatalog.service.exception.ObjectNotFoundException;

@Service
public class SkuService {
	
	@Autowired
	private SkuRepository skuRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private MediaRepository mediaRepository;

	public List<SkuDto> findAll() {
		return skuRepository.findAll().stream().map(SkuDto::new).collect(Collectors.toList());
	}

	public SkuDto save(@Valid SkuFormDto skuFormDto) {
		Product product = productRepository.findById(skuFormDto.getProductId()).orElseThrow(
				() -> new ObjectNotFoundException("Product ID : " + skuFormDto.getProductId() + " not found."));
		Sku sku = new Sku();
		sku.setProduct(product);
		sku.setColor(skuFormDto.getColor());
		sku.setPrice(skuFormDto.getPrice());
		sku.setQuantity(skuFormDto.getQuantity());
		sku.setSize(skuFormDto.getSize());	
		sku.setHeight(skuFormDto.getHeight());
		sku.setWidth(skuFormDto.getWidth());
		
		for(String imagemUrl : skuFormDto.getImages()) {
			Media media = new Media(imagemUrl, sku);
			sku.addImages(media);
			mediaRepository.save(media);
		}
		
		return new SkuDto(skuRepository.save(sku));
	}

	public SkuDto update(Long id, @Valid SkuFormDto skuFormDto) {
		Product product = productRepository.findById(skuFormDto.getProductId()).orElseThrow(
				() -> new ObjectNotFoundException("Product ID : " + skuFormDto.getProductId() + " not found."));
		Sku sku = skuRepository.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("Sku ID : "+ id + " not found."));
		sku.setProduct(product);
		sku.setColor(skuFormDto.getColor());
		sku.setPrice(skuFormDto.getPrice());
		sku.setQuantity(skuFormDto.getQuantity());
		sku.setSize(skuFormDto.getSize());	
		sku.setHeight(skuFormDto.getHeight());
		sku.setWidth(skuFormDto.getWidth());
		
		for(String imagemUrl : skuFormDto.getImages()) {
			mediaRepository.save(new Media(imagemUrl, sku));
		}
				
		return new SkuDto(skuRepository.save(sku));
	}

	public void deleteById(Long id) {
		skuRepository.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("Sku ID : "+ id + " not found."));
		skuRepository.deleteById(id);
	}

	public SkuDto findById(Long id) {
		return new SkuDto(skuRepository.findById(id).orElseThrow(
				() -> new ObjectNotFoundException("Sku ID : "+ id + " not found.")));
	}
}
