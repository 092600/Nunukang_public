import sys
import pymysql, csv, os

sys.path.append(os.path.dirname(os.path.abspath(os.path.dirname(__file__))))


from config.config import DB_Config as db



db_config = db()

conn = pymysql.connect(host=db_config.host,
                             user=db_config.user,
                             password=db_config.password,
                             database=db_config.database)

cursor = conn.cursor()

cnt = 0

path = "./nunukang_db/nunukang_db_setting_files/fishing_spots/csv"
spot_list = os.listdir(path)

for loc in spot_list:
    with open("nunukang_db/nunukang_db_setting_files/fishing_spots/csv/"+loc, "r", encoding="utf-8") as f:
        line =  csv.reader(f)

        for spot in line:
            cnt += 1
                
            if (spot[2] == "저수지"):
                fishing_spot_type = 2
            elif (spot[2] == "바다"): 
                fishing_spot_type = 1
            elif (spot[2] == "평지"):
                fishing_spot_type = 3
            else:
                continue

            if ((spot[5] != "" ) and (spot[6] != "")) :
                try:
                    insert_sql = "INSERT INTO fishing_spot (fishing_spot_id, type, address, latitude, longitude, number, capacity, price, name ) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s);"
                    val = (cnt, fishing_spot_type, spot[4], spot[5], spot[6], spot[7], spot[10], spot[12], spot[1])
                    
                    cursor.execute(insert_sql, val)
                    conn.commit()
                except Exception as e:
                    print(spot[12])        
            
            else :
                continue