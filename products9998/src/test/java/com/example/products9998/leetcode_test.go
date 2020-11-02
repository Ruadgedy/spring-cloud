package products9998

import (
	"math"
)

type TreeNode struct {
	Val int
	Left *TreeNode
	Right *TreeNode
}

var res = math.MaxInt16
func minDepth(root *TreeNode) int {
	if root == nil {
		return 0
	}
	recur(root, 1)
	return res
}

func recur(root *TreeNode, depth int) {
	if root == nil {
		return
	}
	if root.Left == nil && root.Right == nil {
		res = min(res, depth)
		return
	}
	if root.Left != nil {
		recur(root.Left, depth + 1)
	}
	if root.Right != nil {
		recur(root.Right, depth + 1)
	}
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func intersection(nums1 []int, nums2 []int) []int {
	m := make(map[int]bool)
	res := []int{}
	for i := 0; i < len(nums1); i++ {
		m[nums1[i]] = true
	}
	for i := 0; i< len(nums2); i++ {
		if m[nums2[i]] {
			res = append(res, nums2[i])
			m[nums2[i]] = false
		}
	}
	return res
}


type Queue struct {
	arr []TreeNode
}
