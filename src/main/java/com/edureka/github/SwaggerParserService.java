package com.edureka.github;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.edureka.github.exceptions.InvalidSwaggerFileException;

import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;

public class SwaggerParserService {

	private static final Logger logger = LoggerFactory.getLogger(SwaggerParserService.class);

	public static void main(String[] args) throws FileNotFoundException {
		SwaggerParserService klassObj = new SwaggerParserService();
		SwaggerParser swaggerParser = new SwaggerParser();
		String filePath = "C:\\Users\\Documents\\repo\\edureka-transaction-api\\swagger.json";
		Swagger swagger = klassObj.validateValidSwaggerFile(swaggerParser,
				klassObj.getDataFromFile(new FileInputStream(new File(filePath))));
		logger.info("Swagger details = {} ", swagger);
		klassObj.printEndPoints(swagger);
	}

	private void printEndPoints(Swagger swagger) {
		if (swagger.getPaths() != null && swagger.getPaths().size() > 0) {
			swagger.getPaths().keySet().stream().forEach(s -> System.out.println(s));
		}
	}

	public Swagger validateValidSwaggerFile(SwaggerParser swaggerParser, String content) {
		Swagger swagger = null;
		try {
			swagger = swaggerParser.parse(content);
			if (swagger == null) {
				throw new InvalidSwaggerFileException("Invalid swagger file");
			}
		} catch (Exception e) {
			logger.error("Exception occured while parsing swagger file content = {} ", e);
			throw new InvalidSwaggerFileException("Invalid swagger file");
		}
		return swagger;
	}

	public String getDataFromFile(InputStream input) {
		StringBuilder fileContent = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(input));
			String line;
			while ((line = reader.readLine()) != null) {
				fileContent.append(line);
			}
			logger.info("Swagger file content = {} ", fileContent.toString());
		} catch (Exception e) {
			logger.error("Exception occured while reading file = {} ", e);
			throw new InvalidSwaggerFileException("Invalid swagger file");
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.error("Exception occured while closing file = {} ", e);
				}
			}
		}
		return fileContent.toString();
	}

}
