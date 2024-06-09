package com.ista.ejercicio.services;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.ista.ejercicio.entity.Asset;

@Service
public class S3Service {

	private final static String BUCKET = "awsproyectousuariofranklin";

	@Autowired
	private AmazonS3 amazonS3;

	public String putObject(MultipartFile multipartFile) {
		String extension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
		String key = String.format("%s.%s", UUID.randomUUID(), extension);

		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(multipartFile.getContentType());

		try {
			PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET, key, multipartFile.getInputStream(),
					objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead);

			amazonS3.putObject(putObjectRequest);

			return key;
		} catch (IOException io) {
			throw new RuntimeException(io);
		}
	}

	public Asset getObject(String key) {
		S3Object s3Object = amazonS3.getObject(BUCKET, key);
		ObjectMetadata metaData = s3Object.getObjectMetadata();

		try {
			S3ObjectInputStream inputStream = s3Object.getObjectContent();
			byte[] bytes = IOUtils.toByteArray(inputStream);

			return new Asset(bytes, metaData.getContentType());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void deleteObject(String key) {
		amazonS3.deleteObject(BUCKET, key);
	}

	public String getObjectURL(String key) {
		return String.format("https://%s.s3.amazonaws.com/%s", BUCKET, key);

	}
}