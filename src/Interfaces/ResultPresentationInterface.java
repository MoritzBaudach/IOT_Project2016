package Interfaces;

public interface ResultPresentationInterface {

    public abstract void execute() throws Exception;
    
    public abstract void plotGraph() throws Exception;

    public abstract void exportToExcel() throws Exception;
}