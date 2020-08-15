package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 700; //здоровье
    public static int bossDamage = 50; // сила босса
    public static String bossDefenceType = ""; // тип урона
    public static int[] heroesHealth = {250, 260, 270, 300}; //здоровье героев
    public static int[] heroesDamage = {20, 15, 25, 0}; // сила ударов героев
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic"}; // типы атак героев
    public static int roundNumber = 0;
    public static int medicsVybor = 0;

    public static void main(String[] args) {
        System.out.println("Game start");
        printStatistics();
        while (!isGameFinished()) {
            roundNumber++;
            System.out.println("Round: " + roundNumber);
            round();
        }
    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackType.length); //0,1,2
        for (int i = 0; i < heroesAttackType.length; i++) {

            if (heroesAttackType[randomIndex] == heroesAttackType[3]){
                continue;

            }else {
                bossDefenceType = heroesAttackType[randomIndex];
                System.out.println("Boss choose " + bossDefenceType);
                break;
            }

        }
    }

    public static void round() {
        changeBossDefence();
        heroesHit();

        if (bossHealth > 0) {
            bossHits();
        }
        if (heroesHealth[3] > 0){
            medicsMetod();
        }

        printStatistics();
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] < bossDamage) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void medicsMetod(){
        for (int i = 0; i < heroesHealth.length; i++) {

            int healthFor = 30;

            Random r = new Random();
            int randomR = r.nextInt(heroesHealth.length);
            medicsVybor = heroesHealth[randomR];

            if (heroesHealth[i] < 100 && heroesHealth[i] > 0 &&  heroesHealth[randomR]!= heroesHealth[3]){
                heroesHealth[randomR] = heroesHealth[randomR] + healthFor;

                System.out.println("Медик вылечил " + heroesAttackType[randomR]);
            } break;
        }

    }


    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0) {
                if (bossHealth > 0) {
                    if (bossDefenceType == heroesAttackType[i]) {
                        Random r = new Random();
                        int coef = r.nextInt(8) + 2; //2,3,4,5,6,7,8,9
                        if (bossHealth - heroesDamage[i] * coef < 0) {
                            bossHealth = 0;
                        } else {
                            bossHealth = bossHealth - heroesDamage[i] * coef;
                        }
                        System.out.println("Critical attack: "
                                + (heroesDamage[i] * coef));
                    } else {
                        if (bossHealth - heroesDamage[i] < 0) {
                            bossHealth = 0;
                        } else {
                            bossHealth = bossHealth - heroesDamage[i];
                        }
                    }
                } else {
                    break;
                }
            }
        }

        }


    public static void printStatistics() {
        System.out.println("__________________");
        System.out.println("Boss health: " + bossHealth);
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i]
                    + " Health: " + heroesHealth[i]);
        }
        System.out.println("__________________");
    }
}
