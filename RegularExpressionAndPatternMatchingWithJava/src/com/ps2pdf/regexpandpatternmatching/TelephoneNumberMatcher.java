package com.ps2pdf.regexpandpatternmatching;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelephoneNumberMatcher {
	private static String paragraph = "This is a sample string with multiple email."
			+ " Contact Jane at 212 454 3332 to get more details about anything."
			+ " Contact John at (212) 454-3334 to get more details about anything."
			+ " Contact Colt at 212-454-333 to get more details about anything.";
	public static void main(String[] args) throws Exception {
		findValidManhattanPhoneNumbers();
		findValidManhattanPhoneNumbersInAParagraph();
		formatValidManhattanPhoneNumbersInAParagraph();
	}
	
	public static void formatValidManhattanPhoneNumbersInAParagraph() {
		String manhattanNumberPattern = "(.*?)(\\(?(212|332|646)\\)?[ -]?(\\d{3})[ -](\\d{4}))(.*?)";
		String result = paragraph.replaceAll(manhattanNumberPattern, "$1($3) $4-$5");
		StyledPrinter.print("Example: Format telephone numbers", result);
	}

	public static void findValidManhattanPhoneNumbersInAParagraph() throws Exception {
		String manhattanNumberPattern = ".*?(\\(?(212|332|646)\\)?[ -]?(\\d{3})[ -](\\d{4})).*?";
		StyledPrinter.printHeader("Example: Use parenthesis to group email parts");
		Pattern pattern = Pattern.compile(manhattanNumberPattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(paragraph);
		while (matcher.find()) {
			String phoneNumber = matcher.group(1);

			System.out.println(phoneNumber);
		}

		StyledPrinter.printFooter("Example: Use parenthesis to group email parts");
	}

	public static void findValidManhattanPhoneNumbers() throws Exception {
		String validNumber_1 = "212 454 3332";
		String validNumber_2 = "212-454-3335";
		String validNumber_3 = "(212) 454-3334";

		String invalidNumber_1 = "21 454 3332";
		String invalidNumber_2 = "212-454-333";

		String patternStringWithGroups = "\\(?(212|332|646)\\)?[ -]?(\\d{3})[ -](\\d{4})";

		List<String> allNumbers = List.of(validNumber_1, validNumber_2, validNumber_3, invalidNumber_1,
				invalidNumber_2);

		StyledPrinter.printHeader("Example: Use parenthesis to group email parts");
		for (String email : allNumbers) {
			Pattern pattern = Pattern.compile(patternStringWithGroups);
			Matcher matcher = pattern.matcher(email);
			while (matcher.find()) {
				int numberOfGroups = matcher.groupCount(); // We expect 4
				if (numberOfGroups > 4) {
					throw new Exception("Expected 4 groups but got " + numberOfGroups);
				}
				String completeTelephoneNumber = matcher.group(0);
				String areaCode = matcher.group(1);
				String first3Numbers = matcher.group(2);
				String last4Numbers = matcher.group(3);

				System.out.println("Phone number breakdown for " + completeTelephoneNumber + "\n\tArea Code: "
						+ areaCode + "\n\tNumber: " + first3Numbers + "-" + last4Numbers);
			}
		}

		StyledPrinter.printFooter("Example: Use parenthesis to group email parts");
	}
}
