package 创建型模式;


import lombok.Data;

public class 建造者模式 {
    public static void main(String[] args) {
        Director director = new Director(new ConcreteBuilder1());
        RoomProduct roomProduct = director.getRoomProduct();
    }
}

//复杂对象(产品)
@Data
class RoomProduct {
    private String door;
    private String sofa;
    private String television;
    private String wall;
    private String xxx;
}
//抽象建造者
interface Builder {
   void setDoor();
   void setSofa();
   void setTelevision();
   void setWall();
   void setXxx();
   RoomProduct getRoomProduct();
}
//具体建设者1
class ConcreteBuilder1 implements Builder{

    private RoomProduct roomProduct = new RoomProduct();

    ConcreteBuilder1(){
    }

    ConcreteBuilder1(RoomProduct roomProduct) {
        this.roomProduct = roomProduct;
    }

    @Override
    public void setDoor() {
        roomProduct.setDoor("门");
    }

    @Override
    public void setSofa() {
        roomProduct.setSofa("沙发");
    }

    @Override
    public void setTelevision() {
        roomProduct.setTelevision("电视");
    }

    @Override
    public void setWall() {
        roomProduct.setWall("墙");
    }

    @Override
    public void setXxx() {
        roomProduct.setXxx("xxx");
    }

    @Override
    public RoomProduct getRoomProduct() {
        this.setDoor();
        this.setSofa();
        this.setTelevision();

        return roomProduct;
    }
}

//指挥者
class Director {

    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public RoomProduct getRoomProduct() {
        builder.setWall();
        builder.setXxx();
        RoomProduct roomProduct = builder.getRoomProduct();
        return roomProduct;
    }

}