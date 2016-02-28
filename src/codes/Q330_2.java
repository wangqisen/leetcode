package codes;

/**
 * Created by wangqisen on 16/2/28.
 */
public class Q330_2 {

    public int minPatches(int[] nums, int n) {
        int miss=1,index=0,times=0;
        while(miss<=n){
            if(index<nums.length&&nums[index]<=miss){
                miss+=nums[index];
                index++;
            }else{
                miss+=miss;
                times++;
            }
        }
        return times;
    }

    public static void main(String args[]){
        Q330_2 q330_2=new Q330_2();
        int[] nums=new int[]{1,3};
        int n=6;
        System.out.println(q330_2.minPatches(nums,n));
    }
}
