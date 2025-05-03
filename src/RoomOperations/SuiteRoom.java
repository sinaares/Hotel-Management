package RoomOperations;

public class SuiteRoom extends Room{
    private static final double PRICE = 3000.0;
    private static final int CAPACITY = 3;
    private static final String TYPE = "SUITE";

    public SuiteRoom(int roomNumber){
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
