package javaapplication3;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
public class RewriteMe {
    public Database database = new Database();
    public List<Question> questions = database.getQuestions();

    //Skriv en funktioner som returnerar hur många frågor det finns i databasen?
    public int getAmountOfQuestionsInDatabase(){
        return questions.size();

    }

    //Hur många frågor finns i databasen för en viss, given kategori (som ges som inparameter)
    public int getAmountOfQuestionsForACertainCategory(Category category){
        Category category1 = category;
        int count = 0;
        int condition = 0;
        int conditionToCheck = getAmountOfQuestionsInDatabase();
        while(condition<conditionToCheck){
            if(questions.get(condition).category==category1){
                count++;
            }
            condition++;
        }
        return count;

    }

    //Skapa en lista innehållandes samtliga frågesträngar i databasen
    public List<String> getListOfAllQuestions(){
        List<String> resultlist = new ArrayList<>();
        int condition = 0;
        int conditionToCheck = getAmountOfQuestionsInDatabase();
        while(condition<conditionToCheck){
            resultlist.add(questions.get(condition).question);
            condition++;
        }
        return resultlist;
    }

    //Skapa en lista innehållandes samtliga frågesträngar där frågan tillhör en viss kategori
    //Kategorin ges som inparameter
    public List<String> getAllQuestionStringsBelongingACategory(Category category){
        List<String> resultlist = new ArrayList<>();
        int condition = 0;
        int conditionToCheck = getAmountOfQuestionsInDatabase();
        while(condition<conditionToCheck){
            if(questions.get(condition).category==category){
             resultlist.add(questions.get(condition).question);   
            }
            condition++;
        }
            
        return resultlist;
    }

    //Skapa en lista av alla svarsalternativ, där varje svarsalternativ får förekomma
    // en och endast en gång i den lista som du ska returnera
    public List<String> getAllAnswerOptionsDistinct(){
        List<String> resultlist = new ArrayList<>();
        int condition = 0;
        int condition2 = 0;
        int conditionToCheck = getAmountOfQuestionsInDatabase();
        while(condition<conditionToCheck){
            while(condition2<questions.get(condition).answers.size()){
                resultlist.add(questions.get(condition).answers.get(condition2));
                condition2++;
            }
            condition++;
        }
        return resultlist;
    }


    //Finns en viss sträng, given som inparameter, som svarsalternativ till någon fråga i vår databas?
    public boolean isThisAnAnswerOption(String answerCandidate){
        boolean result = false;
        int condition = 0;
        int condition2 = 0;
        int conditionToCheck = getAmountOfQuestionsInDatabase();
        while(condition<conditionToCheck){
            while(condition2<questions.get(condition).answers.size()){
                if(questions.get(condition).answers.get(condition2).equalsIgnoreCase(answerCandidate)){
                  result = true;
              }
                condition2++;
            }
            condition++;
        }
        return result;
    }

    //Hur ofta förekommer ett visst svarsalternativ, givet som inparameter, i databasen
    public int getAnswerCandidateFrequncy(String answerCandidate){
         int count = 0;
         int condition = 0;
        int condition2 = 0;
        int conditionToCheck = getAmountOfQuestionsInDatabase();
        while(condition<conditionToCheck){
            while(condition2<questions.get(condition).answers.size()){
                if(questions.get(condition).answers.get(condition2).equalsIgnoreCase(answerCandidate)){
                  count++;
              }
                condition2++;
            }
            condition++;
        }
        return count;
    }

    //Skapa en Map där kategorierna är nycklar och värdena är en lista
    //av de frågesträngar som tillhör varje kategori
    public Map<Category, List<String>> getQuestionGroupedByCategory(){
        Map map=new HashMap();  
        Category[] categoryvalues = Category.values();
        
        int condition = 0;
        
        int conditionToCheck = getAmountOfQuestionsInDatabase();
        while(condition<categoryvalues.length){
            int condition2 = 0;
            List<String> questionlist = new ArrayList<>();
            while(condition2<conditionToCheck){
               if(questions.get(condition2).category==categoryvalues[condition]){
                 questionlist.add(questions.get(condition).question);
            }
                condition2++;
            }
            map.put(categoryvalues[condition], questionlist);
            condition++;
        }
        return map;
    }

    //Skapa en funktion som hittar det svarsalternativ som har flest bokstäver, i en kategori, given som inparameter
    // OBS: Du måste använda Reduce!
    public String getLongestLettercountAnwerInAGivenCategory(Category c)
    {
        String result = null;
        int condition = 0;
        int conditionToCheck = getAmountOfQuestionsInDatabase();
        while(condition<conditionToCheck){
            if(questions.get(condition).category==c){
                int temp = questions.get(0).answers.get(0).length();
                 result = questions.get(condition).answers.get(0);
                 int i = 0;
                 while(i<questions.get(condition).answers.size()){
                      if(temp < questions.get(condition).answers.get(i).length()){
                        temp = questions.get(condition).answers.get(i).length();
                        result = questions.get(condition).answers.get(i);
                    }
                     i++;
                 }
            }
            condition++;
        }
        
        
        return result;
    }


    public static void main(String[] args){
        RewriteMe uppg4 = new RewriteMe();
        System.out.println("Total Questions in Database: "+uppg4.getAmountOfQuestionsInDatabase());
        System.out.println("Amount of Question for History: "+uppg4.getAmountOfQuestionsForACertainCategory(Category.HISTORY));
        System.out.println("longest String count in  Category Food: "+uppg4.getLongestLettercountAnwerInAGivenCategory(Category.FOOD));
        System.out.println("list of All Questions: "+uppg4.getListOfAllQuestions().toString());
        Map map =uppg4.getQuestionGroupedByCategory();
  Set set=map.entrySet();//Converting to Set so that we can traverse  
    Iterator itr=set.iterator();  
    while(itr.hasNext()){  
        //Converting to Map.Entry so that we can get key and value separately  
        Map.Entry entry=(Map.Entry)itr.next();  
        System.out.println(entry.getKey()+" "+entry.getValue());  
    }  
             System.out.println(uppg4.getLongestLettercountAnwerInAGivenCategory(Category.HISTORY));
    }
}
