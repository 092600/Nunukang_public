
import math
from domain.fish.species.fish_species import FishSpecies

from domain.fish.score.fish_score import FishScore
import pandas as pd

class Fish:
    def __init__(self, ciga_lst, fish_lst, 
        fish_speices_name,
        email, image_path, file_name):
        
        self.email = email
        self.image_path = image_path
        self.file_name = file_name

        self.species = self.get_fish_species_name(fish_speices_name)

        self.gap_x_ciga_lst = float(ciga_lst[2] - ciga_lst[0])
        self.gap_y_ciga_lst = float(ciga_lst[3] - ciga_lst[1])
        self.gap_x_fish_lst = float(fish_lst[2] - fish_lst[0])
        self.gap_y_fish_lst = float(fish_lst[3] - fish_lst[1])
        
        self.use_ciga_size = self.gap_x_ciga_lst if self.gap_x_ciga_lst >= self.gap_y_ciga_lst else self.gap_y_ciga_lst



    def get_fish_size(self):
        
        if (self.gap_x_fish_lst >= self.gap_y_fish_lst):
            size = round(float(self.gap_x_fish_lst / float(self.use_ciga_size)) * 8.8,4)
            # && 물고기가 세로로 눕혀져있음  
        else :
            size = round(float(self.gap_y_fish_lst / float(self.use_ciga_size) ) * 8.8, 4)
        
        return size
    

    
    def get_fish_species_name(self, fish_species_name):
        if (fish_species_name == "Red seabream"):
            return FishSpecies.RED_SEABREAM
        elif (fish_species_name == "Black porgy"):
            return FishSpecies.BLACK_PROGY
        elif (fish_species_name == "Olive flounder"):
            return FishSpecies.OLIVE_FLOUNDER
        elif (fish_species_name == "Korea rockfish"):
            return FishSpecies.KOREA_ROCKFISH
        elif (fish_species_name == "Rock bream"):
            return FishSpecies.ROCK_BREAM
    



    def get_result_data(self):
        fs = FishScore(self.species)

        fishDatas = fs.getFishDatas()

        df_total = pd.DataFrame(data = fishDatas, columns=["fish_size", "fish_score", "category"])

        tmpDF = pd.DataFrame(
            data = {"fish_size" : self.get_fish_size(), "fish_score" : None, "category" : self.species.value},
                    index=[len(df_total)],
                    columns=["fish_size", "fish_score", "category"])
        
        appendFishsDF = pd.concat([df_total, tmpDF])

        fs.insert_fish_for_score(self.get_fish_size())


        return_data = {
                "species" : str(self.species.name),
                "fishSize" : self.get_fish_size(),
                "fishScore" : int(fs.getFishScores(appendFishsDF)),
                "picturePath" : self.image_path,
                "pictureName" : self.file_name,
                "fishingUser" : {
                    "email" : self.email,
                }
            }

        return return_data
