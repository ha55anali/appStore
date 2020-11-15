package GUI.classes;

public class App {
    String name;
    String description;
    int price;

    public App(){
        name="";
        description="";
        price=0;
    }

    public App(String name, String desc, int price){
        this.name=name;
        this.description=desc;
        this.price=price;

    }

}
