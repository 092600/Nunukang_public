
import sys
import os
import pymysql

sys.path.append(os.path.dirname(os.path.abspath(os.path.dirname(__file__))))

from config import DB_Config as db_config



conn = pymysql.connect(host=db_config.host,
                             user=db_config.user,
                             password=db_config.password,
                             database=db_config.database)

cursor = conn.cursor()

cnt = 0
red_seabream_cnt = 0
black_progy_cnt = 0
olive_folunder_cnt = 0
korea_rockfish_cnt = 0
rock_bream_cnt = 0



try :
    with open("/Users/sim/github/Nunukang_public/nunukang_db/nunukang_db_setting_files/fish_for_score_calculate/csv/fish_score.csv", "r", encoding="utf-8") as f:
        lines = f.readlines()

        for line in lines:
            tmp = line.split(",")
            
            cnt+=1

            if (tmp[-2]=='0') :

                insert_sql = "INSERT INTO fish_for_score (id, fish_size) VALUES (%s, %s);"
                val = (cnt , tmp[-1])

                cursor.execute(insert_sql, val)
                conn.commit()

                insert_sql = "INSERT INTO red_seabream (id, species) VALUES (%s, %s);"
                val = (cnt ,1)

                cursor.execute(insert_sql, val)
                conn.commit()

            
            elif (tmp[-2] == '1'):

                insert_sql = "INSERT INTO fish_for_score (id, fish_size) VALUES (%s, %s);"
                val = (cnt , tmp[-1])

                cursor.execute(insert_sql, val)
                conn.commit()

                insert_sql = "INSERT INTO black_progy (id, species) VALUES (%s, %s);"
                val = (cnt, 2)
                
                cursor.execute(insert_sql, val)
                conn.commit()
            
            elif (tmp[-2] == '2'):

                insert_sql = "INSERT INTO fish_for_score (id, fish_size) VALUES (%s, %s);"
                val = (cnt , tmp[-1])

                cursor.execute(insert_sql, val)
                conn.commit()

                insert_sql = "INSERT INTO olive_flounder (id, species) VALUES (%s, %s);"
                val = (cnt , 3)
            
                cursor.execute(insert_sql, val)
                conn.commit()

            elif (tmp[-2] == '3'):

                insert_sql = "INSERT INTO fish_for_score (id, fish_size) VALUES (%s, %s);"
                val = (cnt , tmp[-1])

                cursor.execute(insert_sql, val)
                conn.commit()

                insert_sql = "INSERT INTO korea_rockfish (id, species) VALUES (%s, %s);"
                val = (cnt , 4)

                cursor.execute(insert_sql, val)
                conn.commit()
            
            elif (tmp[-2] == "4"):
            
                insert_sql = "INSERT INTO fish_for_score (id, fish_size) VALUES (%s, %s);"
                val = (cnt , tmp[-1])

                cursor.execute(insert_sql, val)
                conn.commit()

                insert_sql = "INSERT INTO rock_bream (id, species) VALUES (%s, %s);"
                val = (cnt , 5)

                cursor.execute(insert_sql, val)
                conn.commit()

except Exception as e:
    print(e)