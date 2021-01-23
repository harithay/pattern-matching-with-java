package com.ps2pdf.regexpandpatternmatching;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StyledPrinter {
	public static void print(String title, String content) {
		printHeader(title);
		System.out.println(content);
		printFooter(title);
	}

	public static void printHeader(String title) {
		String formattedTitle = "*    START: " + title + "    *";
		String stars = String.join("",
				Stream.generate(() -> "*").limit(formattedTitle.length()).collect(Collectors.toList()));

		System.err.println(stars);
		System.out.println(formattedTitle);
		System.out.println(stars);
	}

	public static void printFooter(String title) {
		String formattedTitle = "*     END : " + title + "    *";
		String stars = String.join("",
				Stream.generate(() -> "*").limit(formattedTitle.length()).collect(Collectors.toList()));

		System.out.println(stars);
		System.out.println(formattedTitle);
		System.out.println(stars);
		System.out.println();
		System.out.println();
	}
}
