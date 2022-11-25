package me.Prisma.utils;

public class GamingTime {
    private static double T = 0;
    private static double S = 0;
    private static double M = 0;
    private static double H = 0;
    public static String timeText;
    public static void timeUp() {
        T += 1;
        if(T == 20){
            T = 0;
            S += 1;
        }
        if (S == 60){
            S = 0;
            M += 1;
        }
        if(M == 60){
            M=0;
            H+=1;
        }
        timeText = H+" [じかん] "+M+" [ぶん] "+S+" [びょう]";
    }
}