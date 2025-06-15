package org.example.springlearndemo.util;

import java.util.HashMap;
import java.util.Map;

public class TestUtil {

    public static void main(String[] args) {

        System.out.println(repeatedSubstringPattern("ababab"));
    }

    //力扣1432
    public int maxDiff(int num) {
        //最大化 a：
        //
        //从左到右找到第一个不是 9 的数字，把它和所有相同的数字换成 9（比如 123456 → 923456）。
        //最小化 b：
        //
        //如果第一位不是 1，把它和所有相同的数字换成 1（比如 555 → 111）。
        //
        //如果第一位是 1，找到后面第一个不是 0 或 1 的数字，把它和所有相同的数字换成 0（比如 10000 → 10000 不能变，但 10000 → 00000 非法，所以只能换其他数字）。
        char[] s = String.valueOf(num).toCharArray();
        char[] a = s.clone();
        char x = '9';
        for (int i = 0; i < a.length; i++) {
            if (a[i] != '9') {
                x = a[i];
                break;
            }
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) a[i] = '9';
        }

        // 生成最小的b：分情况处理
        char[] b = s.clone();
        char y;
        if (b[0] != '1') {
            y = b[0];
            for (int i = 0; i < b.length; i++) {
                if (b[i] == y) b[i] = '1';
            }
        } else {
            // 第一位是1，找后面第一个非0/1的数字替换为0
            y = '0';
            for (int i = 1; i < b.length; i++) {
                if (b[i] != '0' && b[i] != '1') {
                    y = b[i];
                    break;
                }
            }
            for (int i = 0; i < b.length; i++) {
                if (b[i] == y) b[i] = '0';
            }
        }

        return Integer.parseInt(new String(a)) - Integer.parseInt(new String(b));

    }

    //力扣283
    public void moveZeroes(int[] nums) {
        for (int i=0; i<nums.length; i++){
            if (nums[i] == 0){
                for (int j=i+1; j<nums.length; j++){
                    if (nums[j] != 0){
                        nums[i] = nums[j];
                        nums[j] = 0;
                        break;
                    }
                }
            }
        }
    }

    public static <T> void printArray(T[] array) {
        for (T item : array) {
            System.out.println(item);
        }
    }

    // 返回两个元素中“更大者”，要求 T 是可比较的
    public static <T extends Comparable<T>> T max(T a, T b) {
        return (a.compareTo(b) >= 0) ? a : b;
    }


    //力扣 389
    public static char findTheDifference(String s, String t) {

        // 获取字符串t的最后一个字符
        char c = t.charAt(t.length() - 1);
        // 创建一个HashMap来存储字符串s中每个字符出现的次数
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            if (map.get(t.charAt(i)) == null || map.get(t.charAt(i)) == 0) {
                return t.charAt(i);
            } else {
                map.put(t.charAt(i), map.get(t.charAt(i)) - 1);
            }
        }

        return c;
    }

    //力扣28
    public int strStr(String haystack, String needle) {
        int res = -1;
        if (haystack.length() < needle.length()) {
            return res;
        }
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.length()-1-i < needle.length()){
                return -1;
            }
            if (haystack.substring(i, i + needle.length()).equals(needle)){
                res = i;
                break;
            }
        }
        return res;
    }

    //力扣3423
    public int maxAdjacentDistance(int[] nums) {
        int res = 0;
        for(int i = 0; i < nums.length-1; i++){
            res = Math.max(res, Math.abs(nums[i] - nums[i+1]));
        }
        res = Math.max(res, Math.abs(nums[nums.length-1])-nums[0]);
        return res;
    }

    //力扣242
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] array = new char[26];
        for (char c : s.toCharArray()) {
            array[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            array[c - 'a']--;
        }
        for (char c : array) {
            if (c != 0) {
                return false;
            }
        }
        return true;
    }

    //力扣459
    public static boolean repeatedSubstringPattern(String s) {
        int[] array = new int[26];
        for (char c: s.toCharArray()){
            array[c - 'a']++;
        }
        for (int i = 0; i < array.length; i++) {
            if ((array[i]>1&&s.length()%array[i]!=0) || (array[i]==1)) {
                return false;
            }
        }
        return true;
    }

}
