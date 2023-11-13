package DecouverteMultithreading.premiereFacon;

public class MonThread extends Thread{

    private String message;
    private boolean termine;

    public MonThread(String message) {
        this.message = message;
    }

    public void run(){
        termine = false;

        while(!termine){
            System.out.println(message+"------------"+Thread.currentThread().getName());
        }
    }

    public void mettreFin(){
        termine = true;
    }
}
