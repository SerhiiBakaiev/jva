package gfx;

public class ResourceProvider {

    private static ResourceProvider instance;


    private ResourceProvider(){

    }

    public static ResourceProvider getInstance(){
        if(instance != null){
            return instance;
        }

        instance = new ResourceProvider();
        return instance;
    }

    public TileLibrary getTileLibrary(){
        return TileLibrary.getInstance();
    }
}
