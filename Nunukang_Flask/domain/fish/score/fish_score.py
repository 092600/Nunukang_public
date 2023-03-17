import pymysql
import pandas as pd
import numpy as np

from config import config

from domain.fish.species import fish_species

class FishScore:

    def __init__(self, species):
        print(config.user)
        print(config.password)
        print(config.db)
        print(config.port)
        
        self.species = species
        self.db = pymysql.connect(host="localhost", user=str(config.user), password=config.password, db=str(config.db), charset='utf8')
    

    
    def getFishDatas(self):
        db = self.db

        cursor = db.cursor()
        sql = "select fish_size, fish_score, species from fish_for_score t1 inner join " + self.species.name + " t2 where t1.id = t2.id"
        cursor.execute(sql)
        fishs = cursor.fetchall()

        self.data_size = len(fishs)
        return fishs


    def insert_fish_for_score(self, fish_size):
        
        id = self.get_fish_for_score_last_Id()

        cursor = self.db.cursor()

        insert_sql = "INSERT INTO fish_for_score (id, fish_size) VALUES (%s, %s);"
        val = (id , fish_size)

        cursor.execute(insert_sql, val)
        self.db.commit()


        insert_sql = "INSERT INTO "+ self.species.name +" (id, species) VALUES (%s, %s);"
        val = (id , self.species.value)
    
        cursor.execute(insert_sql, val)
        self.db.commit()

        self.db.close()

        
    def calculate_score(self, Percentile_Rank):
        if Percentile_Rank >= 0.99: # 상위 0.1% 이내
            score = 100
        elif 0.90 < Percentile_Rank: # 상위 10% 이내
            score = 90
        elif 0.85 < Percentile_Rank: # 상위 15% 이내
            score = 85
        elif 0.80 <  Percentile_Rank: # 상위 20% 이내
            score = 80
        elif 0.75 < Percentile_Rank: # 상위 25% 이내
            score = 75
        elif 0.70 < Percentile_Rank: # 상위 30% 이내
            score = 70
        elif 0.65 < Percentile_Rank: # 상위 35% 이내
            score = 65
        elif 0.60 < Percentile_Rank: # 상위 40% 이내
            score = 60
        elif 0.55 < Percentile_Rank: # 상위 45% 이내
            score = 55
        elif 0.50 < Percentile_Rank: # 상위 50% 이내
            score = 50
        elif 0.45 < Percentile_Rank: # 상위 55% 이내
            score = 45
        elif 0.40 < Percentile_Rank: # 상위 60% 이내
            score = 40
        elif 0.35 < Percentile_Rank: # 상위 65% 이내
            score = 35
        elif 0.30 < Percentile_Rank: # 상위 70% 이내
            score = 30
        elif 0.25 < Percentile_Rank: # 상위 75% 이내
            score = 25
        elif 0.20 < Percentile_Rank: # 상위 80% 이내
            score = 20
        elif 0.15 < Percentile_Rank: # 상위 85% 이내
            score = 15
        elif 0.10 < Percentile_Rank: # 상위 90% 이내
            score = 10
        elif Percentile_Rank <= 0.10: # 그 이하
            score = 5
        return score    

   
    def getFishScores(self, df_total):

        if self.species.value == 1:
            df_total_광어 = df_total[df_total['fish_size'] > 35]
            df_total_광어['Percentile_Rank'] = df_total_광어['fish_size'].rank(pct = True, method='min').round(2)
            df_total_광어['score'] = df_total_광어['Percentile_Rank'].apply(lambda Percentile_Rank: self.calculate_score(Percentile_Rank))

            return df_total_광어['score'].iloc[-1]
        

        elif self.species.value == 2:
            df_total_우럭 = df_total[df_total['fish_size'] > 23].rank(pct = True, method='min').round(2)
            df_total_우럭['Percentile_Rank'] = df_total_우럭['fish_size'].rank(pct = True, method='min').round(2)
            df_total_우럭['score'] = df_total_우럭['Percentile_Rank'].apply(lambda Percentile_Rank: self.calculate_score(Percentile_Rank))
            
            
            return df_total_우럭['score'].iloc[-1]
        

        elif self.species.value == 3:
            df_total_참돔 = df_total[df_total['fish_size'] > 24]
            df_total_참돔['Percentile_Rank'] = df_total_참돔['fish_size'].rank(pct = True, method='min').round(2)
            df_total_참돔['score'] = df_total_참돔['Percentile_Rank'].apply(lambda Percentile_Rank: self.calculate_score(Percentile_Rank))


            return df_total_참돔['score'].iloc[-1]

        elif self.species.value == 4:
            df_total_감성돔 = df_total[df_total['fish_size'] > 25]
            df_total_감성돔['Percentile_Rank'] = df_total_감성돔['fish_size'].rank(pct = True, method='min').round(2)
            df_total_감성돔['score'] = df_total_감성돔['Percentile_Rank'].apply(lambda Percentile_Rank: self.calculate_score(Percentile_Rank))


            return df_total_감성돔['score'].iloc[-1]


        elif self.species.value == 5:
            df_total_돌돔 = df_total[df_total['fish_size'] > 24]
            df_total_돌돔['Percentile_Rank'] = df_total_돌돔['fish_size'].rank(pct = True, method='min').round(2)
            df_total_돌돔['score'] = df_total_돌돔['Percentile_Rank'].apply(lambda Percentile_Rank: self.calculate_score(Percentile_Rank))


            return df_total_돌돔['score'].iloc[-1]
        



    def get_fish_for_score_last_Id(self):
        cursor = self.db.cursor()

        sql = "select max(t1.id) from fish_for_score as t1"
        cursor.execute(sql)

        fishs = cursor.fetchone()

        return fishs[0] + 1
        
    