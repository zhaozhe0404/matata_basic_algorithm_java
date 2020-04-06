package algorithm.dp;

import java.util.HashMap;

/**
 * 动态规划问题由浅入深
 */
public class DynamicProgrammingMouse {

    public static void main(String[] args) {
        DynamicProgrammingMouse dpm = new DynamicProgrammingMouse();
        System.out.println(dpm.getClimbingWays(12));
        System.out.println(dpm.getClimbingWaysIterate(12, new HashMap<>()));
        System.out.println(dpm.getClimbingWaysDp(12));
        int[] p = new int[]{3, 4, 3, 5, 5};
        int[] g = new int[]{200, 300, 350, 400, 500};
        //System.out.println(dpm.getGoldAmount(5, 10, p, g));
        System.out.println(dpm.getGoldAmountDp(5, 10, p, g));
    }

    /**
     * 1. 上台阶问题
     * 问题描述：有十个台阶，每次只能往上走1个或2个台阶，问有多少种走法
     * 解决思路：看最后一个台阶怎么上，a. 从第8个台阶走，跨2个台阶 b. 从第9个台阶走，跨1个台阶
     *          假设十个台阶有f(10)种走法，八个台阶有f(8)种走法，九个台阶有f(9)种走法
     *          那么，f(10) = f(8) + f(9)
     * 所以有： f(n) = f(n-2) + f(n-1); f(1) = 1; f(2) = 2
     * 最有子结构： f(10)的最优子结构是f(8)和f(9)
     * 边界： f(1) = 1; f(2) = 2
     * 状态转移方程： f(n) = f(n-2) + f(n-1);
     */
    public int getClimbingWays(int n) {// n是台阶数量
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return getClimbingWays(n-1) + getClimbingWays(n-2);
    }

    /**
     * 1. 上台阶问题（续1） --- 备忘录算法
     * 复杂度问题：上面的递归方式复杂度是2^n，因为递归调用链类似一个满二叉树，二叉树的一个节点是一次运算，一共有2^n个节点
     * 如何降低复杂度呢？
     * 可以看到这颗二叉树：
     *                   f(n)
     *                |        \
     *             f(n-1)    f(n-2)
     *         |       \     |     \
     *      f(n-2)  f(n-3)  f(n-3) f(n-4)
     *    …………………………………………………………………………
     * 其中，很多次调用都是重复的，比如根节点左子树的f(n-2)与右子树的f(n-2)是重复计算的
     * 所以，
     *    我们每计算一个f(n)就把其结果用hash表记录下来
     *    当之后用到的时候，就先从hash表中取
     * 所以，
     *    我们需要将递归的方式改为迭代的方式
     */
    public int getClimbingWaysIterate(int n, HashMap<Integer, Integer> map) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        } else {
            int result = getClimbingWaysIterate(n-1, map) + getClimbingWaysIterate(n-2, map);
            map.put(n, result);
            return result;
        }
    }

    /**
     * 1. 上台阶问题（续2）---空间复杂度优化（动态规划算法）
     * 上面的算法空间复杂度是O(n)
     * 我们可以从这个二叉树底部向上计算，这样计算每个f(n)时，f(n-1)和f(n-2)已经计算好了
     */
    public int getClimbingWaysDp(int n) {
        int left = 1;
        int right = 2;
        if (n == 1) {
            return left;
        }
        if (n == 2) {
            return right;
        }
        int result = 0;
        for (int i=3; i<=n; i++) {
            result = left + right;
            left = right;
            right = result;
        }
        return result;
    }

    /**
     * 2. 国王和金矿问题
     * 问题描述：有5座金矿，每座金矿可采量分别是200金、300金、350金、400金、500金，挖完金矿分别需要3、4、3、5、5人，
     *          国王现在有10个人，一座金矿要么全挖，要么不挖，问怎么分配人力，可以得到的黄金最多。
     * 解决思路：第5个金矿存在挖与不挖两种选择
     * 问题来了：如果第5个金矿挖，那么前4个金矿的人力是10-（第5个金矿需要的人力）；------- A  最终获得黄金dp(4,5)+G[4] G[]代表每个金矿的黄金量
     *          如果第5个金矿不挖，那么前4个金矿的人力是10；                    ------- B 最终获得黄金dp(4,10)
     *          最终，5个金矿下，最有选择来自于上述两种情况A和B：max[dp(4,5)+G[4], dp(4,10)]
     *  最优子结构：dp(5,10) = max[dp(4,5)+G[4], dp(4,10)]
     *             dp(n,W) = max[dp(n-1,W-P[n-1])+G[n-1], dp(n-1,W)]  其中，P[]代表用工量
     *  边界：n=1时，当W>=P[0]，dp(n,W) = G[0]
     *              当W<P[0]，dp(n,W) = 0
     *  状态转移方程：
     *       dp(n, W) = 0 (n=1 and W<P[0])
     *       dp(n, W) = G[0] (n=1 and W>=P[0])
     *       dp(n, W) = dp(n-1, W) (n>1 and W<P[n-1])
     *       dp(n, W) = max{ dp(n-1, W), dp(n-1, W-P[n-1])+G[n-1]) } (n>1 and W>=P[n-1])
     */
    /* 递归方式 */
    public int getGoldAmount(int n, int w, int[] p, int[] g) {
        if (n == 1 && w < p[0]) {
            return 0;
        }
        if (n == 1 && w >= p[0]) {
            return g[0];
        }
        if (w < p[n-1]) {
            return getGoldAmount(n-1, w, p, g);
        }
        if (w >= p[n-1]) {
            return Math.max(getGoldAmount(n-1, w, p, g), getGoldAmount(n-1, w-p[n-1], p, g)+g[n-1]);
        }
        return 1;
    }

    /**
     * 2. 国王和金矿问题 -- 动态规划解决
     * 我们列一张表：
     *                   worker1    worker2    worker3   worker4   worker5   worker6   worker7   worker8   worker9   worker10
     * goldmine1(200/3)     0          0        200        200       200      200       200       200        200       200
     * goldmine2(300/4)     0          0        200        300       300      300       500       500        500       500
     * goldmine3(350/3)     0          0        350        350       350      550       650       650        650       850
     * goldmine4(400/5)     0          0        350        350       400      550       650       750        750       850
     * goldmine5(500/5)     0          0        350        350       500      550       650       850        850       900
     *
     * a. 第一行是需要先计算好的  w>=p[0], result=g[0]; w<p[0], results=0;
     * b. 后面“每一行”，是根据“前一行”计算的
     *       ba. w数量小于当前金矿j用工量(w<p[i])时，黄金量为上一行对应工人数量的可得黄金量(results[i]=previousResult[i])
     *       bb. w数量大于当前金矿j用工量(w>p[i])时，黄金量为下面两者最大值(max{ previousResults[i], previousResults[i-p[j]]+g[j] })
     *           bba. w个工人开采j-1个金矿时，可获得的黄金量 previousResults[i]
     *           bbc. w-p[j]个工人开采j-1个金矿，同时，p[j]个工人开采第j个金矿  previousResults[i-p[j]]+g[j]
     */
    public int getGoldAmountDp(int n, int w, int[] p, int[] g) {
        int[] prevResults = new int[w+1];
        int[] results = new int[w+1];
        // 初始化第一个金矿的可采量
        for (int i=1; i<=w; i++) {
            if (i<p[0]) {
                prevResults[i] = 0;
            } else {
                prevResults[i] = g[0];
            }
        }

        // 从第二个金矿开始计算每个金矿的可采量
        for (int i=1; i<n; i++) {
            for (int j=1; j<=w; j++) {
                if (j<p[i]) {
                    results[j] = prevResults[j];
                } else {
                    results[j] = Math.max(prevResults[j], prevResults[j-p[i]]+g[i]);
                }
            }
            for (int k=1; k<=w; k++) {
                prevResults[k] = results[k];
            }
        }

        return results[w];
    }

    /**
     * 动态规划算法什么时候算法复杂度远大于递归？
     * 有没有发现：
     *      递归复杂度2^n只和金矿数量有关系？而动态规划复杂度n*w，和金矿数量、工人数量都有关系。
     *  所以，
     *      当金矿数量很少，工人数量很多时，递归复杂度不一定比动态规划算法差
     */


    //////////////////  The end. //////////////
}













