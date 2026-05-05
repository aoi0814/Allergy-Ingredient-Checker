package com.example.service;

import java.util.ArrayList;

/**
 * ルールを表すクラス．
 *
 * 
 */
class Rule {
    ArrayList<String> antecedents;
    String consequent;

    Rule(ArrayList<String> theAntecedents,String theConsequent){
        this.antecedents = theAntecedents;
        this.consequent = theConsequent;
    }

    /**
     * ルールをString形式で返す
     *
     * @return    ルールを整形したString
     */
    public String toString(){
        return antecedents.toString()+"->"+consequent;
    }

    /**
     * ルールの前件を返す．
     *
     * @return    前件を表す ArrayList
     */
    public ArrayList<String> getAntecedents(){
        return antecedents;
    }

    /**
     * ルールの後件を返す．
     *
     * @return    後件を表す String
     */
    public String getConsequent(){
        return consequent;
    }
    
}
