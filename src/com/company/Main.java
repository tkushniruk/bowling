package com.company;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.calculate("X|7/|9-|X|-8|8/|-6|X|X|X||81");
    }

      private void calculate(String game){
        List<String> frames = Arrays.asList(game.split("[|]+"));
        int bonusScore = 0;
        int bonusBalls = 0;
        int score = 0;

        int cnt = 0;
        for(String frame : frames){
            int tempScore = 0;
            cnt++;
            for(char i : frame.toCharArray()){
                System.out.println(i + " " + bonusBalls + " " + bonusScore + " " + score);
                if(!Character.isDigit(i) && i != '-'){
                    if(i == 'X'){
                        if(bonusBalls > 0){
                            bonusScore += 10;
                        }
                        bonusBalls += 2;
                        score += 10;

                        if(cnt == frames.size()-1)bonusBalls = 2;
                        continue;
                    }else if(i == '/'){
                        if(bonusBalls > 0){
                            bonusScore += 10 - tempScore;
                        }
                        bonusBalls += 1;
                        if(cnt == frames.size()-1)bonusBalls = 1;
                        score += 10 - tempScore;
                        continue;
                       // System.out.println(10 - tempScore);
                    }
                }else if(Character.isDigit(i)){
                    int pushed = Integer.parseInt(String.valueOf(i));
                    tempScore = pushed;
                    if(bonusBalls > 0){
                        bonusScore += pushed;
                    }
                    score += pushed;

                }
                bonusBalls = bonusBalls > 0 ? --bonusBalls : 0;

            }
        }
        cnt = 0;
        while(bonusBalls-- > 0){
            for(char i : frames.get(frames.size() - 1).toCharArray()){
                switch (i){
                    case 'X':
                        score += 10;
                        break;
                    case '/':
                        score += 10;
                        bonusBalls = 0;
                        break;
                    case '-':
                        break;
                    default:
                        score += Integer.parseInt(String.valueOf(i));

                        break;
                }
            }
        }
        System.out.println(score+bonusScore);
    }
}
