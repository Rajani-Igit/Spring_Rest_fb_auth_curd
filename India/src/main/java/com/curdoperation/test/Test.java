package com.curdoperation.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		byte[] array = Files.readAllBytes(new File("D:\\Photos\\clg1.jpg").toPath());
		String base64String = Base64.getEncoder().encodeToString(array);
		System.out.println(base64String);

	}

}
