package com.mshl.HASH_STORE;

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

}
