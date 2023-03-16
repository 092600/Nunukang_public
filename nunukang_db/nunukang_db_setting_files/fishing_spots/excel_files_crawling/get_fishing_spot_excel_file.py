
from bs4 import BeautifulSoup as bs

from selenium import webdriver
from selenium.webdriver.common.by import By



import requests
import time

url = "https://www.localdata.go.kr/lif/lifeCtacDataView.do?opnEtcSvcId=12_04_10_E"

driver = webdriver.Chrome("nunukang_db/nunukang_db_setting_files/fishing_spots/excel_files_crawling/seleniumDriver/chromedriver")
driver.get(url)

x_path_list = {
    '서울특별시' : '//*[@id="sidoNm"]/option[1]',
    '부산광역시' : '//*[@id="sidoNm"]/option[2]', 
    '대구광역시' : '//*[@id="sidoNm"]/option[3]', 
    '인천광역시' : '//*[@id="sidoNm"]/option[4]', 
    '광주광역시' : '//*[@id="sidoNm"]/option[5]', 
    '대전광역시' : '//*[@id="sidoNm"]/option[6]', 
    '울산광역시' : '//*[@id="sidoNm"]/option[7]', 
    '세종특별자치시' : '//*[@id="sidoNm"]/option[8]', 
    '경기도' : '//*[@id="sidoNm"]/option[9]', 
    '강원도' : '//*[@id="sidoNm"]/option[10]', 
    '충청북도' : '//*[@id="sidoNm"]/option[11]', 
    '충청남도' : '//*[@id="sidoNm"]/option[12]', 
    '전라북도' : '//*[@id="sidoNm"]/option[13]', 
    '전라남도' : '//*[@id="sidoNm"]/option[14]', 
    '경상북도' : '//*[@id="sidoNm"]/option[15]', 
    '경상남도' : '//*[@id="sidoNm"]/option[16]', 
    '제주특별자치도' : '//*[@id="sidoNm"]/option[17]', 
}

# locationSelectBox = driver.find_element(By.XPATH, '//*[@id="sidoNm"]')
# locationSelectBox.click()

time.sleep(2)

for location in x_path_list:
    # select 버튼으로 지역 변경
    driver.find_element(By.XPATH, x_path_list[location]).click()
    time.sleep(0.5)
    # 검색버튼 클릭
    driver.find_element(By.XPATH, '//*[@id="searchBtn"]').click()
    time.sleep(0.5)

    # 엑셀 다운버튼 클릭
    driver.find_element(By.XPATH, '/html/body/aside/div/div/div[3]/div[2]/div[2]/a').click()

        
