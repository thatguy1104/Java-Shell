package uk.ac.ucl.jsh;

public class Factory {
    
    public Application getApp(String app) {
        if (app.equals("cd")) {
            return new Cd();
        } else if (app.equals("pwd")) {
            return new Pwd();
        }
        return null;
    }
}
