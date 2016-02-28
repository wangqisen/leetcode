package codes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by wangqisen on 16/2/25.
 */
public class Q330 {

    /*
    *
    * 最简单的思路就是将添加的可能性元素都通过迭代的方式试一遍,直至满足可以加到n为止.
    *
    * */

    private int miniumEleNum=Integer.MAX_VALUE;

    public int minPatches(int[] nums, int n) {
        List<Integer> newNums=new ArrayList<Integer>();
        for(int num:nums){
            newNums.add(num);
        }

        boolean[] visited=new boolean[n+1];
        Arrays.fill(visited,false);
        for(int num:nums){
            visited[num]=true;
        }

        ite(newNums,0,n,visited);

        return miniumEleNum-newNums.size();
    }

    public void ite(List<Integer> newNums,int m,int n,boolean []visited){
        if(ifAllFit(n,newNums)) {
            if(newNums.size()<miniumEleNum)
                miniumEleNum=newNums.size();
            return;
        }

        for(int i=m+1;i<=n;i++){
            if(!visited[i]){
                visited[i]=true;
                newNums.add(i);
                int index=newNums.size()-1;
                ite(newNums,i+1,n,visited);
                visited[i]=false;
                newNums.remove(index);
            }
        }
    }

    public boolean ifAllFit(int n,List<Integer> list){
        List<Integer> newList=new ArrayList<Integer>();
        newList.addAll(list);

        Collections.sort(newList);
        for(int m=1;m<=n;m++){
            if(!ifFit(m,newList))
                return false;
        }
        return true;
    }

    public boolean ifFit(int target,List<Integer> list){
        boolean[] visited=new boolean[list.size()];
        List<Integer> newList=new ArrayList<Integer>();
        return iteFit(target,list,visited,newList);
    }

    public boolean iteFit(int target,List<Integer> list,boolean[] visited,List<Integer> newList){
        int sum=0;
        for(int i:newList)
            sum+=i;
        if(sum==target) {
            return true;
        } else if(sum>target) {
            return false;
        } else{
          for(int i=0;i<list.size();i++){
              if(!visited[i]){
                  visited[i]=true;
                  newList.add(list.get(i));
                  int index=newList.size()-1;
                  boolean flag=iteFit(target,list,visited,newList);
                  if(flag)
                      return true;
                  visited[i]=false;
                  newList.remove(index);
              }
          }
        }
        return false;
    }

    public static void main(String args[]){
        int []nums=new int[]{1,2,3};
        int n=5;
        List<Integer> list=new ArrayList<Integer>();
        for(int num:nums)
            list.add(num);
        Q330 q330=new Q330();
        System.out.println(q330.minPatches(nums,n));
    }
}
