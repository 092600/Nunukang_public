package com.nunukang.nunukang.domain.fish.species;


public enum FishSpecies {
    DUMMY, RED_SEABREAM, BLACK_PROGY, OLIVE_FLOUNDER, KOREA_ROCKFISH, ROCK_BREAM;


    // public int getSpecies()
    public static FishSpecies getSpeciesName(Integer speciesNumber) {
        FishSpecies fishSpecies = null;
        switch (speciesNumber) {
            case (1):
                fishSpecies = FishSpecies.RED_SEABREAM;
                break;
            case (2):
                fishSpecies = FishSpecies.BLACK_PROGY;
                break;
            case (3):
                fishSpecies = FishSpecies.OLIVE_FLOUNDER;
                break;
            case (4):
                fishSpecies = FishSpecies.KOREA_ROCKFISH;
                break;
            case (5):
                fishSpecies = FishSpecies.ROCK_BREAM;     
                break;
        }

        return fishSpecies;

    }
}
