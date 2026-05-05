package com.example.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllergyService {
	//料理名を受け取り，その料理に含まれるアレルギー食材を返すallergenメソッド
    public List<String> allergen(String inputDish) {
    	// 料理と食材を記録するマップ
        Map<String, List<String>> recipeDatabase = new HashMap<>();

        // テキストファイルの読み込み
        String fileName = ResourceUtil.resolvePath("Recipe.txt");
        System.out.println("入力");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                // 各行をカンマで分割
                String[] parts = line.split(",");
                String dish = parts[0]; // 料理名を取得
                List<String> ingredients = Arrays.asList(Arrays.copyOfRange(parts, 1, parts.length)); // 食材を取得
                recipeDatabase.put(dish, ingredients); // 料理と食材を記録
            }
        } catch (IOException e) {
            System.out.println("データベースの読み込みに失敗しました: " + e.getMessage());
            return null;
        }

        // ユーザーからの入力を受け取る
        //Scanner scanner = new Scanner(System.in);
        //System.out.print("料理名: ");
        //String inputDish = scanner.nextLine();
         

        // 入力した料理とデータベースの照合
        Matcher matcher = new Matcher();
        HashMap<String, String> bindings = new HashMap<>();
        boolean matched = false;
        for (Map.Entry<String, List<String>> entry : recipeDatabase.entrySet()) {
            String dish = entry.getKey(); // データベースから料理名を受け取る
            List<String> dishname = Arrays.asList(inputDish.split("")); // 入力した料理を1文字ずつ分割
            int count = dishname.size();
            while(count > 0) {
                for(int i = 0; i + count <= dishname.size(); i++){
                    String dishpart = dishname.get(i);
                    for(int j = 1; j < count ; j++){ //count分の文字数を料理名から取得
                        dishpart += dishname.get(i + j);
                    }
                    if (matcher.matching(dish, dishpart, bindings)) {
                        System.out.println(dishpart + "に含まれるアレルゲン");
                        matched = true;
                        for (String ingredient : entry.getValue()) {
                            System.out.println("・ " + ingredient); // 食材の表示
                        }
                        return entry.getValue();
                    }
                }
                count--;
            }
        }

        if (!matched) {
            System.out.println("入力された料理はデータベースに存在しません。");
        }
		return null;
        
        

        //scanner.close();
    }
    
    
    
    
    
    //料理と，webページ上で選択された料理に含まれるアレルギー食材（とユーザー入力によるアレルギー食材※未実装）を受け取り，代替案を返すalternativeメソッド
    public String alternative(String dish, List<String> allergens) {
        // アレルギーリストとキーワードの例
        //List<String> allergies = new ArrayList<>();
        //allergies.add("卵");
        //allergies.add("小麦");

        //String keyword = "グラタン";
    	System.out.println("guratan " );
        // URLの生成
        String url = Test.generateUrl(allergens, dish);

        // 生成したURLを表示
        System.out.println("生成されたURL: " + url);

        String extractUrl = Test.ExtractUrl(url);

        // ブラウザでURLを開く
        //Test.openUrlInBrowser(extractUrl);
        
        return extractUrl;
    }
    
    public String alternative2(String dish, List<String> allergens) {
        // 実際の実装ではより複雑なロジックが必要です
        return dish + "の代替案: " + String.join(", ", allergens) + "を除いた料理";
    }


    
    
    
    public Map<String, Map<String, List<String>>> symptoms(List<String> allergens) {
    	String rulesFile = ResourceUtil.resolvePath("SymptomsRule.data");
    	

		try {
			SymptomsFinder finder = new SymptomsFinder(rulesFile);

			//String allergen = "";
			//Scanner scanner = new Scanner(System.in);
			//ArrayList<String> allergens = allergens;
			//while (String allergen : allergens) {
				//System.out.println("Input a query. If finished, please input a dot.");
				//allergen = scanner.nextLine();
				//if (allergen.equals("."))
					//break;
				//allergens.add(allergen);
			//}
			//scanner.close();

			Map<String, Map<String, List<String>>> result = finder.getSymptomsAndTreatments(allergens);
			System.out.println(result);
			return result;
		} catch (IOException e) {
			System.err.println("Error reading files: " + e.getMessage());
		}
		return null;
    }

        
     
    
    
    
    
    
}