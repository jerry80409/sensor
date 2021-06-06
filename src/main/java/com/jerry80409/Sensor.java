package com.jerry80409;

import java.util.HashMap;
import java.util.Map;
import lombok.experimental.UtilityClass;

/**
 * 請以下列條件，設計資料轉換演算法：
 *
 * 有溫度感應器有上線以及離線兩種狀態，以及溫度-40～140度。溫度值皆為整數。
 * 請問狀態會使用何種datatype儲存?該dataType佔記憶體幾個byte?
 * boolean status; (1 byte)
 *
 * 請問溫度會使用何種datatype儲存?該dataType佔記憶體幾個byte?
 * int dataType; (4 byte)
 * 00000000_00000000_00000000_00000000 (0)
 *
 * 每分鐘資料被儲存在依時間先後排序排列的一個Byte array。
 * 每分鐘只能使用1個byte來紀錄資料。
 *
 * 這些資料會以曲線圖來呈現。
 * 圖內需要顯示的資料為最後一次上線時間至目前時間。
 * 若感應器目前的狀態為離線，圖內便無資料。
 *
 * 請依上述需求設計演算法。將狀態以及溫度以一個byte存下來的方式。
 */
@UtilityClass
class Sensor {

    // cache 驗證用
    private static Map<Byte, Integer> CACHE = new HashMap<>();

    /**
     * 解法:
     * 1. byte 範圍: -128 ~ 127
     * 2. 需求 -40 ~ 140, 故將 -40 - 88 = -128,
     * 3. 故溫度 -128(-40) ~ 52(140)
     * 4. 離線 127 (1111_1111)
     *
     * @param online
     * @param temp
     * @return
     */
    byte enocde(boolean online, int temp) {

        if (temp < -40 || temp > 140) {
            throw new RuntimeException(String.format("temp(%d) 超過 -40°C ~ 140°C)", temp));
        }

        // offline
        if (!online) {
            return 0B0111_1111;
        }

        // 88
        int diff = -128 - (-40);

        for (int i = -40; i <= 140; i++) {
            // temp - 88
            byte b =(byte) ((i - diff) & 0xff);
            System.out.println("i:" + i + " " + Integer.toBinaryString(i - diff));
            CACHE.put(b, i);
        }

        return  (byte) ((temp - diff) & 0xff);
    }

    int decode(byte b) {
        return CACHE.get(b);
    }
}
