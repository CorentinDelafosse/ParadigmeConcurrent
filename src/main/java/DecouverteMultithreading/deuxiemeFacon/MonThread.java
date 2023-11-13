package DecouverteMultithreading.deuxiemeFacon;

public class MonThread implements Runnable{
    private String message;
    private boolean termine;

    public MonThread(String message) {
        this.message = message;
    }

    public void run(){
        termine = false;

        while(!termine){
            System.out.println(message+"------------"+Thread.currentThread().getName());

            try{
                Thread.sleep(50);
            }
            catch (InterruptedException e){
            }
        }
    }

    public void mettreFin(){
        termine = true;
    }
}
