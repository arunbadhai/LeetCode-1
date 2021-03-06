package Topics.BTree;

import Libs.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BTConstructPrePostTraversal {
    private int preIndex = 0, posIndex = 0;

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        TreeNode root = new TreeNode(pre[preIndex++]);
        if (root.val != post[posIndex]) {
            root.left = constructFromPrePost(pre, post);
        }
        if (root.val != post[posIndex]) {
            root.right = constructFromPrePost(pre, post);
        }
        posIndex++;
        return root;
    }

    public TreeNode constructFromPrePostII(int[] pre, int[] post) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < post.length; i++) {
            m.put(post[i], i);
        }
        return dfs(m, pre, post, 0, pre.length-1, 0, post.length-1);
    }

    private TreeNode dfs(Map<Integer, Integer> m, int[] pre, int[] post, int pres, int pree, int posts, int poste) {
        if (pres > pree || posts > poste) {
            return null;
        }
        TreeNode root = new TreeNode(post[poste]);
        if (pres + 1 <= pree){
            int index = m.get(pre[pres+1]);
            root.left = dfs(m, pre, post, pres+1, pres+1+(index-posts), posts, index);
            root.right = dfs(m, pre, post, pres+2+(index-posts), pree, index+1, poste-1);
        }
        return root;

    }

    public TreeNode constructFromPrePostIII(int[] pre, int[] post) {
        if (pre.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        if (pre.length == 1) {
            return root;
        }
        int i = 0;
        while (post[i] != pre[1]) {
            i++;
        }
        root.left = constructFromPrePostIII(Arrays.copyOfRange(pre, 1, i+2),
                Arrays.copyOfRange(post, 0, i+1));
        root.right = constructFromPrePostIII(Arrays.copyOfRange(pre, i+2, pre.length),
                Arrays.copyOfRange(post, i+1, post.length-1));
        return root;
    }
}
