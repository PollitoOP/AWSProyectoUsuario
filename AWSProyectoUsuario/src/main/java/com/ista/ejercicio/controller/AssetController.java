package com.ista.ejercicio.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ista.ejercicio.entity.Asset;
import com.ista.ejercicio.services.S3Service;

@RestController
@RequestMapping("api/assets")
public class AssetController {

	@Autowired
	private S3Service s3Service;

	@PostMapping("/upload-object")
	Map<String, String> upload(@RequestParam MultipartFile file) {
		String key = s3Service.putObject(file);

		Map<String, String> result = new HashMap<>();
		result.put("key", key);
		result.put("url", s3Service.getObjectURL(key));

		return result;
	}

	// obtener el objeto
	@GetMapping(value = "/get-object", params = "key")
	ResponseEntity<ByteArrayResource> getObject(@RequestParam String key) {
		Asset asset = s3Service.getObject(key);
		ByteArrayResource resource = new ByteArrayResource(asset.getContent());

		return ResponseEntity.ok().header("Content-Type", asset.getContentType())
				.contentLength(asset.getContent().length).body(resource);

	}

	// elimibar objeto
	@DeleteMapping(value = "/delete-object", params = "key")
	void deleteObject(@RequestParam String key) {
		s3Service.deleteObject(key);
	}
}
	