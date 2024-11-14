package project;

import project.controller.Controller;

public class Application {

    public static void main(String[] args) {
        Controller controller = new Controller();

        controller.startSearch();
        controller.findShortestPath();
    }
}
