package com.example.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SymptomsFinder {

	private ArrayList<Rule> rules;
	private ArrayList<String> wm;

	public SymptomsFinder(String rulesFile) throws IOException {
		FileManager fm = new FileManager();
		this.rules = fm.loadRules(rulesFile);
	}

	public ArrayList<Rule> getRules() {
		return rules;
	}

	public ArrayList<String> getWm() {
		return wm;
	}

	// アレルゲンに対応する症状と対処法を取得するメソッド
    public Map<String, Map<String, List<String>>> getSymptomsAndTreatments(List<String> allergens) {
        Map<String, Map<String, List<String>>> result = new LinkedHashMap<>();

        for (String allergen : allergens) {
            // 一時的な作業メモリを作成
            ArrayList<String> tempWm = new ArrayList<>();
            tempWm.add(allergen);
            RuleBase tempRb = new RuleBase(rules, tempWm);

            // 推論を実行
            Map<String, List<String>> symptomsAndTreatments = tempRb.forwardChain();

            // アレルゲンごとの結果を追加
            result.put(allergen, symptomsAndTreatments);
        }

        return result;
    }
}
