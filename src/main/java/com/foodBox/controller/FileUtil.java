package com.foodBox.controller;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {
	private FileUtil() {
	    // restrict instantiation
	  }

	  public static final String folderPath =  "C:\\Users\\lenovo\\Desktop\\foodBox\\foodBox-frontend\\foodBox\\src\\assets\\img\\";
	  public static final Path filePath = Paths.get(folderPath);

}
