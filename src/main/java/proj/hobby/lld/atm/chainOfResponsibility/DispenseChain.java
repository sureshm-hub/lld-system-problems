package proj.hobby.lld.atm.chainOfResponsibility;

public interface DispenseChain {
    void setNext(DispenseChain chain);
    void dispense(int amount);
    boolean canDispense(int amount);
}
