public class Median_Of_Medians {

    class calculate_Median
    {
        static int a, b;
        int temp;

        static int Partition(int arr[], int l, int r)
        {int temp;
            int lst = arr[r], i = l, j = l;
            while (j < r)
            {
                if (arr[j] < lst)
                {

                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;

                    i++;
                }
                j++;
            }
            temp = arr[i];
            arr[i] = arr[r];
            arr[r] = temp;
            return i;
        }
        static int randomPartition(int arr[], int l, int r)
        { int temp;
            int n = r - l + 1;
            int pivot = (int) (Math.random() % n);
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            return Partition(arr, l, r);
        }
        static int MedianUtil(int arr[], int l, int r, int k)
        {
            if (l <= r)
            {
                int partitionIndex = randomPartition(arr, l, r);
                if (partitionIndex == k)
                {
                    b = arr[partitionIndex];
                    if (a != -1)
                        return Integer.MIN_VALUE;
                }
                else if (partitionIndex == k - 1)
                {
                    a = arr[partitionIndex];
                    if (b != -1)
                        return Integer.MIN_VALUE;
                }
                if (partitionIndex >= k)
                    return MedianUtil(arr, l, partitionIndex - 1, k);

                else
                    return MedianUtil(arr, partitionIndex + 1, r, k);
            }

            return Integer.MIN_VALUE;
        }
        static void findMedian(int arr[], int n)
        {
            int res;
            a = -1;
            b = -1;

            if (n % 2 == 1)
            {
                MedianUtil(arr, 0, n - 1, n / 2);
                res = b;
            }
            else
            {
                MedianUtil(arr, 0, n - 1, n / 2);
                res = (a + b) / 2;
            }

        }

    }



}
