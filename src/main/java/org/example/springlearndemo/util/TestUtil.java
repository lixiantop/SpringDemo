package org.example.springlearndemo.util;

import java.util.*;

public class TestUtil {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            System.out.println("hello world");
        });
        t.start();

    }

    public class ListNode {
      int val;
     ListNode next;
     ListNode() {}
      ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

    public ListNode addTwoNumbersV2(ListNode l1, ListNode l2) {
        ListNode tailL1 = reverseList(l1);
        ListNode tailL2 = reverseList(l2);
        ListNode head = addTwoNumbersV1(tailL1, tailL2);
        return reverseList(head);
    }

    public ListNode addTwoNumbersV1(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode temp = head;
        int carry = 0;
        while (l1 !=null || l2 != null) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            temp.next = new ListNode(sum % 10);
            temp = temp.next;
            carry = sum / 10;
        }
        if (carry > 0) {
            temp.next = new ListNode(carry);
        }
        return head.next;
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode head = new ListNode();
        ListNode temp = head;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                temp.next = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                list2 = list2.next;
            }
            temp = temp.next;
        }
        if (list1 != null) {
            temp.next = list1;
        }
        if (list2 != null) {
            temp.next = list2;
        }
        return head.next;

    }

    public boolean checkStraightLine(int[][] coordinates) {
        for (int i = 0; i < coordinates.length - 2; i++) {
            if ((coordinates[i + 1][1] - coordinates[i][1]) * (coordinates[i + 2][0] - coordinates[i + 1][0]) != (coordinates[i + 2][1] - coordinates[i + 1][1]) * (coordinates[i + 1][0] - coordinates[i][0]))
                return false;
        }


        return true;
    }

    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                five++;
            } else if (bills[i] == 10) {
                if (five == 0) {
                    return false;
                }
                five--;
                ten++;
            } else if (bills[i] == 20) {
                if (ten > 0 && five > 0){
                    ten--;
                    five--;
                } else if (five >= 3){
                    five -= 3;
                } else{
                    return false;
                }
            }
        }
        return true;
    }

    public double average(int[] salary) {
        double sum = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < salary.length; i++) {
            sum += salary[i];
            if (salary[i] < min) {
                min = salary[i];
            }
            if (salary[i] > max) {
                max = salary[i];
            }
        }
        sum = sum - min - max;
        return sum / salary.length;
    }

    public String tictactoe(int[][] moves) {
        for(int i=0; i<moves.length; i++){
            if(moves[i][0] == moves[i][1] && moves[i][1] == moves[i][2])
                return "A";
            if (moves[0][i] == moves[1][i] && moves[1][i] == moves[2][i])
                return "B";
        }
        return "Draw";
    }

    public boolean judgeCircle(String moves) {
        boolean res = false;
        int x = 0, y = 0;
        for (char c : moves.toCharArray()) {
            if (c == 'U') {
                x++;
            } else if (c == 'D') {
                x--;
            } else if (c == 'L') {
                y++;
            } else if (c == 'R') {
                y--;
            }
        }
        if (x==0 && y==0) {
            res = true;
        }
        return res;
    }

    public int calPoints(String[] operations) {
        int sum = 0;
        List<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        for (String s : operations) {
            switch (s) {
                case "C":
                    sum -= stack.pop();
                    break;
                case "D":
                    sum += 2 * stack.peek();
                    stack.push(2 * stack.peek());
                    break;
                case "+":
                    sum += stack.peek() + stack.get(stack.size() - 2);
                    stack.push(stack.peek() + stack.get(stack.size() - 2));
                    break;
                default:
                    stack.push(Integer.parseInt(s));
                    sum += Integer.parseInt(s);
                    break;
            }
        }
        return sum;
    }

    public int possibleStringCount(String word) {
        int count = 1;
        for(int i=0; i<word.length()-1; i++){
            for (int j = i+1; j < word.length(); j++){
                if (word.charAt(i) == word.charAt(j))
                    count++;
                else break;
            }
        }

        return count;
    }

    public String toLowerCase(String s) {
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z'){
                s = s.replace(s.charAt(i), (char)(s.charAt(i) + 32));
            }
        }
        return s;
    }

    public boolean canMakeArithmeticProgression(int[] arr) {
        boolean res = false;
        //arr排序
        Arrays.sort(arr);
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] - arr[i - 1] == arr[i + 1] - arr[i]) {
                res = true;
            } else {
                res = false;
                break;
            }
        }
        return res;
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
