package reflaction;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

//Caching
//The Cache class is a generic class for caching objects.
//It works with generic classes that have:
//a) a public constructor with one K type parameter;
//b) a K getKey() method with any access modifier.
//
//Task:
//1. Choose the correct type for the cache field. The Map<K, V> cache field must store keys for which there are active references.
//Keys with no active references must be automatically removed from the cache along with their values.
//2. Implement the getByKey method:
//2.1. Return an object from the cache that matches key.
//2.2. If an object doesn't exist in the cache, then use reflection to add a new instance to the cache. See item a).
//3. Implement the put method:
//3.1. Using reflection, get a reference to the method described in item b).
//3.2. Use reflection to allow access to it.
//3.3. Using reflection, call the getKey method on obj to get key.
//3.4. Add the <key, obj> pair to the cache.
//3.5. Return true if the method finishes correctly, otherwise return false. Ignore exceptions.

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();   // TODO: Add your code here
    private double tt = 5;

    public V getByKey(K key, Class<V> clazz) throws Exception {
        // TODO: Add your code here
        V value = cache.get(key);
        if(value == null){
            V obj = (V) clazz.getConstructor(key.getClass()).newInstance(key);
            cache.put(key,obj);
        }
        return value;
    }

    public boolean put(V obj) {
        // TODO: Add your code here
        boolean flag = false;
        try {
            Class clazz = obj.getClass();
            Method method = clazz.getDeclaredMethod("getKey", null);
            method.setAccessible(true);
            K key = (K) method.invoke(obj);
            cache.put(key, obj);
            flag = true;
        }catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException err){
            flag = false;
        }
        return flag;
    }

    public int size() {
        return cache.size();
    }
}