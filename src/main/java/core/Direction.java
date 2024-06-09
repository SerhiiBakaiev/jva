package core;

public enum Direction {
    D(0),
    L(1),
    R(2),
    U(3);

    private int _animationRow;

    Direction(int row) {
        _animationRow = row;
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
        return _animationRow;
    }
}
