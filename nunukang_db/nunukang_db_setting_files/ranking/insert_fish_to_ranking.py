import pymysql, random, sys, os
from enum import Enum

sys.path.append(os.path.dirname(os.path.abspath(os.path.dirname(__file__))))

from config import DB_Config as db_config

class FishSpecies(Enum):
    RED_SEABREAM = 1
    BLACK_PROGY = 2
    OLIVE_FLOUNDER = 3
    KOREA_ROCKFISH = 4
    ROCK_BREAM = 5





conn = pymysql.connect(host=db_config.host,
                             user=db_config.user,
                             password=db_config.password,
                             database=db_config.database)

cursor = conn.cursor()
    


lst = [FishSpecies.RED_SEABREAM, FishSpecies.BLACK_PROGY, FishSpecies.OLIVE_FLOUNDER, FishSpecies.KOREA_ROCKFISH, FishSpecies.ROCK_BREAM]


RED_SEABREAM = [
    [35.05, 5],
    [36.90, 45],
    [38.56, 65],
    [39.50, 75],
    [41.25, 85],
    [41.85, 85],
    [42.25, 85],
    [44.40, 90],
    [45.35, 90],
    [47.68, 90],
    [48.50, 90],
    [48.95, 100],
    [49.71, 100],
    [50.03, 100],
    [42.68, 85],
    [33.6, 90],
    [30.21, 90],
    [36.53, 35],
    [40.55, 80],
    [43.25, 90],
    [37.96, 60],
    [34.98, 90],
    [34.64, 90],
    [41.45, 85],
    [38.1, 60],
    [41.87, 85],
    [38.58, 65],
    [41.54, 85],
]



BLACK_PROGY = [
    [34.05, 40],
    [35.69, 50],
    [37.7, 65],
    [38.30, 65],
    [38.95, 70],
    [39.10, 70],
    [39.40, 70],
    [40.51,  75],
    [41.85, 80],
    [42.25, 85],
    [44.15, 90],
    [44.39, 90],
    [46.51, 90],
    [42.85, 85],
    [31.85, 30],
    [43.7, 90],
    [43.49, 85],
    [32.88, 35],
    [40.42, 75],
    [43.08, 85],
    [40.55, 75],
]


KOREA_ROCKFISH = [
    [34.75, 55],
    [37.01, 75],
    [37.28, 75],
    [36.62, 70],
    [39.17, 85],
    [39.39, 85],
    [39.58, 90],
    [40.63, 90],
    [41.79, 90],
    [42.44, 90],
    [42.82, 90],
    [42.93, 90],
    [43.17, 90],
    [44.19, 100],
    [30.82, 20],
    [30.24, 20],
    [32.18, 30],
    [39.79, 90],
    [33.36, 40],
    [30.77, 20],
]


OLIVE_FLOUNDER = [
    [40.21, 90],
    [40.96, 90],
    [32.93, 60],
    [34.73, 75],
    [40.05, 90],
    [31.61, 50],
    [34.98, 75],
    [35.37, 80],
    [35.41, 80],
    [35.53, 80],
    [32.9, 60],
    [33.2, 65],
    [30.23, 35],
    [36.24, 85],
    [35.14, 80],
    [33.68, 65],
    [34.81, 75],
    [32.23, 55],
    [39.25, 90],
]



ROCK_BREAM = [
    [34.24, 45],
    [36.98, 70],
    [33.17, 40],
    [31.72, 30],
    [30.22, 20],
    [38.37, 75],
    [30.96, 25],
    [33.25, 40],
    [40.94, 85],
    [39.61, 80],
    [35.46, 55],
    [30.06, 20],
    [35.98, 60],
    [40.31, 85],
    [38.1, 75],
    [30.09, 20],
    [38.46, 75],
    [34.62, 50],
    [37.2, 70],
    [40.83, 85],
    [37.39, 70],
    [33.84, 45],
    [40.45, 85],
    [31.16, 25],
]

fish_id = 0

for fish in lst:
    
    if (fish.name == "RED_SEABREAM"):
        for f in RED_SEABREAM:
            fish_id+=1

            insert_sql = "INSERT INTO fish (fish_id, fish_size, fish_score, species, fish_catcher_id) VALUES (%s, %s, %s, %s, %s);"
            val = (fish_id , f[0], f[1], fish.value, random.randint(1, 11))

            cursor.execute(insert_sql, val)
            conn.commit()

            print(f)

    elif (fish.name == "BLACK_PROGY"):
        for f in BLACK_PROGY:
            fish_id+=1

            insert_sql = "INSERT INTO fish (fish_id, fish_size, fish_score, species, fish_catcher_id) VALUES (%s, %s, %s, %s, %s);"
            val = (fish_id , f[0], f[1], fish.value, random.randint(1, 11))

            cursor.execute(insert_sql, val)
            conn.commit()

            print(f)

    elif (fish.name == "OLIVE_FLOUNDER"):
        for f in OLIVE_FLOUNDER:
            fish_id+=1

            insert_sql = "INSERT INTO fish (fish_id, fish_size, fish_score, species, fish_catcher_id) VALUES (%s, %s, %s, %s, %s);"
            val = (fish_id , f[0], f[1], fish.value, random.randint(1, 11))

            cursor.execute(insert_sql, val)
            conn.commit()

            print(f)

    elif (fish.name == "KOREA_ROCKFISH"):
        for f in KOREA_ROCKFISH:
            fish_id+=1

            insert_sql = "INSERT INTO fish (fish_id, fish_size, fish_score, species, fish_catcher_id) VALUES (%s, %s, %s, %s, %s);"
            val = (fish_id , f[0], f[1], fish.value, random.randint(1, 11))

            cursor.execute(insert_sql, val)
            conn.commit()

            print(f)

    elif (fish.name == "ROCK_BREAM"):
        for f in ROCK_BREAM:
            fish_id+=1

            insert_sql = "INSERT INTO fish (fish_id, fish_size, fish_score, species, fish_catcher_id) VALUES (%s, %s, %s, %s, %s);"
            val = (fish_id , f[0], f[1], fish.value, random.randint(1, 11))

            cursor.execute(insert_sql, val)
            conn.commit()

            print(f)