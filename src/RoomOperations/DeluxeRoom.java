package RoomOperations;

public class DeluxeRoom extends Room{
    private static final double PRICE = 2000.0;
    private static final int CAPACITY = 2;
    private static final String TYPE = "DELUXE";

    public DeluxeRoom(int roomNumber){
        super(roomNumber,PRICE,CAPACITY,TYPE);
    }


    @Override
    public int getCapacity() {
        return CAPACITY;
    }

    @Override
    public double getPrice() {
        return PRICE;
    }

    @Override
    public String getRoomType() {
        return TYPE;
    }
}
