package Application;

public class Application {

    /**
     * Main function
     * @param args not used
     */
    public static void  main(String[] args){
        Application app = new Application();
        app.start();
    }

    /**
     * Starts the execution of the Application
     */
    private void start(){
        AppStarter appStarter = new AppStarter();
        appStarter.startProgram();
    }
}
