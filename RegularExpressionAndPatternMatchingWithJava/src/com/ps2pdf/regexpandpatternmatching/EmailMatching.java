package com.ps2pdf.regexpandpatternmatching;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailMatching {
	public static void main(String[] args) throws Exception {
		String validEmail_1 = "john.doe-534aeiio@google.ca";
		String validEmail_2 = "jane.doe-534aeiio@google.com";
		String validEmail_3 = "colt.bennett-534aeiio@google.ca";

		String invalidEmail_1 = "john.doe1-534aeiio@google.ca"; // last name ends with a number
		String invalidEmail_2 = "ja-ne.doe-534aeiio@google.com"; // there is a dash in the middle of first name
		String invalidEmail_3 = "colt.bennett-534aeio@google.ca"; // unique id only has 4 vowels

		List<String> allEmails = List.of(validEmail_1, validEmail_2, validEmail_3, invalidEmail_1, invalidEmail_2,
				invalidEmail_3);

		String patternString = "^[A-Za-z]+\\.[A-Za-z]+-\\d{2,4}[aeiou]{5}@google\\.(ca|com)$";

		matchUsingJavaStringMatcher(allEmails, patternString);

		String patternStringWithGroups = "^([A-Za-z]+)\\.([A-Za-z]+)-(\\d{2,4}[aeiou]{5})@(google\\.(ca|com))$";
		extractGroupsUsingJavaPatternClass(allEmails, patternStringWithGroups);
	}

	public static void matchUsingJavaStringMatcher(List<String> allEmails, String patternString) {
		List<String> matchingEmails = new ArrayList<String>();
		for (String email : allEmails) {
			if (email.matches(patternString)) {
				matchingEmails.add(email);
			}
		}
		StyledPrinter.print("Example: Match emails using String.match", String.join("\n", matchingEmails));
	}

	public static void extractGroupsUsingJavaPatternClass(List<String> allEmails, String patternStringWithGroups) throws Exception {
		StyledPrinter.printHeader("Example: Use parenthesis to group email parts");
		for (String email : allEmails) {
			Pattern pattern = Pattern.compile(patternStringWithGroups);
			Matcher matcher = pattern.matcher(email);
			while (matcher.find()) {
				int numberOfGroups = matcher.groupCount(); // We expect 5
				if (numberOfGroups > 5) {
					throw new Exception("Expected 5 groups but got " + numberOfGroups);
				}
				String completeEmail = matcher.group(0);
				String firstName = matcher.group(1);
				String lastName = matcher.group(2);
				String uuid = matcher.group(3);
				String domain = matcher.group(4);
				
				System.out.println("Email breakdown for " + completeEmail +
					"\n\tFirst Name: " + firstName + 
					"\n\tLast Name: " + lastName + 
					"\n\tUUID: " + uuid + 
					"\n\tDomain: " + domain 
				);
			}
		}

		StyledPrinter.printFooter("Example: Use parenthesis to group email parts");
	}
}
