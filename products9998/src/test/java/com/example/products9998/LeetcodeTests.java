package com.example.products9998;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author :  yuhao
 * @date: 2020/10/29
 * @description:
 */
public class LeetcodeTests {
    // 1. 打印出所有的密码组合
    String plusOne(String s, int j){
        char[] chars = s.toCharArray();
        if (chars[j] == '9'){
            chars[j] = '0';
        }else {
            chars[j] += 1;
        }
        return new String(chars);
    }

    String minusOne(String s, int j){
        char[] chars = s.toCharArray();
        if (chars[j] == '0'){
            chars[j] = '9';
        }else {
            chars[j] -= chars[j];
        }
        return new String(chars);
    }


    int openLock(String[] deadends, String target){
        // 记录需要跳过的死亡密码
        Set<String> dead = new HashSet<>();
        for (String deadend : deadends) {
            dead.add(deadend);
        }

        // base case检查
        if (dead.contains("0000") || dead.contains(target)){
            return -1;
        }

        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>(); // 记录访问过的密码
        int step = 0; // 记录已经走过的路数
        queue.offer("0000");
        visited.add("0000");
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();

                for (int j = 0; j < 4; j++) {
                    String plusOne = plusOne(poll, j);
                    String minusOne = minusOne(poll, j);
                    if (plusOne.equals(target) || minusOne.equals(target)){
                        return step + 1;
                    }
                    if (!dead.contains(plusOne) && visited.add(plusOne)){
                        queue.add(plusOne);
                    }
                    if (!dead.contains(minusOne) && visited.add(minusOne)){
                        queue.add(minusOne);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    @Test
    public void testOpenLock(){
        System.out.println(openLock(new String[]{"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"}, "8888"));
    }

    boolean[][] visited;
    int[][] dirs = {{-1,0}, {0,1}, {1,0},{0,-1}};
    public int numIslands(char[][] grid){
        int res = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1' && !visited[i][j]){
                    visited[i][j] = true;
                    dfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int row, int col) {
        for (int[] dir : dirs) {
            int n_row = row + dir[0];
            int n_col = col + dir[1];
            if (n_row < 0 || n_row >= grid.length || n_col < 0 || n_col >= grid[0].length || visited[n_row][n_col] || grid[n_row][n_col] == '0'){
                continue;
            }
            visited[n_row][n_col] = true;
            dfs(grid, n_row, n_col);
        }
    }

    @Test
    public void testHashCode(){
        System.out.println("柳岩".hashCode());
    }

    int res = 0;
    public int islandPerimeter(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j] && grid[i][j] == 1){
                    visited[i][j] = true;
                    dfs(grid, visited, i, j);
                }
            }
        }
        return res;
    }

    private void dfs(int[][] grid, boolean[][] visited, int row, int col) {
        for (int[] dir : dirs) {
            int n_row = row + dir[0];
            int n_col = col + dir[1];
            if (n_row < 0 || n_row >= grid.length || n_col < 0 || n_col >= grid[0].length ||grid[n_row][n_col] == 0){
                res++;
                continue;
            }
            if (!visited[n_row][n_col]){
                visited[n_row][n_col] = true;
                dfs(grid, visited, n_row, n_col);
            }

        }
    }

    public boolean canFormArray(int[] arr, int[][] pieces) {
        return true;
    }

    List<String> sentences;
    boolean[] chars;
    public List<String> wordBreak(String s, List<String> wordDict){
        chars = new boolean[26];
        for (String word : wordDict) {
            for (int i = 0; i < word.length(); i++) {
                chars[word.charAt(i) - 'a'] = true;
            }
        }
        sentences = new ArrayList<>();
        dfs(s, wordDict, 0, new ArrayList<>());
        return sentences;
    }

    private boolean dfs(String s, List<String> wordDict, int start, List<String> tmp){
        if (start >= s.length()){
            sentences.add(String.join(" ", tmp));
            return true;
        }
        for (int i = start; i < s.length(); i++) {
            if (!chars[s.charAt(i) - 'a']){
                return false;
            }
            if (wordDict.contains(s.substring(start, i + 1))){
                tmp.add(s.substring(start, i + 1));
                if (!dfs(s, wordDict, i + 1, tmp)){
                    return false;
                }
                tmp.remove(tmp.size() - 1);
            }
        }
        return false;
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i])){
                res.add(nums2[i]);
            }
        }
        return res.stream().distinct().mapToInt(Integer::intValue).toArray();
    }

}

class UnionFind{
    int[] parent;
    int count;

    public UnionFind(int rows, int cols, char[][] grid){
        parent = new int[rows * cols];
        count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1'){
                    parent[i * cols + j] = i * cols + j;
                    count++;
                }
            }
        }
    }

    public void union(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot){
            return;
        }
        parent[pRoot] = qRoot;
        count--;
    }

    public int find(int p){
        while (p != parent[p]){
            p = parent[parent[p]];
        }
        return p;
    }
}
