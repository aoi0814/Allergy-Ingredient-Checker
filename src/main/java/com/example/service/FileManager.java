package com.example.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

class FileManager {

	public ArrayList<Rule> loadRules(String theFileName) {
		ArrayList<Rule> rules = new ArrayList<Rule>();
		try (BufferedReader br =
				new BufferedReader(new InputStreamReader(new FileInputStream(theFileName), StandardCharsets.UTF_8))) {
			String line;
			ArrayList<String> antecedents = null;

			while ((line = br.readLine()) != null) {
				String trimmed = line.trim();
				if (trimmed.isEmpty()) {
					continue;
				}

				if (trimmed.startsWith("if ")) {
					String antecedent = trimQuotes(trimmed.substring(3).trim());
					antecedents = new ArrayList<String>();
					antecedents.add(antecedent);
				} else if (trimmed.startsWith("then ")) {
					if (antecedents == null || antecedents.isEmpty()) {
						continue;
					}
					String consequent = trimQuotes(trimmed.substring(5).trim());
					rules.add(new Rule(antecedents, consequent));
					antecedents = null;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return rules;
	}

	private String trimQuotes(String input) {
		if (input.length() >= 2 && input.startsWith("\"") && input.endsWith("\"")) {
			return input.substring(1, input.length() - 1);
		}
		return input;
	}
}
