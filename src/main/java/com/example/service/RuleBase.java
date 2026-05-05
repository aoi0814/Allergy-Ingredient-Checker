package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * ルールベースを表すクラス．
 *
 * 
 */
class RuleBase {
	ArrayList<String> wm;
	ArrayList<Rule> rules;

	RuleBase(ArrayList<Rule> theRules, ArrayList<String> theWm) {
		wm = theWm;
		rules = theRules;
	}

	public void setWm(ArrayList<String> theWm) {
		wm = theWm;
	}

	public void setRules(ArrayList<Rule> theRules) {
		rules = theRules;
	}

	/**
	 * 前向き推論を行うためのメソッド
	 *
	 */
	public Map<String, List<String>> forwardChain() {
		boolean newAssertionCreated;
		Map<String, List<String>> result = new LinkedHashMap<>();
		// 新しいアサーションが生成されなくなるまで続ける．
		do {
			newAssertionCreated = false;
			for (Rule rule : rules) {
				ArrayList<String> antecedents = rule.getAntecedents();
				String consequent = rule.getConsequent();
				ArrayList<HashMap<String, String>> bindings = matchingAssertions(antecedents);
				if (bindings != null) {
					for (int i = 0; i < bindings.size(); i++) {
						// 後件をインスタンシエーション
						String newAssertion = instantiate(consequent, bindings.get(i));
						for (String antecedent : antecedents) {
							if (antecedent.startsWith("?x cause")) {
								String[] parts = antecedent.split(" cause ");
								if (parts.length == 2) {
									String symptom = parts[1];
									addToMap(result, symptom, newAssertion);
								}
							}
						}
						if (!wm.contains(newAssertion)) {
							wm.add(newAssertion);
							newAssertionCreated = true;
						}
					}
				}
			}
		} while (newAssertionCreated);
		return result;
	}

	/**
	 * マッチするアサーションに対するバインディング情報を返す （再帰的）
	 *
	 * @param 前件を示す ArrayList
	 * @return バインディング情報が入っている ArrayList
	 */
	public ArrayList<HashMap<String, String>> matchingAssertions(ArrayList<String> theAntecedents) {

		ArrayList<HashMap<String, String>> bindings = new ArrayList<>();
		return matchable(theAntecedents, 0, bindings);
	}

	private ArrayList<HashMap<String, String>> matchable(ArrayList<String> theAntecedents, int n,
			ArrayList<HashMap<String, String>> bindings) {

		if (n == theAntecedents.size()) {
			return bindings;
		} else if (n == 0) {
			boolean success = false;
			for (int i = 0; i < wm.size(); i++) {
				HashMap<String, String> binding = new HashMap<String, String>();
				if ((new Matcher()).matching(theAntecedents.get(n), wm.get(i), binding)) {
					bindings.add(binding);
					success = true;
				}
			}
			if (success) {
				return matchable(theAntecedents, n + 1, bindings);
			} else {
				return null;
			}
		} else {
			boolean success = false;
			ArrayList<HashMap<String, String>> newBindings = new ArrayList<HashMap<String, String>>();
			for (int i = 0; i < bindings.size(); i++) {
				for (int j = 0; j < wm.size(); j++) {
					HashMap<String, String> binding = new HashMap<>(bindings.get(i));
					if ((new Matcher()).matching(theAntecedents.get(n), wm.get(j), binding)) {
						newBindings.add(binding);
						success = true;
					}
				}
			}
			if (success) {
				return matchable(theAntecedents, n + 1, newBindings);
			} else {
				return null;
			}
		}
	}

	private String instantiate(String thePattern, HashMap<String, String> theBindings) {

		String result = new String();
		StringTokenizer st = new StringTokenizer(thePattern);

		for (int i = 0; i < st.countTokens();) {
			String token = st.nextToken();
			if (var(token)) {
				result = result + " " + theBindings.get(token);
			} else {
				result = result + " " + token;
			}
		}
		return result.trim();
	}

	private boolean var(String str1) {
		// 先頭が ? なら変数
		return str1.startsWith("?");
	}

	private static void addToMap(Map<String, List<String>> map, String key, String value) {
		// 指定されたキーに対するリストを取得（なければ新規作成）
		List<String> list = map.computeIfAbsent(key, k -> new ArrayList<>());
		// リストに値を追加
		if (!list.contains(value)) {
			list.add(value);
		}
	}

}
