# MahJong_Help_Server

## 介紹


- 本專案主要是在協助**新手**在學習打麻將時，可能對麻將胡牌的規則不熟，進而不了解手中的手牌中是否已經聽牌，或者是聽牌了但不知道聽甚麼，所以本專案提供了三大目標
    - 1.能夠使用簡單的裝置幫助新手
    - 2.幫助新手計算廳的牌有那些
    - 3.可以辨識出手牌有哪些
- 此系統經過兩次改版
- 初版:
    - 為輸入一張麻將手牌圖片後，經過影像處理及機器學習方法判斷，系統能夠告訴使用者此副牌型是否已經聽牌，並且聽的牌有那些。
    - 我們採用Android App的方式來實現，但是手機的運算能力不及電腦，所以我們使用Client-Server的架構，交由電腦(Server)運算出結 果後，再呈現到手機(Client)上
- 第二版:
    - 將辨識桶子的改成用HoughCircles並且改善機器學習辨識的效率。
- 第三版:
    - 實作電腦視窗程式
    - 支援Linux環境
    - 可支援電腦攝影機
    - 機器學習，使用Mobile Net
## 架構設計

### 系統架構
![](https://i.imgur.com/sTZ8vCP.png)
- Client:分為 Android 與 JavaFx
    - Android App: 利用手機的拍照與剪裁功能拍攝麻將，再用Socket網路連結，傳到server，server運算完將結果傳回來。
    - JavaFx與server都是用java物件傳送資料
- 神經網路:分為ResNet50與 Mobile Net
    - ResNet-50
        
    - MobileNet(因為要放在tx2 而採用輕量型的網路)
        

 ### class diagram

![](https://i.imgur.com/UHfHDqp.png)
     
- 麻將演算法
    - 此部分將手牌經過演算法計算，可以得到聽得牌。
- 影像處理
    - 有剪取、灰階、二值化、去雜訊、反轉
- 比對策略
    - 特徵點比對策略
    - 樣板匹配比對策略
    - 差值比對策略
    - 機器學習比對策略
### 比對策略說明

#### 樣板比對策略
- 模板匹配演算法是指通過滑窗(一次移動一個像素)的方式，通過比較模板與子圖的相似度，找到相似度最大的子圖。
    - 方法限制
        - 樣板比對的方式一定會在source圖中找到某一處與sample圖中最相似的部分，如此一來，儘管兩張圖片是不同種類的圖片，系統也會因為某些部分相近的關係而導致誤判。

    一萬跟二萬因為下方相同導致辨識錯誤
    - ![](https://i.imgur.com/uYztdeT.png)


#### 差值比對策略
- 兩個影像相減，計算兩個影像之間重疊的面積。
    - 影像先經過
        - 1.灰階  
        - 2.去雜訊
        - 3.二值化 
        - 4.反轉
    - 方法限制
	    - 這樣比對的方法需要精確的定位，否則兩個張牌的有可能因為角度稍微歪掉或是牌放相反就導致錯誤。

    "南" 相減成功
    ![](https://i.imgur.com/Bexv8yr.png)
    "四萬"因為牌放顛倒導致相減錯誤
    ![](https://i.imgur.com/uNWiScD.png)

#### 特徵點比對

- 基於特徵點的SITF 匹配 (特徵點較多，準確率較高)

- 要解決旋轉,角度偏差的匹配問題，就用到了特徵點演算法。先找到圖像中的一些“特徵點”，這些點不會因為視角的改變、光照的變化、雜訊的干擾而消失，
    - 方法限制
    - 此方法與前兩種方法比較，其結果較為穩定些。但仍有部分誤判，其原因為：麻將的特徵點較少，圖樣變化不大，容易因為背景白色的部分過多而被判定與樣本中的其他圖片為同一種類。
二萬與三萬太相近導致辨識錯誤。
二桶與五筒特徵點相似導致辨識錯誤。
![](https://i.imgur.com/ErYx0fo.png)

#### 霍夫找圓
- 方法限制
    此方法要先經過灰階處理，再使用找圓的方式來計算筒子的個數，但是此方法再針對橢圓的方式，偵測率較低。
因為角度不同導致筒子不夠圓
![](https://i.imgur.com/3hutvoq.png=100x)

#### 機器學習比對策略
- 神經網路:分為ResNet50與 Mobile Net
   - 環境
        - Ubuntu 16.04.3 
        - python 3.5.0
        - tensorflow 1.12  
        - Keras 2.2.4
    - ResNet-50
        - 資料集466張 
        - 測試集168
        - Epoch 150
    - MobileNet(因為要放在tx2 而採用輕量型的網路)
        - 使用pre-train 權重來加快訓練的時間，
        - 訓練與驗證的分配比率(17 : 3)，
        - 訓練集1841張
        - 驗證集325張
        - 測試集分別為165張
        - Epoch 1000
    - 訓練過程
        - ![](https://i.imgur.com/CXUD4Vk.png)


### 執行畫面

#### javaFx桌面
##### 介面
![](https://i.imgur.com/VkWq1DV.png)
##### 攝影機拍攝畫面
![](https://i.imgur.com/KdeaUWj.png)
##### 計算結果
![](https://i.imgur.com/niBS9JQ.png)

#### App介面


