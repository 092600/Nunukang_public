

from domain.fish.score.fish_score import FishScore
from domain.fish.species import fish_species
import pandas as pd
import random, math
# from fish_score.py import FishScore


for i in range(0, 14):
    size = random.uniform(30, 44)
    fs = FishScore(fish_species.FishSpecies.RED_SEABREAM)

    fishDatas = fs.getFishDatas()
    df_total = pd.DataFrame(data = fishDatas, columns=["fish_size", "fish_score", "category"])

    tmpDF = pd.DataFrame(
        data = {"fish_size" : size, "fish_score" : None, "category" : fs.species.value},
                index=[len(df_total)],
                columns=["fish_size", "fish_score", "category"])

    appendFishsDF = pd.concat([df_total, tmpDF])

    print(round(size, 2), fs.getFishScores(appendFishsDF))

    fs.insert_fish_for_score(43.15)