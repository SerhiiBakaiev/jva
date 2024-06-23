package controller;

public interface EntityController extends Controller{
    boolean isRequestingUp();
    boolean isRequestingDown();
    boolean isRequestingRight();
    boolean isRequestingLeft();
    boolean isRequestingSpace();
    boolean isAttacking();
}
