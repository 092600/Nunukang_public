
import sys
import os

sys.path.append("/Users/sim/github/Nunukang_public/fishing_location_crawling/db_config.py")

import pymysql
import db_config
# from db_config import host, user, password, database
# from db_config import *;



conn = pymysql.connect(host=db_config.host,
                             user=db_config.user,
                             password=db_config.password,
                             database=db_config.database)

cursor = conn.cursor()

cnt1 = 0
cnt2 = 0
cnt3 = 0
cnt4 = 0
cnt5 = 0




try :
    with open("./fish_score.csv", "r", encoding="utf-8") as f:
        lines = f.readlines()

        for line in lines:
            tmp = line.split(",")
            
            print(tmp[-2])

            if (tmp[-2]=='0') :
                cnt1 += 1

                insert_sql = "INSERT INTO red_seabream (id, fish_size, species) VALUES (%s, %s, %s);"
                val = (cnt1 , tmp[-1], 1)

                cursor.execute(insert_sql, val)
                conn.commit()

            
            elif (tmp[-2] == '1'):
                cnt2 += 1

                insert_sql = "INSERT INTO black_progy (id, fish_size, species) VALUES (%s, %s, %s);"
                val = (cnt2, tmp[-1], 2)
                
                cursor.execute(insert_sql, val)
                conn.commit()
            
            elif (tmp[-2] == '2'):
                cnt3 += 1

                insert_sql = "INSERT INTO olive_flounder (id, fish_size, species) VALUES (%s, %s, %s);"
                val = (cnt3 , tmp[-1], 3, )
            
                cursor.execute(insert_sql, val)
                conn.commit()

            elif (tmp[-2] == '3'):
                cnt4 += 1

                insert_sql = "INSERT INTO korea_rockfish (id, fish_size, species) VALUES (%s, %s, %s);"
                val = (cnt4 , tmp[-1], 4, )

                cursor.execute(insert_sql, val)
                conn.commit()
            
            elif (tmp[-2] == "4"):
                cnt5 += 1
            
                insert_sql = "INSERT INTO rock_bream (id, fish_size, species) VALUES (%s, %s, %s);"
                val = (cnt5 , tmp[-1], 5, )

                cursor.execute(insert_sql, val)
                conn.commit()

except Exception as e:
    print(e)