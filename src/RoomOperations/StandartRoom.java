package RoomOperations;

public class StandartRoom extends Room {
    private static final double PRICE = 1000.0;
    private static final int CAPACITY = 1;
    private static final String TYPE = "STANDARD";

    public StandartRoom(int roomNumber){
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
