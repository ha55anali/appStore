package dbaseInterface;

public class Search {
    
    public static int binarySearch(int [] arr, int key, int left, int right) {
        
        if(left > right) {
            throw new IllegalArgumentException("Array_Limits_Exception");
        }
        
        int mid = (right + left) / 2;
        if(arr[mid] == key) return mid;
        if(left == right ) {
            if(arr[left] != key)
                return -1;
            else
                return left;
        }

        if(key < arr[mid])
            return binarySearch(arr, key, left, mid-1);
        else
            return binarySearch(arr, key, mid+1, right);
    }
}
