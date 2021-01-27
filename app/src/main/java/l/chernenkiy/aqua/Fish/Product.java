package l.chernenkiy.aqua.Fish;

public class Product {

    private String name;
    private String size;
    private String price;
    private String title;
    private String image;

    public Product(String name, String size, String price, String title, String image) {

        this.name = name;
        this.size = size;
        this.price = price;
        this.title = title;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }


    public String getImage() {
        return image;
    }

 }
