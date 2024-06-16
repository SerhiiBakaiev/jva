package core;

public enum Direction {
    R(0),
    L(1),
    D(2),
    U(3);
    private final int animationRow;

    Direction(int row) {
        animationRow = row;
    }

    public  static Direction fromMotion(Motion motion){
        double x = motion.getVector().getX();
        double y = motion.getVector().getY();
        if(x == 0 && y >0) // down
            return D;
        if(x == 0 && y < 0) // up
            return U;
        if(x > 0 && y == 0)
            return R;
        if(x < 0 && y == 0)
            return L;

        return  U;
    }

    public int getAnimationRow() {
        return animationRow;
    }
}
