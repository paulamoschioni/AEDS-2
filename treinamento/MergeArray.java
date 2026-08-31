import java.util.Scanner;
/*You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, 
and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
Merge nums1 and nums2 into a single array sorted in non-decreasing order.
The final sorted array should be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, 
 where the first m elements denote the elements that should be merged, and the last n 
 elements are set to 0 and should be ignored. nums2 has a length of n. */
class MergeArray {
    public void swap(int[] nums1, int[] nums2, int i){
        int temp = nums1[i];
        nums1[i] = nums2[i];
        nums2[i] = temp;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //comparing 
        int size = m + n;
        int j; //index to control the position of nums2
        for(int i = 0; i < size; i++){ 
         j = 0;
         if(nums1[i] > nums2[i]){  //if is in drecreasing ordem
             swap(nums1,nums2,i);
         } 
        
            else if(nums1[i] == nums2[i]){ //if is the same number, it should enter in the following poçsition of nums1
                temp = nums1[i+1];
                nums1[i+1] = nums2[i];
                nums2[i] = temp;
            }
            //if nums1 < nums2 its already correct
        }
        
    }
}