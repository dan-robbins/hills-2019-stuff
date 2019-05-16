import java.util.LinkedList;

public class ThreadWorker extends Thread {
    private LinkedList<String> list;
    private String myWord;
    private int count;

    ThreadWorker(LinkedList<String> list, String word) {
        this.list = list;
        this.myWord = word.toUpperCase();
        count = 0;
    }

    public void run() {
        while (true) {
            String line;
            // check if list is not empty and removes the head
            // synchronization needed to avoid atomicity violation
            synchronized (list) {
                if (list.isEmpty())
                    return; // list is empty
                line = list.remove();
            }
            String[] words = line.split(" ");
            for(String word : words){
                if(word.toUpperCase().equals(myWord)){
                    count++;
                }
            }
        }
    }
    int getCount(){
        return count;
    }
}
