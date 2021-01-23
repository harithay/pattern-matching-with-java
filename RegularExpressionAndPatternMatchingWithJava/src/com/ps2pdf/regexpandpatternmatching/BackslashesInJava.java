package com.ps2pdf.regexpandpatternmatching;

public class BackslashesInJava {
	public static void main(String[] args) {
		/**
		 * Backslashes in java used to escape chars
		 */
		String paragraph_1 = "this is my first paragraph.";
		String paragraph_2 = "this is my second paragraph.";
		String twoNewLines = "\n\n";
		String dialog = paragraph_1 + twoNewLines + paragraph_2;
		StyledPrinter.print("Example: Backslashes in java used to escape chars", dialog);
		
		
		String windowsDir = "C:\\Users\\Windows";
		StyledPrinter.print("Example: print windows directory path", windowsDir);
	}
	
	
}
