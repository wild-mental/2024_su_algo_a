import java.util.Arrays;
import java.util.Collections;

public class NationalAssemblyElection {
    public static void main(String[] args) {
        // read 1 line of number input from standard input
        java.util.Scanner sc = new java.util.Scanner(System.in);
        int n = sc.nextInt();
        // read another line of number input from standard input
        int myVotes = sc.nextInt();
        if (n == 1) {
            System.out.println(0);
            return;
        }
        // read n lines of number input from standard input
        int[] otherVotes = new int[n-1];
        for (int i = 0; i < n-1; i++) {
            otherVotes[i] = sc.nextInt();
        }
        // reverse sort the other votes
        Arrays.sort(otherVotes);
        reverseArray(otherVotes);
        // check the winning condition (the number of votes that I need to buy)
        int voteGap = otherVotes[0] - myVotes;
        // check if I need to buy votes
        if (voteGap < 0) {
            System.out.println(0);
            return;
        } else if (voteGap == 0) {
            System.out.println(1);
            return;
        }
        // iterate through the sorted votes to determine the number of votes to buy
        int votesBought = 0;
        // iterate through the sorted votes and deal with the votes grouped by the same number of votes
        boolean isDone = false;
        int chunkSize;
        while (!isDone) {
            // make chunk of votes with the same number of votes
            chunkSize = 1;
            int gapToNextChunk = 0;
            for (int i = 0; i < n-2; i++) {
                if (otherVotes[i] == otherVotes[i+1]) {
                    chunkSize++;
                } else {
                    gapToNextChunk = otherVotes[i] - otherVotes[i+1];
                    break;
                }
            }
            voteGap = otherVotes[0] - myVotes;
            // determine the number of votes to buy
            if (chunkSize < voteGap) {
                // (1) if chunk is smaller than the vote gap, buy 1 vote per each items in chunk
                votesBought += chunkSize;
                myVotes += chunkSize;
                // decrease the vote count in the chunk
                for (int i = 0; i < chunkSize; i++) {
                    otherVotes[i]--;
                }
            } else if (chunkSize == voteGap) {
                // (2) if chunk is same size with the vote gap, buy 1 vote per each items in chunk and finish
                votesBought += chunkSize;
                System.out.println(votesBought);
                break;
            } else {
                // (3) if chunk is larger than the gap, buy the gap number of votes + 1
                votesBought += voteGap + 1;
                System.out.println(votesBought);
                break;
            }
        }
    }
    // Function to reverse the array
    public static void reverseArray(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            // Swap elements at start and end indices
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            // Move the pointers towards the center
            start++;
            end--;
        }
    }
}
