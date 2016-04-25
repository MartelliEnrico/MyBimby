package me.martelli.lab.mybimby.recipes;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class BaseInfo {
    private int workTime;
    private int totalTime;
    private @Difficulty int difficulty;
    private int portions;

    @Retention(RetentionPolicy.CLASS)
    @IntDef({DIFFICULTY_EASY, DIFFICULTY_MEDIUM, DIFFICULTY_HARD})
    public @interface Difficulty {}
    public static final int DIFFICULTY_EASY = 0;
    public static final int DIFFICULTY_MEDIUM = 1;
    public static final int DIFFICULTY_HARD = 2;

    public BaseInfo(int workTime, int totalTime, @Difficulty int difficulty, int portions) {
        this.workTime = workTime;
        this.totalTime = totalTime;
        this.difficulty = difficulty;
        this.portions = portions;
    }

    public int getWorkTime() {
        return workTime;
    }

    public int getTotalTime() {
        return totalTime;
    }

    @Difficulty
    public int getDifficulty() {
        return difficulty;
    }

    public int getPortions() {
        return portions;
    }
}
