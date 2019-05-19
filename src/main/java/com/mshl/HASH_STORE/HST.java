package com.mshl.HASH_STORE;

import com.mshl.CONSTS.Consts;

import java.sql.Timestamp;
import java.util.Map;
import java.util.TreeMap;

public class HST
{
    private static TreeMap<String, IDINF> map = new TreeMap<String, IDINF>();

    /**
     * Выясняет, существует ли данная комбинация (Cookie, id) в Map'e
     * @param uid - JSESSIONID (создается по умолчанию на стороне клиента)
     * @param id - id пользователя
     * @return
     */
    public static boolean exist(String uid, int id)
    {
        IDINF idinf = map.get(uid);
        if (idinf == null) return false;
        return idinf.getId() == id;
    }


    public static void add(String uid, int id)
    {
        IDINF idinf = map.get(uid);
        if (idinf == null)
        {
            map.put(uid, new IDINF(id, 0));
            return;
        }
        idinf.incCount();
    }

    /**
     * Уменьшает счетчик, но не уничтожает полностью из Map
     * @param uid
     * @param id
     */
    public static void remove(String uid, int id)
    {
        IDINF idinf = map.get(uid);
        if (idinf == null) return;
        idinf.decCount();
        GarbageCollect();
    }


    /**
     * Уничтожает полностью из Map
     * @param uid
     * @param uid
     */
    public static void destroy(String uid, int id)
    {
        if (exist(uid, id))
            map.remove(uid);
    }

    private static void GarbageCollect()
    {
        System.out.println("GarbageCollect");
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (now.getTime() - lastCleaningTime.getTime() < Consts.MinTimeBetweenCleaning) return;
        for(Map.Entry<String, IDINF> entry : map.entrySet())
            if (entry.getValue().getCount() == 0 &&
                    (now.getTime() - entry.getValue().getTime().getTime()) > Consts.MinTimeBetweenCleaning)
            {
                System.out.println("Entry: " + (entry.getValue().getTime().getTime() - now.getTime()));
                map.remove(entry.getKey());
            }

    }

    private static Timestamp lastCleaningTime = new Timestamp(System.currentTimeMillis());

}

